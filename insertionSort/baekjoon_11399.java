import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 사람 수 n 입력받기 ( 1 <= n <= 1,000 )
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 2. 각 사람별 걸리는 시간 배열 입력받기 (1 <= p <= 1,000)
        int[] p = new int[n];
        String input = br.readLine();
        String[] inputArray = input.split(" ");

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(inputArray[i]);
        }

        // 3. 삽입 정렬
        for (int i = 1; i < n; i ++) {
            int insert_point = i;
            int insert_value = p[i];

            for (int j = i-1; j >= 0; j--) {
                if (p[j] < p[i]) {
                    insert_point = j + 1;
                    break;
                }

                if (j == 0) {
                    insert_point = 0;
                }
            }

            for (int j = i; j > insert_point; j--) {
                p[j] = p[j-1];
            }
            p[insert_point] = insert_value;
        }

        // 4. 합 배열 만들고 총합 출력
        int[] S = new int[n];
        S[0] = p[0];

        for (int i = 1; i <n; i++) {
            S[i] = S[i-1] + p[i];
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + S[i];
        }
        
        System.out.print(sum);
    }
}
