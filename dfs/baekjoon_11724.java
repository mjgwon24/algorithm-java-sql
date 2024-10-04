import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    // graph, visited Array
    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static boolean[] visited;
    public static int count = 0;
    
    public static void main(String[] args) throws IOException {
        // 1. input n, m
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];

        // 2. input graph Array
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.get(node1).add(node2);
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node2).add(node1);
        }

        // 3. dfs run
        int i = 1;
        while (i != 0) {
            count++;
            dfs(i);
            
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    i = j;
                    break;
                }
                 i = 0;
            }
        }

        System.out.print(count);
    }

    // dfs function
    public static void dfs(int node) {
        visited[node] = true;
        
        List<Integer> neighbors = graph.get(node);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
              if (!visited[neighbor]) {
                dfs(neighbor);
              }
          }
        }
    }
} 
