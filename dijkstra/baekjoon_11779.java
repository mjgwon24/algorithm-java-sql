import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, startIdx, targetIdx;
    static List<Bus>[] graph;
    static List<Integer> results = new ArrayList<>();

    static class Bus {
        int v;
        int w;
        List<Integer> path;

        public Bus(int v, int w, List<Integer> path) {
            this.v = v;
            this.w = w;
            this.path = path;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 출발 도시 번호
            int b = Integer.parseInt(st.nextToken()); // 도착지 도시 번호
            int c = Integer.parseInt(st.nextToken()); // 버스 비용

            // 단방향
            graph[a].add(new Bus(b, c, new ArrayList<>()));
        }

        // 구하고자 하는 출발점 도시 번호, 도착점 도시 번호
        st = new StringTokenizer(br.readLine());
        startIdx = Integer.parseInt(st.nextToken());
        targetIdx = Integer.parseInt(st.nextToken());

        Bus resultBus = dijkstra();

        // 출력1. 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
        System.out.println(resultBus.w);

        // 출력2. 최소 비용을 갖는 경로에 포함된 도시 개수 (출발 도시, 도착 도시 포함)
        System.out.println(resultBus.path.size() + 1);
        
        // 출력3. 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력 (경로 여러개일 경우, 아무거나)
        System.out.print(startIdx + " ");
        for (int n: resultBus.path) {
            System.out.print(n + " ");
        }
    }

    static Bus dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        pq.add(new Bus(startIdx, 0, new ArrayList<>()));
        dist[startIdx] = 0;

        while (!pq.isEmpty()){
            Bus cur = pq.poll();

            if (cur.v == targetIdx) {
                return cur;
            }

            for (Bus next: graph[cur.v]) {
                int newDist = dist[cur.v] + next.w;

                if (newDist >= dist[next.v]) continue;

                dist[next.v] = newDist;
                List<Integer> temp = new ArrayList<>(cur.path);
                temp.add(next.v);
                pq.add(new Bus(next.v, newDist, temp));
            }
        }

        return null;
    }
}
