import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 1 <= N <= 90
        long[][] dp = new long[N + 1][2];

        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }
}


// dp[i][j]: 자릿수가 i이고 뒷자리 숫자가 j일 때, 만들수 있는 이친수의 개수
// j = 0, 뒷자리가 0으로 끝남
// j = 1, 뒷자리가 1로 끝남

// 1. 현재 뒷자리가 0으로 끝나는 경우
// dp[i][0] = dp[i-1][0] + dp[i-1][1]
// 2. 현재 뒷자리가 1으로 끝나는 경우
// dp[i][1] = dp[i-1][0]

// dp[1][1] = 1
// 1

// dp[2][0] = dp[1][1] = 1
// 10

// dp[3][0] = dp[2][0] = 1
// 100
// dp[3][1] = dp[2][0] = 1
// 101

// dp[4][0] = dp[3][0] + dp[3][1] = 2
// 1000, 1010
// dp[4][1] = dp[3][0] = 1
// 1001

// dp[5][0] = dp[4][0] + dp[4][1] = 3
// 10000,10100, 10010 
// dp[5][1] = dp[4][0] = 2
// 10001, 10101
