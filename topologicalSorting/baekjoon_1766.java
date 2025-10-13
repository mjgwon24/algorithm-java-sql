import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] pairs;
    static int[] depth;
    static List<Integer>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제 수 32,000
        M = Integer.parseInt(st.nextToken()); // 문제 정보 수 100,000
        pairs = new int[M][2];
        depth = new int[N + 1];
        
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // A
            int b = Integer.parseInt(st.nextToken()); // B
            
            depth[b]++;
            graph[a].add(b);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        
        // depth가 0인 문제 우선 삽입
        for (int i = 1; i <= N; i++) {
            if (depth[i] == 0) pq.add(i); // 3, 4
        }
        
        // 순서가 있는 문제는 순서대로
        // 순서가 없다면 오름차순 정렬
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int cur = pq.poll(); // 3
            
            if (depth[cur] == 0) sb.append(cur).append(" "); // 3
            
            for (int neighbor : graph[cur]) { // 1
                depth[neighbor]--;
                
                if (depth[neighbor] == 0) pq.add(neighbor);
            }
        }

        // 문제 번호를 나타내는 1이상 N 이하의 정수들
        // 풀어야하는 순서대로 출력
        System.out.println(sb.toString());
    }
}
