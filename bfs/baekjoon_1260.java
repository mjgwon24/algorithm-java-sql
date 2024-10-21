import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class Main {
    static int n, m, v;
    static List<Integer>[] graph;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        // 1. input number of vertices n, number of edge m, start vertex number v
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        // 2. graph initialization
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 3. input graph key, value
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        // sort ascending order
        for (int i = 1; i <= n; i++) {
             Collections.sort(graph[i]);   
        }

        // 4. dfs search
        visited = new boolean[n+1];
        dfs(v);
        
        System.out.println();


        // 5. bfs search
        visited = new boolean[n+1];
        bfs(v);
    }

    // dfs search
    static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) dfs(neighbor);
        }
        
    }

    // bfs search
    static void bfs(int start) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.offer(start);
        visited[start] = true;
        
        while (!bfsQueue.isEmpty()) {
            int outNode = bfsQueue.poll();
            System.out.print(outNode + " ");

            for (int neighbor : graph[outNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    bfsQueue.offer(neighbor);
                }
            }   
        }
    }
} 
