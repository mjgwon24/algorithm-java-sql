import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, E; // 정점 개수 V, 간선 개수 E 
    static int K; // 시작 정점의 번호 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        // 그래프를 인접 리스트로 표현
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 정점1
            int v = Integer.parseInt(st.nextToken()); // 정점2
            int w = Integer.parseInt(st.nextToken()); // 가중치
            graph.get(u).add(new Node(v, w)); // u에서 v로가는 가중치w인 간선 존재
        }

        // 다익스트라 알고리즘 실행
        int[] dist = dijkstra(V, K, graph);
        
        // 결과 출력
        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }

    }

    // BFS와 유사한듯..
    static int[] dijkstra(int V, int start, List<List<Node>> graph) {
        // ?????
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt
                                                     (node -> node.weight));
        int[] dist  = new int[V + 1]; // 각 정점의 최단 거리 저장 
        Arrays.fill(dist, Integer.MAX_VALUE); // 처음에는 갈 수 없는 상태(무한대)로 초기화 

        dist[start] = 0; // 시작점 거리 초기화(시작 정점은 최단 거리 0으로 초기화 - 시작점에서 시작점까지의 거리는 항상 0)
        pq.offer(new Node(start, 0)); // 우선순위 큐에 시작점 넣기 

        while (!pq.isEmpty()) {
            Node current = pq.poll(); // 가장 짧은 경로를 먼저 탐색 

            // 이미 더 짧은 거리로 방문한 정점은 처리할 필요 없으므로 무시
            // 현재 큐에서 꺼낸 정점의 가중치 > dist[현재 큐에서 꺼낸 정점] => 탐색할 필요 없음 
            if (current.weight > dist[current.vertex]) continue;

            // 연결된 정점 탐색 
            for (Node neighbor : graph.get(current.vertex)) {
                // 이웃 정점까지의 거리 = 현재 정점까지의 최단 거리 + 현재 정점에서 이웃 정점까지의 가중치 
                // 현재 위치를 거쳐 이웃 정점으로 가는 거리 계산 
                int newDist = dist[current.vertex] + neighbor.weight;

                // 새로 계산된 거리(newDist)가 더 짧으면, dist 배열을 업데이트하고 큐에 추가
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.offer(new Node(neighbor.vertex, newDist));
                }
            }
        }

        return dist;
    }
}

// 정점과 가중치를 저장하는 노드 클래스 
class Node {
    int vertex; // 정점 
    int weight; // 가중치

    Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
