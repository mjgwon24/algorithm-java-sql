import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, startIdx, targetIdx;
    static List<Bus>[] graph;

    static class Bus {
        int v, w;
        public Bus(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Bus(b, c));
        }

        st = new StringTokenizer(br.readLine());
        startIdx = Integer.parseInt(st.nextToken());
        targetIdx = Integer.parseInt(st.nextToken());

        dijkstra();
    }

    static void dijkstra() {
        int[] dist = new int[N + 1];
        int[] prev = new int[N + 1]; // 경로 복원용, 이전 도시 저장.
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1); // 이전 도시 -1로 초기화 (경로의 시작 표시)

        PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        pq.add(new Bus(startIdx, 0));
        dist[startIdx] = 0;

        while (!pq.isEmpty()) {
            Bus cur = pq.poll();

            // 도착 도시 도달시 종료 (최단거리 찾음)
            if (cur.v == targetIdx) break;

            // 이미 더 짧은 경로로 방문한 경우 스킵
            // if (dist[cur.v] < cur.w) continue;

            for (Bus next: graph[cur.v]) {
                int newDist = dist[cur.v] + next.w;

                if (newDist >= dist[next.v]) continue;

                dist[next.v] = newDist;
                prev[next.v] = cur.v; // 경로 복원용 이전 도시 기록
                pq.add(new Bus(next.v, newDist));
            }
        }
        
        // 경로 복원 - prev[]를 따라가며 역추적
        List<Integer> path = new ArrayList<>();
        int cur = targetIdx; // 2
        while (cur != -1) {
            path.add(cur);
            cur = prev[cur]; // 1
        }
        Collections.reverse(path); // 경로 역순에서 정순으로 변경

        // 최소 비용
        System.out.println(dist[targetIdx]);

        // 최소 비용 경로 포함되어있는 도시 개수
        System.out.println(path.size());

        // 경로 추적
        for (int n: path) {
            System.out.print(n + " ");
        }
    }
}
