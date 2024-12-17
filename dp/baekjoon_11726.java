import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int mod = 10007;
    static int[] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n+1];

        dp[1] = 1;
        if (n > 1) {
            dp[2] = 2;
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] = dp[i] % mod;
        }

        System.out.println(dp[n]);
    }
}

// n = 1 (1x2)
// 1x2 1
// dp[1] = 1

// n = 2 (2x2)
// 2x1 2x1
// 1x2 1x2
// dp[2] = 2

// n = 3 (3x2)
// (2x1 2x1) 1x2
// (1x2 1x2) 1x2
// 1x2 (2x1 2x1)
// dp[3] = 3 = dp[2] + dp[1]

// n = 4 (4x2)
// { (2x1 2x1) 1x2 } 1x2
// { 1x2 1x2 1x2 } 1x2
// { 1x2 (2x1 2x1) } 1x2
// (2x1 2x1) (2x1 2x1)
// 1x2 1x2 (2x1 2x1)
// dp[4] = 5 = dp[3] + dp[2]
