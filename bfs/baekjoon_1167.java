import java.util.*;

public class Main {
    static int N;
    static List<int[]>[] graph;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int inputNode = sc.nextInt();

            while (true) {
                int to = sc.nextInt();
                if (to == -1) break;

                int weight = sc.nextInt();
                graph[inputNode].add(new int[] {to, weight});
            }
        }

        int[] firstBfs = bfs(1);
        int farthestNode = firstBfs[0];

        int[] secondBfs = bfs(farthestNode);
        int farthestDistance = secondBfs[1];

        System.out.println(farthestDistance);
    }

    static int[] bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];

        queue.add(start);
        visited[start] = true;

        int farthestNode = start;
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int[] next : graph[now]) {
                int e = next[0];
                int v = next[1];

                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now] + v;

                    if (distance[e] > maxDistance) {
                        maxDistance = distance[e];
                        farthestNode = e;
                    }
                }
            }
        }

        return new int[] {farthestNode, maxDistance};
    }
} 
