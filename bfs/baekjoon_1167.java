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
            int node = sc.nextInt();

            while (true) {
                int to = sc.nextInt();
                if (to == -1) break;

                int weight = sc.nextInt();
                graph[node].add(new int[] {to, weight});
            }
        }

        int[] firstBfs = bfs(1);
        int farthestEdge = firstBfs[0];

        int[] secondBfs = bfs(farthestEdge);
        int farthestDistance = secondBfs[1];

        System.out.println(farthestDistance);
    }

    static int[] bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];
        int farthestEdge = 1;
        int farthestDistance = 0;

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();

            for (int[] next : graph[nowNode]) {
                int nextEdge = next[0];
                int nextWeight = next[1];

                if (!visited[nextEdge]) {
                    visited[nextEdge] = true;
                    queue.add(nextEdge);
                    distance[nextEdge] = distance[nowNode] + nextWeight;

                    if (distance[nextEdge] > farthestDistance) {
                        farthestEdge = nextEdge;
                        farthestDistance = distance[nextEdge];
                    }
                }
            }
        }

        return new int[] {farthestEdge, farthestDistance};
    }
}

// 1 -(2)- 3 -(3)- 4 -(6)- 5
