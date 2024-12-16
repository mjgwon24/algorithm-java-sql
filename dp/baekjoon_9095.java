import java.util.*;

class Main {
    public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] dp = new int[n + 1];

            dp[1] = 1;
            if (n > 1) dp[2] = 2;
            if (n > 2) dp[3] = 4;

            for (int j = 4; j <= n; j++) {
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }

            System.out.println(dp[n]);
        }
    }
}

// dp[1] = 1
// 1

// dp[2] = 2
// 1 1, 2

// dp[3] = 4
// 1 1 1, 1 2, 2 1, 3

// dp[4] = 7
// 1 1 1 1, 1 2 1, 2 1 1, 3 1, 1 1 2, 2 2, 1 3
// 4 = 3 + 1 = 2 + 2 = 1 + 3
// dp[3] + dp[2] + dp[1]

// dp[5] = 13
// (4) 1 1 1 1 1, 1 2 1 1, 2 1 1 1, 3 1 1, 1 1 2 1, 2 2 1, 1 3 1
// (3) 1 1 1 2, 1 2 2, 2 1 2, 3 2
// (2) 1 1 3, 2 3
// 5 = 4 + 1 = 3 + 2 = 2 + 3
// dp[4] + dp[3] + dp[2]

// dp[6] = dp[5] + dp[4] + dp[3]
// 6 = 5 + 1 = 4 + 2 = 3 + 3

// dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
