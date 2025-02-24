import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 바구니 개수
        int K = Integer.parseInt(st.nextToken()); // K마리 모이면 터짐
        int T = Integer.parseInt(st.nextToken()); // 최대 이동 가능 횟수

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());

        int notZeroCount = 0; // 나도리가 존재하는 바구니 개수
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] != 0) notZeroCount++;
        }

        // 모든 바구니가 비어 있다면 즉시 YES
        if (notZeroCount == 0) {
            System.out.println("YES");
            return;
        }

        // 한 개의 바구니만 남은 경우 터트릴 수 없음
        if (notZeroCount == 1) {
            System.out.println("NO");
            return;
        }

        // 오름차순 정렬 (작은 값부터 큰 값으로 이동)
        Arrays.sort(A);

        int left = N - notZeroCount; // 가장 작은 나도리가 있는 바구니 (0이 아닌 첫 번째 위치)
        int right = N - 1; // 가장 큰 나도리가 있는 바구니

        while (T > 0 && left < right) {
            int remaining = K - A[right]; // 현재 바구니가 터지기까지 필요한 나도리 수

            if (A[left] < remaining) {
                // 왼쪽 바구니의 모든 나도리를 옮길 수 있는 경우
                A[right] += A[left];
                T -= A[left];
                left++;
            } else {
                // 왼쪽 바구니 일부만 옮겨서 터트릴 수 있는 경우
                A[right] += remaining;
                A[left] -= remaining;
                T -= remaining;
                right--; // 터진 바구니는 더 이상 필요 없음
                if (A[left] == 0) left++; // 비어있는 바구니 건너뛰기
            }
        }

        // 모든 나도리를 터트릴 수 있다면 YES, 아니라면 NO
        System.out.println(left > right && T >= 0 ? "YES" : "NO");
    }
}
