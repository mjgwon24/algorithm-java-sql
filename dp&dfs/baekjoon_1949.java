import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] residents;
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        residents = new int[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            residents[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        // 최대 주민 수 출력
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visited[node] = true; // 부모 노드로 재탐색 방지를 위한 방문 처리 (static)
        dp[node][0] = 0;      // 현재 노드를 포함하지 않는 경우
        dp[node][1] = residents[node]; // 현재 노드를 포함하는 경우

        for (int next : graph[node]) {
            // 아직 방문하지 않은 자식 노드만 탐색
            if (!visited[next]) {
                // 자식 노드(next)에 대해 재귀 호출 (서브트리 탐색)
                dfs(next);

                // 현재 노드를 포함하지 않는 경우:
                //   - 자식 노드를 포함할 수도 있고 안 할 수도 있음
                dp[node][0] += Math.max(dp[next][0], dp[next][1]); 

                // 현재 노드를 포함하는 경우:
                //   - 자식 노드는 포함하면 안 됨
                dp[node][1] += dp[next][0]; 
            }
        }
    }
}

// 점화식 도출
// 1. 상태 정의
// dp[i][0]: i번 노드를 선택하지 않았을 때, i번 노드를 루트로 하는 서브트리에서의 최대 주민 수
// dp[i][1]: i번 노드를 선택했을 때, i번 노드를 루트로 하는 서브트리에서의 최대 주민 수

// 2. 선택지 정리 - 부모와 자식간 관계 고려
// 2.1 i번 노드를 선택하지 않는 경우 (dp[i][0])
// 자식 노드들을 포함할 수도 있고, 포함하지 않을 수도 있음
// 각 자식 노드 next에 대해 dp[next][0] 또는 dp[next][1] 중 최댓값 선택
// 2.2 i번 노드를 선택하는 경우 (dp[i][1])
// 현재 노드를 선택했으므로, 자식 노드는 선택 불가
// 모든 자식 노드 next는 dp[next][0]만 가능

// 3. 관계 도출
// dp[i][0] = sum max(dp[next][0], dp[next][1])
// dp[i][1] = residents[i] + sum max (dp[next][0])

// 4. 초기값 설정
// dp[i][0] = 0
// dp[i][1] = residents[i]

// 100 200 300 500 100 400 600
// 1
// |-2
// | |-4
// | ㄴ-5
// ㄴ-3
//   |-6
//   ㄴ-7

// dfs(1)
// |---dfs(2)
//         |---dfs(4) -> dp[4] = (0, 500)
//         |---dfs(5) -> dp[5] = (0, 100)
//         L---dp[2] = (600, 200)
// |---dfs(3)
//         |---dfs(6) -> dp[6] = (0, 400)
//         |---dfs(7) -> dp[7] = (0, 600)
//         L---dp[3] = (1000, 300)
// L---dp[1] = (1600, 1700)
