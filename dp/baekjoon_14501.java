import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N ;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if (i + T[i] - 1 <= N) dp[i + T[i] - 1] = Math.max(dp[i - 1] + P[i], dp[i + T[i] - 1]);
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        int max = dp[1];
        for (int n : dp) {
            if (max < n) max = n;
        }

        System.out.println(max);
    }
}

// 1. 상태 정의
// dp[k]: k일째까지 탐색했을 때 최대 상담 금액.

// 2. 선택지 정의 (현재 - 이전상태 관계 주의)
// 2.1 i일의 상담을 진행하는 경우. (상담을 진행한 후 날짜 기준 최대 금액  = (현재 날짜 - 1) 기준 최대 금액 + 현재 상담 진행 금액)
// dp[i + T[i] - 1] = max(dp[i - 1] + P[i], dp[i + T[i] - 1])
// 2.2 현재 날짜의 상담을 진행하지 않는 경우. (이전 날짜까지 탐색한 금액 or 존재하는 i일의 금액)
// dp[i] = max(dp[i-1], dp[i])

// i = 1,
// 상담을 진행하는 경우, dp[5] = dp[0] + P[1] = 50
// 상담을 진행하지 않는 경우, dp[1] = max(dp[0], dp[1]) = 0

// i = 2,
// 상담을 진행하는 경우, dp[5] = max(dp[1] + P[2], dp[5]) = max(50, 40)
// 상담을 진행하지 않는 경우, dp[2] = (dp[1], dp[2]) = 0
