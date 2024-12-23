import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int T;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(result(N, M));
        }
    }

    static int result(int N, int M) {
        int[][] dp = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) dp[i][i] = 1;
        for (int i = 1; i <= M; i++) dp[1][i] = i;

        for (int i = 2; i <= N; i++) {
            for (int j = i+1; j <= M; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
            }
        }

        return dp[N][M];
    }
}

// 다리는 겹칠 수 없음. 최대한 많은 경우.
// 1. 상태 정의
// dp[i][j]

// 2. 선택지 도출
// dp[i][j] = dp[i][j-1] + dp[i-1][j-1]

// 3. 선택지 관계 정의 (이전 관계 고려)

// 4. 초기값 설정.
// dp[i][i] = 1

// n = 2, m = 2, dp[2][2] = 1
// (1,1), (2,2)

// n = 2, m = 3, dp[2][3] = dp[2][2] + 2 = 3
// (1,1), (2,2) dp[2][2]
// (1,1), (2,3)
// (1,2), (2,3)

// n = 2, m = 4, dp[2][3] + 3 = 6
// (1,1), (2,2) dp[2][3]
// (1,1), (2,3) dp[2][3]
// (1,2), (2,3) dp[2][3]
// (1,1), (2,4)
// (1,2), (2,4)
// 3 4

// n = 3, m = 3, dp[3][3] = 1
// 1 2 3

// n = 3, m = 4, dp[3][4] = dp[3][3] + dp[2][3] = 4
// 1 2 3 dp[3][3]
// 1 2 4
// 1 3 4
// 2 3 4

// n = 3, m = 5, dp[3][5] = dp[3][4] + dp[2][4] = 10
// 1 2 3 dp[3][4]
// 1 2 4 dp[3][4]
// 1 3 4
// 2 3 4 dp[3][4]
// 1 2 5
// 1 3 5
// 1 4 5
// 2 3 5
// 2 4 5
// 3 4 5

// n = 3, m = 6, dp[3][6] = dp[3][5] + 4 = 10
// 1 2 3 dp[3][4]
// 1 2 4 dp[3][4]
// 2 3 4 dp[3][4]
// 1 2 5
// 2 3 5
// 3 4 5
// 1 2 6
// 2 3 6
// 3 4 6
// 4 5 6

// dp[13][13] = 1
// dp[13][14] = 
// 1 ... 13
// 1 ... 14
