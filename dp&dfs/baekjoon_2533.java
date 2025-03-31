import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static List<Integer>[] graph;
    static int minResult = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 친구 관계 정점 개수 2 ≤ N ≤ 1,000,000
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        StringTokenizer st;

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        // 아이디어를 전파하는데 필요한 얼리 아답터의 최소 수
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int currentIdx) {
        visited[currentIdx] = true;

        // dp 초기화
        dp[currentIdx][0] = 0; // 현재 node가 얼리어답터가 아닌 경우, 최소 얼리어답터 수
        dp[currentIdx][1] = 1; // 현재 node가 얼리어답터인 경우, 최소 얼리어답터 수

        for (int neighbor : graph[currentIdx]) {
            if (!visited[neighbor]) {
                dfs(neighbor);

                // 현재 node가 얼리어답터가 아닌 경우, 이웃은 반드시 얼리어답터
                dp[currentIdx][0] += dp[neighbor][1];

                // 현재 node가 얼리어답터인 경우, 이웃은 얼리어답터일 수도 있고 아닐수도 있음
                dp[currentIdx][1] += Math.min(dp[neighbor][1], dp[neighbor][0]);
            }
        }
    }
}

/*
DFS + DP
• dp[node][0]: 현재 node가 얼리어답터가 아닌 경우, 최소 얼리어답터 수
    -> 얼리 아답터가 아닌 사람들: 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들임
• dp[node][1]: 현재 node가 얼리어답터인 경우, 최소 얼리어답터 수
    -> 얼리어답터: 어떤 새로운 아이디어를 먼저 받아들인 사람
[case1]
8
1 2
1 3
1 4
2 5
2 6
4 7
4 8
return: 3

1 (얼리)
|---2 (얼리)
    |---5
    ㄴ---6
|---3
|---4 (얼리)
    |---7
    |---8
-> 얼리어답터 3명

1 (얼리)
|---2
    |---5 (얼리)
    ㄴ---6 (얼리)
|---3
|---4
    |---7 (얼리)
    |---8 (얼리)
-> 얼리어답터 5명

1
|---2 (얼리)
    |---5
    ㄴ---6
|---3 (얼리)
|---4 (얼리)
    |---7
    |---8
-> 얼리어답터 3명

[case2]
9
1 2
1 3
2 4
3 5
3 6
4 7
4 8
4 9
return 3

1 (얼리)
|---2
    |---4 (얼리)
        |---7
        |---8
        |---9
|---3 (얼리)
    |---5
    |---6
-> 얼리 3명

1 (얼리)
|---2
    |---4 (얼리)
        |---7
        |---8
        |---9
|---3
    |---5 (얼리)
    |---6 (얼리)
-> 얼리 4명

1
|---2 (얼리)
    |---4
        |---7 (얼리)
        |---8 (얼리)
        |---9 (얼리)
|---3 (얼리)
    |---5
    |---6
-> 얼리 5명

[ solution ]
1. 현재 node를 얼리어답터로 지정 시, 이웃된 node는 얼리어답터로 지정할 수도 있고 안할수도 있음
2. 현재 node를 얼리어답터로 지정하지 않을 시, 이웃된 node는 반드시 얼리어답터로 지정해야 함


 */
