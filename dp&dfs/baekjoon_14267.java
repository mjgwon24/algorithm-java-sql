import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] upperMan;
    static int[] dp;
    // static boolean[] visited;
    static List<Integer>[] compliments;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 회사 직원 수 (2 ≤ n, m ≤ 100,000)
        M = Integer.parseInt(st.nextToken()); // 최초의 칭찬의 횟수 (2 ≤ n, m ≤ 100,000)
        
        upperMan = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            upperMan[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            // upperMan[-1] = 1, 사장
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) n = 0;
            upperMan[n].add(i); // 상사 n의 부하는 i
        }
        
        compliments = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            compliments[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 칭찬을 받은 직원 번호 (2 ≤ n ≤ N, 1 ≤ w ≤ 1,000)
            int w = Integer.parseInt(st.nextToken()); // 칭찬 수치 (2 ≤ n ≤ N, 1 ≤ w ≤ 1,000)
            compliments[n].add(w); // n번 직원이 w 만큼 칭찬받음
        }
        
        dp = new int[N + 1];
        dfs(1, 0, 0);
        
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    static void dfs(int start, int prev, int depth) {
        // 사장 - 칭찬 안받음
        if (prev == 0) {
            dp[start] = 0;
            // 부하 탐색
            for (int next : upperMan[start]) {
                dfs(next, start, depth + 1); 
            }
            return;
        }
        
        if (!compliments[start].isEmpty()) {
            // 칭찬 받음
            // dp[i] = dp[i의 상사] + compliments[i]
            dp[start] = dp[prev];
            for (int compliment : compliments[start]) {
                dp[start] += compliment;
            }
            for (int next : upperMan[start]) {
                dfs(next, start, depth + 1); // 부하 탐색
            }
        } else {
            // 칭찬 받지 않음
            // -> dp[i] = dp[i의 상사]
            dp[start] = dp[prev];
            for (int next : upperMan[start]) {
                dfs(next, start, depth + 1);
            }
        }
    }
}

// 상사에게는 부하가 한명이 아닐수도 있다? 직속 부하라고 했으니 한명 아닌가?

// 사장(1 고정)부터 내려가면서 칭찬 누적합
// dfs(1) -> 사장이 칭찬 받았는가? 사장의 직속 부하로 내려가 탐색
// |-------dfs(2) -> 2번이 칭찬 받았는가? 받았다면 아래 직원들 모두 칭찬
//          |---------dfs(3) -> 
//         dp[2] = 2
// dp[1] = 0

// dp[i]: i 직원이 칭찬을 받은 양

// i 직원이 칭찬받지 않음
// -> dp[i] = dp[i의 상사]
// i 직원이 칭찬을 받음
// -> dp[i] = dp[i의 상사] + compliments[i]

// 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬
// 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력

// 1 사장
// 2 -> 1 상사
// 3 -> 1
// 4 -> 3
// 5 -> 4

// 2 => 2
// 3 => 2 4
// 5 => 2 4 6
