import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        if (N >= 2) {
            dp[2] = 1;
        }
        if (N >= 3) {
            dp[3] = 1;
        }

        for (int i = 4; i <= N; i++) {
            if (i % 3 == 0 && dp[i / 3] != Integer.MAX_VALUE) {
                dp[i] = dp[i / 3] + 1;
            }

            if (i % 2 == 0 && dp[i / 2] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            if (dp[i - 1] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}

// N = 2
// N / 2 = 1 (1)

// N = 10
// N - 1 = 9 (1)
// N / 3 = 3 (2)
// N / 3 = 1 (3)
