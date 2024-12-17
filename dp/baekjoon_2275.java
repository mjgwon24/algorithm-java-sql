import java.util.*;

class Main {
    static int[][] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        dp = new int[15][15];
        
        for (int i = 1; i <= 14; i++) {
            dp[0][i] = i;
            dp[i][1] = 1;
        }

        for (int i = 1; i <= 14; i ++) {
            for (int j = 2; j <= 14; j++) {
                for (int k = 1; k <= j; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }

        for (int i = 0; i < t; i++) {
            int k = sc.nextInt();
            int n = sc.nextInt();

            System.out.println(dp[k][n]);
        }
    }
}

// k = 1, n = 3

// dp[0][1] = 1, dp[0][2] = 2, dp[0][3] = 3

// dp[1][1] = 1 = dp[0][1] 
// dp[1][2] = 3 = dp[0][1] + dp[0][2]
// dp[1][3] = 6 = dp[0][1] + dp[0][2] + dp[0][3]

// dp[2][1] = 1 = dp[1][1] 
// dp[2][2] = 4 = dp[1][1] + dp[1][2]
// dp[2][3] = 10 = dp[1][1] + dp[1][2] + dp[1][3]
