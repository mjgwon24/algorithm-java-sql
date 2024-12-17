import java.util.*;

class Main {
    static int[][] dp = new int[41][2];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        dp[0] = new int[] {1, 0};
        dp[1] = new int[] {0, 1};

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }
    }
}

// f(0) = 0, dp[0][] = {1, 0}
// f(1) = 1, dp[1][] = {0, 1}
// f(2) = f(1) + f(0) = 1 0, dp[2][] = dp[0][] + dp[1][] = {1, 1}
// f(3) = f(2) + f(1) = (1 0) 1, dp[3][] = dp[2][] + dp[1][] = {1, 2}
// f(4) = f(3) + f(2) = (1 0 1) (1 0), dp[4][] = dp[3][] + dp[2][] = {2, 3}
