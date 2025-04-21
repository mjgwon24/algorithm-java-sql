import java.lang.*;
import java.util.*;

class Solution {
    static double answer;
    static List<Node>[] graph;
    
    static class Node implements Comparable<Node> {
        int v;
        double w;

        public Node(int v, double w) {
            this.v = v;
            this.w = w;
        }

        // 내림차순 정렬
        @Override
        public int compareTo(Node n) {
            // return (int) (n.w - this.w); -> 시간 초과
            return Double.compare(n.w, this.w);
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < succProb.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double c = succProb[i];

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        // 1. 최고 거리 dist 도출
        double[] dist = dijkstra(n, start_node);

        // for (double d: dist) System.out.print(d + " ");

        return dist[end_node];
    }

    // 최고거리 도출
    static double[] dijkstra(int n, int start) {
        double[] dist = new double[n];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 1.0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // System.out.printf("현재 pq에서 나온 node: %d, weight: %f\n", cur.v, cur.w);

            // 저장된 기존 거리가 더 크다면, 패스
            // if (dist[cur.v] != 0 && dist[cur.v] > cur.w) {
            //     System.out.printf("dist[%d]: %f >= %f 충족하여 패스.\n", cur.v, dist[cur.v], cur.w);
            //     continue;
            // }

            // System.out.println("진행!");

            for (Node next: graph[cur.v]) {
                double newDist = cur.w * next.w;

                // 새로운 거리가 기존 거리보다 작을 경우, 탐색하지 않음
                if (newDist <= dist[next.v]) {
                    // System.out.println("새로운 거리가 기존 거리보다 작을 경우, 탐색하지 않음");
                    // System.out.printf("newDist: %f <= dist[%d] = %f 충족.\n", newDist, next.v, dist[next.v]);
                    continue;
                }

                pq.add(new Node(next.v, newDist));
                dist[next.v] = newDist;
            }
        }

        return dist;
    }
}

/*
n개의 노드(0부터 시작)로 구성된 양방향 가중 그래프가 주어집니다. 
이는 edges[i] = [a, b]와 같이 노드 a와 b를 연결하고, 해당 간선을 따라갈 성공 확률이 succProb[i]인 간선 목록으로 표현됩니다.
시작 노드와 끝 노드가 주어졌을 때, 시작부터 끝까지 최대 성공 확률을 가지는 경로를 찾아 그 성공 확률을 반환하세요.
시작에서 끝까지의 경로가 없다면 0을 반환하세요. 
답이 올바른 답과 1e-5 이하로 차이가 난다면 승인됩니다.

[ex]
Input: 
n = 3, 
edges = [[0,1],[1,2],[0,2]], 
succProb = [0.5,0.5,0.2], 
start = 0, 
end = 2
Output: 0.25000
시작점에서 종료점까지 두 경로가 있으며, 
하나는 성공 확률이 0.2이고 다른 하나는 0.5 * 0.5 = 0.25입니다.

*/
