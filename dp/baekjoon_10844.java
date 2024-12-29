import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int MOD = 1000000000;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= N; i++) {
            for (int j = 0; j<= 9; j++) {
                if (j == 0) dp[i][j] = dp[i-1][1] % MOD;
                if (j == 9) dp[i][j] = dp[i-1][8] % MOD;
                if (j >= 1 && j <= 8) dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
            }
        }

        long sum = 0;
        for (long n : dp[N]) {
            sum += n;
        }

        System.out.println(sum % MOD);
    }
}

// 1. 상태 정의
// dp[i][j]: 길이가 i인 계단 수 중에서 가장 마지막 자릿값이 j인 경우의 수

// 2. 선택지 정의 - 이전 상태와 연결
// 2.1 마지막 자릿값이 0 - 이전 자릿값이 1일 경우 가능
// 2.2 마지막 자릿값이 9 - 이전 자릿값이 8일 경우 가능
// 2.3 마지막 자릿값이 1~8 - 이전 자릿값은 현재 값 -1 또는 +1

// 3. 상태간 관계 도출
// 3.1 마지막 자릿값이 0
// dp[i][0] = dp[i-1][1]
// 3.2 마지막 자릿값이 9
// dp[i][9] = dp[i-1][8]
// 3.3 마지막 자릿값이 1~8
// dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] (j >= 1 && j <= 8)

// 4. 초기화
// dp[1][j] = 1 (j != 0)
// dp[1][0] = 0
