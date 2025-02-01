import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, M, K;
    static List<List<Node>> graph = new ArrayList<>();
    static long[] dist;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 노드 수 (2 ≤ N ≤ 100,000)
        M = Integer.parseInt(st.nextToken()); // 간선 수 (1 ≤ M ≤ 500,000)
        K = Integer.parseInt(st.nextToken()); // 면접장 수 (1 ≤ K ≤ N)
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 역방향 그래프 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // (1 ≤ C ≤ 100,000)
            
            // 단방향 간선
            // u -> v 대신 v -> u로 저장
            graph.get(v).add(new Node(u, c));
        }
        
        // 도시 기준으로 각 면접장까지의 최단거리 구하기
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        // 면접장 위치 입력
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        while (st.hasMoreTokens()) {
            int interviewCity = Integer.parseInt(st.nextToken()); // 면접장 위치
            dist[interviewCity] = 0;
            pq.offer(new Node(interviewCity, 0));
        }
        
        // 다중 시작점 다익스트라 실행
        dijkstra(pq);
        
        // 가장 먼 도시와 그 거리 도출
        // 최악의 경로 거리: (N - 1) * c = 99999 * 100,000 = 9,999,900,000
        int maxSpot = -1;
        long maxDist = -1;
        for (int i = 1; i <= N; i++) {
            if (maxDist < dist[i] && dist[i] != Long.MAX_VALUE) {
                maxSpot = i;
                maxDist = dist[i];
            }
        }

        System.out.println(maxSpot);
        System.out.println(maxDist);
    }
    
    // 다중 시작점 다익스트라
    static void dijkstra(PriorityQueue<Node> pq) {
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (current.w > dist[current.v]) continue;
            
            for (Node neighbor : graph.get(current.v)) {
                long newDist = neighbor.w + dist[current.v];
                
                if (newDist < dist[neighbor.v]) {
                    dist[neighbor.v] = newDist;
                    pq.offer(new Node(neighbor.v, newDist));
                }
            }
        }
    }
}

// 단방향 간선이므로 면접장부터 도시가 아닌, 도시부터 면접장까지의 거리 도출해야됨
// 도시 기준으로 각 면접장까지의 거리 구하기
// dist[i]: 도시i와 면접장까지의 거리

// 2 -> 5(1) -> 1(4)

class Node {
    int v;
    long w;
    
    public Node(int v, long w) {
        this.v = v;
        this.w = w;
    }
}
