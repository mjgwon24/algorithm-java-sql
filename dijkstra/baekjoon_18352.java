import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K, X; // 도시 개수 N, 도로 개수 M, 거리 정보 K, 출발 도시 번호 X
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        // 1. 출발 도시로부터 다른 도시로 가는 최단 경로 dist[] 배열 구하기 - queue 사용해 BFS로 탐색
        int[] dist = dijkstra(X);

        // 2. dist[] 배열에서 최단 거리 값 K를 가진 index 출력 
        List<Integer> results = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) results.add(i);
        }

        if (results.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int n : results) System.out.println(n);
        }
    }

    static int[] dijkstra(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        // 거리 배열 선언 및 초기화(거리 max로 초기화) 
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 연결 정점 탐색
            for (int neighbor : graph.get(now)) {
                // 새로운 거리(가중치) 계산
                int newDist = dist[now] + 1;

                // 새로운 거리(가중치)가 더 짧다면, dist 갱신 
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    queue.offer(neighbor);
                }
            }
        }

        return dist;
    }
}

// 모든 도로의 거리(가중치)는 1
// 최단 거리가 정확히 K인 모든 도시의 번호 출력
// 출발도시: X
// 1. 출발 도시로부터 다른 도시로 가는 최단 경로 dist[] 배열 구하기 - pq 사용해 BFS로 탐색
// 2. dist[] 배열에서 최단 거리 값 K를 가진 index 출력 

// List<List<Integer>> graph = ArrayList<>()
// graph[1] = 2, 3
// graph[2] = 3, 4
