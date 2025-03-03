import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int MOD = 1000000003;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 색 개수 (4 ≤ N ≤ 1,000)
        int K = Integer.parseInt(br.readLine()); // 선택할 색 개수 (1 ≤ K ≤ N)

        if (N / 2 < K) {
            System.out.println(0);
            return;
        }

        long[][] dp = new long[N + 1][N / 2 + 1];

        //초기화
        // dp[i][1] = i
        // if i짝수, dp[i][i/2] = 2
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            if (i % 2 == 0) {
                dp[i][i / 2] = 2;
                // System.out.printf("dp[%d][%d] = %d\n", i, i / 2, 2);
            }
        }
        // System.out.println();
        // System.out.println();

        // dp[i][j] = dp[i - 1][j] + dp[i - 2][j - 1]
        for (int i = 5; i <= N; i++) {
            for (int j = 2; j <= N / 2 && j <= i / 2; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
                // System.out.printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
            }
        }
        
        // 어떤 인접한 두 색도 동시에 선택하지 않고 
        // K개의 색을 고를 수 있는 경우의 수
        // 1,000,000,003 (10억 3) 으로 나눈 나머지를 출력
        System.out.println(dp[N][K] % MOD);
    }
}

/***
색상환: 색을 고리 모양으로 연결하여 나타낸 것
인접한 두 색을 동시에 사용하지 않기
서로 이웃하지 않은 색들을 선택하는 경우의 수

4개의 색, 2개 선택 => 2

i는 4이상, j는 1이상
dp[i][j]: i개의 색 중, j개의 색을 인접하지 않게 선택하는 경우의 수

초기화
dp[i][1] = i
if i짝수, dp[i][i/2] = 2

dp[4][1] = 4
dp[4][2] = 2

dp[5][1] = 5
1, 2, 3, 4, 5
dp[5][2] = 5 = dp[4][2] + dp[3][1] = 2 + 3
1) 3, (+ 4)
2) 4, (+ 5)
3) (+ 5)

dp[6][1] = 6
dp[6][2] = 9 = dp[5][2] + 4
1) 3, 4, (+ 5)
2) 4, 5 (+ 6)
3) 5 (+ 6)
4) (+ 6)
dp[6][3] = 2
1, 3, 5
2, 4, 6

dp[7][1] = 7
dp[7][2] = 14 = dp[6][2] + 5
1) 3, 4, 5 (+ 6)
2) 4, 5, 6 (+ 7)
3) 5, 6 (+ 7)
4) 6 (+ 7)
5) (+ 7)
dp[7][3] = dp[6][3] + dp[5][2] = 2 + 5 = 7

dp[8][1] = 8
dp[8][2] = dp[7][2] + 6 = 20 = dp[i - 1][2] + dp[i - 2][j - 1]
1) 3, 4, 5, 6 (+ 7)
2) 4, 5, 6, 7 (+ 8)
3) 5, 6, 7 (+ 8)
4) 6, 7 (+ 8)
5) 7 (+ 8)
6) (+ 8)
dp[8][3] = 16 = dp[7][3] + dp[6][2] (9)
1 1 1 1 + 2 2 2 + 3 3 (9번, dp[6][2])
1) 3) 5, 6, 7
   4) 6, 7
   5) 7
2) 4) 6, 7, 8
   5) 7, 8
   6) 8
3) 5) 7, 8
   6) 8
4) 6) 8
dp[8][4] = 2


dp[9][2] = dp[8][2] + 7 = 27
1) 3, 4, 5, 6, 7 (+ 8)
2) 4, 5, 6, 7, 8 (+ 9)
3) 5, 6, 7, 8 (+ 9)
4) 6, 7, 8 (+ 9)
5) 7, 8 (+ 9)
6) 8 (+ 9)
7) (+ 9)

dp[9][3] = dp[8][3] + dp[7][2] = 16 + 14 = 30
1 1 1 1 1 + 2 2 2 2 + 3 3 3 + 4 4 (14번, dp[7][2])
1) 3) 5, 6, 7 (+ 8)
   4) 6, 7 (+ 8)
   5) 7 (+ 8)
   6) (+ 8)
2) 4) 6, 7, 8 (+ 9)
   5) 7, 8 (+ 9)
   6) 8 (+ 9)
   7) (+ 9)
3) 5) 7, 8 (+ 9)
   6) 8 (+ 9)
   7) (+ 9)
4) 6) 8 (+ 9)
   7) (+ 9)
5) 7) (+ 9)


dp[i][1] = 1
dp[i][2] = dp[i - 1][j] + dp[i - 2][j - 1]
dp[i][3] = dp[i - 1][j] + dp[i - 2][j - 1]
dp[i][j] = dp[i - 1][j] + dp[i - 2][j - 1]



***/
