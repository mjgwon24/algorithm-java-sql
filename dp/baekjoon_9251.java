import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static char[] arr1;
    static char[] arr2;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int aLength = a.length();
        int bLength = b.length();
        arr1 = new char[aLength + 1];
        arr2 = new char[bLength + 1];
        dp = new int[aLength + 1][bLength + 1];

        for (int i = 1; i <= aLength; i++) {
            arr1[i] = a.charAt(i-1);
        }
        for (int i = 1; i <= bLength; i++) {
            arr2[i] = b.charAt(i-1);
        }

        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j <= bLength; j++) {
                if (arr1[i] == arr2[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                // System.out.printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
            }
        }

        int max = dp[aLength][1];

        for (int n : dp[aLength]) {
            if (max < n) max = n;
        }
        System.out.println(max);
    }
}

// dp[i][j] : arr1 i번째, arr2 j번째까지 탐색했을 때 같은 문자들의 길이
// 1. arr2에 같은 문자가 새로 발견된 경우.
// dp[i-1][j-1] + 1
// 2. arr2에 같은 문자가 발견되지 않은 경우.
// Math.max(dp[i][j-1], dp[i-1][j]);

// A CAPCAK
// dp[1][1] = 0
// dp[1][2] = 발견될 경우 dp[i-1][j-1] + 1 = 1
// dp[1][3] = 발견되지 않았을 경우 dp[i][j-1] = 1
// ...

// C CAPCAK
// dp[2][1] = 발견될 경우 dp[i-1][j-1] + 1 = dp[1][0] + 1 = 1
// dp[2][2] = 발견되지 않았을 경우 dp[i][j-1] = 1
// dp[2][3] = 1
// dp[2][4] = 발견될 경우 dp[i-1][j-1] + 1 = dp[1][3] + 1 = 2
// dp[2][5] = 2
// dp[2][6] = 2
