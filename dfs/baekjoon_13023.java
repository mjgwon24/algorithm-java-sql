import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int n, m;
    static List<Integer>[] relationGraph;
    static boolean found = false; // depth가 5가 되는 관게 찾으면 true, 1 출력
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 1. n, m 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relationGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            relationGraph[i] = new ArrayList<>();
        }

        // 2. 친구관계 그래프 입력받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationGraph[a].add(b);
            relationGraph[b].add(a);
        }

        // 3. 사람마다 dfs 탐색
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            dfs(i, 0);
            if (found) break;
        }

        System.out.println(found ? 1 : 0);
    }

    /**
     * dfs 탐색 함수
     */
    static void dfs(int node, int depth) {
        // depth가 4면 found
        if (depth == 4) {
            found = true;
            return;
        }

        // 방문 체크
        visited[node] = true;

        // 해당 node의 친구 탐색
        for (int neighbor : relationGraph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, depth + 1);
            }
            if (found) return;
        }

        // 방문 닫기
        visited[node] = false;
    }
}
