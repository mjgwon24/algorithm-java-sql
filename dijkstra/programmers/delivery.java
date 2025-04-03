import java.lang.*;
import java.util.*;

class Solution {
    static List<Node>[] graph;
    
    static class Node {
        int v, w;
        
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        // graph 초기화
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] arr: road) {
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            
            // 양방향
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        // for (int i = 1; i <= N; i++) {
        //     for (Node n: graph[i]) {
        //         System.out.print(n.v + ", " + n.w + " | ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
        int[] dist = dijkstra(N, K);
        int answer = 0;
        
        for (int n: dist) {
            // System.out.print(n + " ");
            if (n <= K) answer++;
        }

        // 음식 주문을 받을 수 있는 마을의 개수를 return
        return answer;
    }
    
    static int[] dijkstra(int N, int K) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        pq.add(new Node(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Node next: graph[cur.v]) {
                int newDist = dist[cur.v] + next.w;
                
                if (dist[next.v] < newDist) continue;
                if (newDist > K) continue;
                
                pq.add(new Node(next.v, newDist));
                dist[next.v] = newDist;
            }
        }
        
        return dist;
    }
}

/***
int N 마을의 개수 1 이상 50 이하
int[][] road 각 마을을 연결하는 도로의 정보 1 이상 2,000 이하
    • a, b(1 ≤ a, b ≤ N, a != b)는 도로가 연결하는 두 마을의 번호
    • c(1 ≤ c ≤ 10,000, c는 자연수)는 도로를 지나는데 걸리는 시간
int K 음식 배달이 가능한 시간 1 이상 500,000 이하

• 각 마을은 양방향으로 통행할 수 있는 도로로 연결
• 1번 마을에 있는 음식점에서 각 마을로 음식 배달
• K 시간 이하로 배달이 가능한 마을에서만 주문받음

[ 자료형, 알고리즘 ]
PriorityQueue<Node>
최단 시간 - 모든 경우 탐색 -> BFS

[ ex ]
N = 5, K = 3
road
[[1,2,1],
[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]	
result = 4


***/
