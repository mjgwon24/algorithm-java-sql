import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, X;
    static List<Node>[] graph;
    static List<Node>[] oppositGraph;
    static int[] times;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수 (1 ≤ N ≤ 1,000)
        M = Integer.parseInt(st.nextToken()); // 단방향 도로 개수 (1 ≤ M ≤ 10,000)
        X = Integer.parseInt(st.nextToken()); // 파티 마을 (1 ≤ X ≤ N)
        times = new int[N + 1];
        // Arrays.fill(times, Integer.MAX_VALUE);

        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        oppositGraph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            oppositGraph[i] = new ArrayList<>();
        }

        // i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // (1 ≤ Ti ≤ 100)

            // 정방향
            graph[a].add(new Node(b, c));

            // 역방향
            oppositGraph[b].add(new Node(a, c));
        }

        dijkstra(oppositGraph);
        dijkstra(graph);
        
        //  N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간
        int maxT = 0;
        for (int n : times) {
            if (maxT < n && n != Integer.MAX_VALUE) maxT = n;
        }
        System.out.println(maxT);
    }

    // 1. 역방향 X -> start
    // 2. 정방향 X -> start
    static void dijkstra(List<Node>[] currentGraph) {
        int[] tempTimes = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.offer(new Node(X, 0));
        visited[X] = true;
        Arrays.fill(tempTimes, Integer.MAX_VALUE);
        tempTimes[X] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : currentGraph[current.v]) {
                // if (visited[next.v]) continue;
                
                int newDist = current.w + next.w;
                
                if (tempTimes[next.v] > newDist) {
                    tempTimes[next.v] = newDist;
                    pq.offer(new Node(next.v, newDist));
                    visited[next.v] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            times[i] += tempTimes[i];
        }
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
N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간
start -> X -> start
목적지로 오고가는거 자체는 최단시간임.....

1. 역방향 X -> start
2. 정방향 X -> start
해당 학생의 총 소요시간 = 1, 2 합

1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

1. start -> X
3 -> 2 -> 3
times = 6 + (1 + 2) = 9



***/
