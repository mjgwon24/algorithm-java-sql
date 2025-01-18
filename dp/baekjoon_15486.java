import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int[] T;
    static int[] P;
    static int[] dp;
    
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]);
            
            if (i + T[i] - 1 > N) continue;
            dp[i + T[i] - 1] = Math.max(dp[i + T[i] - 1], dp[i - 1] + P[i]);
        }
        
        int cnt = 0;
        int max = dp[1];
        for (int n : dp) {
            if (max < n) max = n;
            // System.out.printf("dp[%d] = %d\n", cnt, dp[cnt++]);
        }
        System.out.println(max);
    }
}

// 상담을 했을 때 얻을수있는 최대 수익 도출
// dp[i]: i일째 얻을 수 있는 최대 수익
// 1. i일자 날짜의 상담을 진행했을 경우
// dp[i + T[i] - 1] = dp[i - 1] + P[i]
// 2. i일자 날짜의 상담을 진행하지 않았을 경우
// dp[i] = max(dp[i - 1], dp[i])

// 1일 소요는 당일에 끝남. N일 초과 날짜에 끝나는 상담은 진행 불가능
// i = 1 
// 상담 진행, dp[3] = 10
// 상담 x, dp[1] = 0
// i = 2
// 상담 진행, dp[6] = dp[1] + 20 = 20
// 상담 x, dp[2] = dp[1] = 0
// i = 3
// 상담 진행, dp[3] = dp[2] + 10 = 10
// 상담 x, dp[3] = dp[2], dp[3] = 10
// i = 4
// 상담 진행, dp[4] = dp[3] + 20 = 30
// 상담 x, dp[4] = 30
// i = 5
// 상담 진행, dp[6] = max(dp[4] + 15, dp[6]) = 45
// 상담 x, 
// i = 6
// 상담 진행, dp[..] 초과
