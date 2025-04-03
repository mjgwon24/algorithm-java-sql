import java.lang.*;
import java.util.*;

class Solution {
    static List<Integer>[] graph;
    
    static class Node {
        int v;
        long w;
        
        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] arr: edge) {
            int a = arr[0];
            int b = arr[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        long[] dist = dijkstra(n);
        int answer = 0;
        long maxDist = 0;
        
        for (long l: dist) {
            if (maxDist < l && l != Long.MAX_VALUE) maxDist = l;
        }
        // System.out.println(maxDist);
        
        for (long l: dist) {
            if (maxDist == l) answer++;
        }
        
        // 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return
        return answer;
    }
    
    static long[] dijkstra(int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(nn -> nn.w));
        pq.add(new Node(1, 0)); // vertex, weight
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Integer next: graph[cur.v]) {
                long newDist = dist[cur.v] + 1;
                
                if (newDist >= dist[next]) continue;
                
                pq.add(new Node(next, newDist));
                dist[next] = newDist;
            }
        }
        
        return dist;
    }
}

/***
int n: 노드의 개수 2 이상 20,000 이하
int[][] edge: 간선에 대한 정보 1개 이상 50,000개 이하
    • [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미 (가중치 1)

• 양방향
• 거리 long


[ ex ]
n = 6
[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	
return = 3

***/
