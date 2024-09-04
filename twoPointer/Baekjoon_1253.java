import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 1253. 좋은 수 구하기
 *
 * 슈도코드
 * 1. 수의 개수 N, N개의 수 배열 A 입력받기
 * 2. 변수 초기화 - 좋은 수의 개수를 저장할 변수 cnt = 0, sum = 0, start_i = 0, end_i = N-1
 * 3. for N, 요소를 전부 순회하며 다른 두 요소의 합이 해당 요소의 값이 되는 경우의 수가 있는지 찾기
 * 3.1 start_i = 0; end_i = N-1;
 * 3.2 while ( start_i != end_i )
 * 3.2.1 if start_i == i, start_i++;
 * 3.2.2 if end_i == i, end_i--;
 * 3.2.3 sum = A[start_i] + A[end_i]
 * 3.2.4 if sum > A[i], end_i--;
 * 3.2.5 if sum < A[i], start_i++;
 * 3.2.6 if sum == A[i], cnt++; break;
 * 4. 최종 cnt 출력
 */
class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);

        System.out.println(goodNumber(N, A));
    }

    public static Integer goodNumber (int N, int[] A) {
        // 2. 변수 초기화 - 좋은 수의 개수를 저장할 변수 cnt = 0, sum = 0, start_i = 0, end_i = N-1
        int cnt = 0;
        int sum = 0;
        int start_i = 0;
        int end_i = N-1;

        // 3. for N, 요소를 전부 순회하며 다른 두 요소의 합이 해당 요소의 값이 되는 경우의 수가 있는지 찾기
        for (int i = 0; i < N; i++) {
            start_i = 0;
            end_i = N-1;

            while (start_i != end_i) {
                if (start_i == i) {
                    start_i++;
                    if (start_i == end_i) break;
                }
                if (end_i == i) {
                    end_i--;
                    if (start_i == end_i) break;
                }
                sum = A[start_i] + A[end_i];

                if (sum > A[i]) {
                    end_i--;
                } else if (sum < A[i]) {
                    start_i++;
                } else {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }
}
