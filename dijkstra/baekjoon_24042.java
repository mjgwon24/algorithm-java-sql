import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Node {
        int v;
        long w;

        Node (int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
    
    static int N, M;
    static int[] A;
    static int[] B;
    static List<Node>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지역 수
        M = Integer.parseInt(st.nextToken()); // 횡단보도 주기

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (long i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향
            graph[a].add(new Node(b, i));
            graph[b].add(new Node(a, i));
        }

        // 1번 지역에서 N번 지역까지 가는데 필요한 최소 시간 분단위 출력
        System.out.println(dijkstra());
    }

    // 1 -> N
    static long dijkstra() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        pq.add(new Node(1, 0L));
        dist[1] = 0;
        int currentTime = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.w > dist[cur.v]) continue;

            for (Node next: graph[cur.v]) {
                long init = (cur.w / M);
                if (init * M + next.w < cur.w) init++;
                
                long newTime = init * M + next.w + 1;
                
                // System.out.printf("\n도착지: %d, 예상 도착 시간: %d\n", next.v, newTime);

                if (newTime >= dist[next.v]) {
                    // System.out.println("저장된 시간보다 많음. 건너뜀.");
                    continue;
                }

                pq.add(new Node(next.v, newTime));
                dist[next.v] = newTime;
            }
        }

        // for (int n: dist) System.out.print(n + " ");
        // System.out.println();
        
        return dist[N];
    }
}
