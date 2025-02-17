import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static List<Integer>[] graph;
    static int[] results;
    static boolean[] visited;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 노드 개수 (2 ≤ * ≤ 10ˆ5)
        
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // both directions
            graph[a].add(b);
            graph[b].add(a);
        }

        results = new int[N + 1];
        visited = new boolean[N + 1];
        dfs(1);

        for (int i = 2; i <= N; i++) {
             System.out.println(results[i]);
        }
    }

    static void dfs(int start) {
        visited[start] = true;
        
        for (int next : graph[start]) {
            if (!visited[next]) {
                results[next] = start;
                dfs(next);
            }
        }
    }
}

// root node: 1
// Print the parent node number of each node in order from Node 2

// [ graph ]
// 1
// |--6
//    |--3
//       |--5
// |--4
//    |--2
//    |--7

// dfs(1)
// |--dfs 6, results[6] = 1
//    |--dfs 3, results[3] = 6
//       |--5
// |--dfs 4, results[6] = 1
//    |--2
//    |--7


