import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int MOD = 1000000000;
    static int N;
    static int K;
    static int[][] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        dp = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for (int i = 1; i <= K; i++) {
            dp[1][i] = i;
        }

        for(int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
        
    }
}

// dp[2][2] ~ dp[N][K]
// dp[N][K] = dp[N - 1][K] + dp[N][K - 1]
