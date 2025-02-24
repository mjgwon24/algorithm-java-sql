import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) return;

            int[] aNums = new int[N];
            int[] bNums = new int[M];

            for (int i = 0; i < N; i++) {
                aNums[i] = Integer.parseInt(br.readLine());
            }
            for (int i = 0; i < M; i++) {
                bNums[i] = Integer.parseInt(br.readLine());
            }

            int results = 0, i = 0, j = 0;
            while (i < N && j < M) {
                if (aNums[i] == bNums[j]) { // 같은 CD 발견
                    results++;
                    i++;
                    j++;
                } else if (aNums[i] < bNums[j]) {
                    // aNums의 값이 작으면 증가
                    i++; 
                } else {
                    // bNums의 값이 작으면 증가
                    j++;
                }
            }

            System.out.println(results);
        }
    }
}
