import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, E, K;
    // graph[i]로 바로 접근 가능하므로 O(1) -> 조회속도 개선
    static ArrayList<Node>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점의 개수(1 ≤ V ≤ 20,000)
        E = Integer.parseInt(st.nextToken()); // 간선의 개수 (1 ≤ E ≤ 300,000)
        K = Integer.parseInt(br.readLine());  // 시작 정점의 번호

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // start node
            int v = Integer.parseInt(st.nextToken()); // end node
            int w = Integer.parseInt(st.nextToken()); // weight

            graph[u].add(new Node(v, w));
        }

        int[] result = dijkstra(K);

        // StringBuilder를 사용하여 모든 결과를 한 번에 출력 -> O(1)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(result[i] == Integer.MAX_VALUE ? "INF\n" : result[i] + "\n");
        }

        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (dist[current.v] < current.w) continue;
 
            for (Node neighbor : graph[current.v]) {
                int newDist = dist[current.v] + neighbor.w;
                
                if (newDist >= dist[neighbor.v]) continue;

                dist[neighbor.v] = newDist;
                pq.offer(new Node(neighbor.v, newDist));
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        // PriorityQueue 내부적으로 최적화된 정렬 방식 사용을 위한 오버라이드
        @Override
        public int compareTo(Node other) {
            return this.w - other.w;
        }
    }
}
