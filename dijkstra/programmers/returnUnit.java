import java.lang.*;
import java.util.*;

class Solution {
    static List<Integer>[] graph;
    
    static class Node {
        int v;
        int w;
        
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // destination -> sources
        int[] dist = dijkstra(n, destination);
        int[] answer = new int[sources.length];
        
        // Long.MAX_VALUE면 -1로 변경
        int i = 0;
        for (int s: sources) {
            answer[i++] = dist[s] == Integer.MAX_VALUE ? -1 : dist[s];
        }
        
        // 주어진 sources의 원소 순서대로
        // 강철부대로 복귀할 수 있는 최단시간을 담은 배열
        // 복귀가 불가능한 경우 해당 부대원의 최단시간은 -1
        return answer;
    }
    
    // destination -> sources
    static int[] dijkstra(int n, int destination) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(nn -> nn.w));
        pq.add(new Node(destination, 0));
        dist[destination] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Integer next: graph[cur.v]) {
                int newDist = dist[cur.v] + 1;
                
                if (newDist >= dist[next]) continue;
                
                pq.add(new Node(next, newDist));
                dist[next] = newDist;
            }
        }
        
        return dist;
    }
}

/***
int n 강철부대가 위치한 지역을 포함한 총지역의 수 (3 ≤ n ≤ 100,000)
int[][] roads 두 지역을 왕복할 수 있는 길 정보 (2 ≤ roads의 길이 ≤ 500,000)
    • 두 지역 간의 길을 통과하는 데 걸리는 시간은 모두 1
    • [a, b] 형태로 두 지역 a, b가 서로 왕복할 수 있음을 의미
int[] sources 각 부대원이 위치한 서로 다른 지역들을 나타내는 정수 배열 (1 ≤ sources의 길이 ≤ 500)
int destination 강철부대의 지역

• weight -> long
• 임무의 시작 때와 다르게 되돌아오는 경로가 없어짐 ???????? 안없어지는데....

[ sol1 ]
n = 3
roads [[1, 2], [2, 3]]	
sources [2, 3]	
destination 1
result [1, 2]

sources[0] = 2, destination = 1 => 2에서 1로 가야함
2 -> 1
result[0] = 1

sources[1] = 3, destination = 1 => 3에서 1로 가야함
3 -> 2 -> 1
result[1] = 2




[ sol2 ]
n = 5
roads [[1, 2], [1, 4], [2, 4], [2, 5], [4, 5]]	
sources [1, 3, 5]	
destination 5
result [2, -1, 0]

srources[0] = 1, destination = 5 => 1에서 5로 가야함
1 -> 2 -> 5 or 1 -> 4 -> 5
result[0] = 2

srources[1] = 3, destination = 5 => 3에서 5로 가야함
3 -> x
result[1] = -1

srources[1] = 3, destination = 5 => 3에서 5로 가야함
3 -> x
result[1] = -1


***/
