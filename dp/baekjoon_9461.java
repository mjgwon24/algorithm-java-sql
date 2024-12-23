import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(p(N));
        }
    }

    static long p(int n) {
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            if (i <= 3) dp[i] = 1;
            else if (i > 3 && i <= 5) dp[i] = 2;
            else dp[i] = dp[i-1] + dp[i-5];
        }

        return dp[n];
    }
}

// p(1) = 1
// p(2) = 1 (흰)
// p(3) = 1

// p4 = p3 + p1 = 2
// p5 = p4 + p0= 2


// p6 = p5 + p1 = 3
// p7 = p6 + p2 = 4
// p8 = p7 + p3 = 5
// p9 = p8 + p4 = 7
// p10 = p9 + p5 = 9
// p11 = p10 + p6 = 12
// p12 = p11 + p7 = 12 + 4

// dp[i] = dp[i-1] + dp[i-5]
