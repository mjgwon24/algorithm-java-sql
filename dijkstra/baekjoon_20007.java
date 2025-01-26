import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, M, X, Y;
    static List<List<Node>> graph = new ArrayList<>();
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        X = Integer.parseInt(st.nextToken()); // 하루 최대 가능 거리 (-1)
        Y = Integer.parseInt(st.nextToken()); // 현재 위치 노드
        
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        
        // 1. 시작 위치로부터 각 노드별 최단거리 dist 배열에 저장 (본인은 0)
        int[] dist = dijkstra();
        
        // 방문 불가능한 집 확인
        Arrays.sort(dist); // dist = [0, 3, 5, 10, 10]
        if (dist[N - 1] * 2 > X) {
            System.out.println(-1);
            return;
        }
        
        // 2. 최소 날짜 계산
        System.out.println(calDay(dist));
    }
    
    // 2. 최소 날짜 계산
    static int calDay(int[] dist) {
        int days = 1;
        int dailyDist = 0;
        
        for (int i = 0; i < N; i++) {
            // dist = [0, 3, 5, 10, 10]
            if (dailyDist + dist[i] * 2 > X) {
                days++;
                dailyDist = 0;
            }
            
            dailyDist += dist[i] * 2;
            // days 1, dailyDist = 6
            // days 1, dailyDist = 16
            // days 2, dailyDist = 20
            // days 3, dailyDist = 20
        }
        
        return days;
    }
    
    // 1. 시작 위치로부터 각 노드별 최단거리 dist 배열에 저장 (본인은 0)
    static int[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt
        (node -> node.w));
        pq.offer(new Node(Y, 0));
        
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[Y] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.w > dist[now.v]) continue;
            
            for (Node neighbor : graph.get(now.v)) {
                if (neighbor.w > dist[neighbor.v]) continue;
                
                int newDist = dist[now.v] + neighbor.w;
                
                if (newDist < dist[neighbor.v]) {
                    dist[neighbor.v] = newDist;
                    pq.offer(new Node(neighbor.v, newDist));
                }
            }
        }
        
        return dist;
    }
}

class Node {
    int v;
    int w;
    
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

// 1. 시작 위치로부터 각 노드별 최단거리 dist 배열에 저장 (본인은 0)
//     dist = [0, 5, 3, 10, 10]
// 2. 최소 날짜 계산
