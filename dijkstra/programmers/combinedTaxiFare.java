import java.lang.*;
import java.util.*;

class Solution {
    static List<Node>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // graph 초기화 (양방향)
        for (int[] fare: fares) {
            int aa = fare[0];
            int bb = fare[1];
            int cc = fare[2];
            
            graph[aa].add(new Node(bb, cc));
            graph[bb].add(new Node(aa, cc));
        }
        
        // 1. Adist(start 6)
        int[] Adist = dijkstra(a, n);
        
        for (int nn: Adist) {
            System.out.print(nn + " ");
        }
        System.out.println();
        
        // 2. Bdist(start 2)
        int[] Bdist = dijkstra(b, n);
        for (int nn: Bdist) {
            System.out.print(nn + " ");
        }
        System.out.println();
        
        // 3. Sdist(start 4)
        int[] Sdist = dijkstra(s, n);
        for (int nn: Sdist) {
            System.out.print(nn + " ");
        }
        System.out.println();
        
        // 4. 최소 금액 도출
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (Adist[i] == Integer.MAX_VALUE || Bdist[i] == Integer.MAX_VALUE || Sdist[i] == Integer.MAX_VALUE) continue;
            
            answer = Math.min(answer, Adist[i] + Bdist[i] + Sdist[i]);
        }
        
        
        return answer;
    }
    
//     1번까지 동승일 경우,
// Sdist[1] + Adist[1] + Bdist[1] = 10 + 25 + 63

// 5번까지 동승일 경우,
// Sdist[5] + Adist[5] + Bdist[5] = 34 + 2 + 46
    
    static int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingInt(node -> node.w));
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            // if (current.w > dist[current.v]) continue;
            
            for (Node next: graph[current.v]) {
                int newDist = current.w + next.w;
                
                if (newDist < dist[next.v]) {
                    dist[next.v] = newDist;
                    pq.offer(new Node(next.v, newDist));
                }
            }
        }
        
        return dist;
    }
    
    static class Node {
        int v;
        int w;
        
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

/***
[ 요구사항 ]
최저 예상 택시요금을 계산해서 return

[ 조건 ]
• A, B 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정
• 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면, 합승을 하지 않아도 됨

[ 입력 ]
int n: 지점의 개수 (3 이상 200 이하)
int s: 출발지점
int a: A의 도착지점
int b: B의 도착지점
int[][] fares: 지점 사이의 예상 택시요금
    • [c, d, f] 형태
    • c지점과 d지점(1 이상 n 이하인 자연수) 사이의 예상 택시요금이 f원(1 이상 100,000 이하)
    • 양방향

[ 도출 과정 ]
fares = 
[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]
n = 6, s = 4, a = 6, b = 2, result = 82

1. 4→1→5 (A, B)
    요금 = 10 + 24 = 34
2. 5→6 (A 혼자 도착지점 a까지)
    요금 = 2
3. 5→3→2 (B 혼자 도착지점 b까지)
    요금 = 24 + 22 = 46
4. 합 = 34 + 2 + 46 = 82
    

[ solution ]
PriorityQueue 사용, 최단경로
dist[]: 출발지로부터 모든 지점까지. 각 목적지별 최소 소요 금액.
1. Adist(start 6)
Adist[1] = 25, Adist[4] = 35, Adist[5] = 2
2. Bdist(start 2)
Bdist[1] = 63, Bdist[3] = 22, Bdist[4] = 66, Bdist[5] = 46
3. Sdist(start 4)
Sdist[1] = 10, Sdist[2] = 66, Sdist[3] = 51, Sdist[5] = 34, Sdist[6] = 35
4. 최소 금액 도출
minV

1번까지 동승일 경우,
Sdist[1] + Adist[1] + Bdist[1] = 10 + 25 + 63

5번까지 동승일 경우,
Sdist[5] + Adist[5] + Bdist[5] = 34 + 2 + 46


***/
