import java.lang.*;
import java.util.*;

class Solution {
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                            {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    static class Node {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int N = grid.length;
        if (grid[0][0] == 1 || grid[N - 1][N - 1] == 1) return -1;

        // 최단 경로 도출 - BFS
        return dijkstra(N, grid);
    }

    static int dijkstra(int N, int[][] grid) {
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(0, 0, 1));

        int[][] dist = new int[N][N];
        for (int[] d: dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = 1;

        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            
            for (int[] mv: move) {
                int nx = cur.x + mv[0];
                int ny = cur.y + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || grid[nx][ny] == 1) continue;

                int newDist = dist[cur.x][cur.y] + 1;

                if (dist[nx][ny] <= newDist) continue;

                dist[nx][ny] = newDist;
                dq.add(new Node(nx, ny, newDist));
            }
        }

        return dist[N - 1][N - 1] == Integer.MAX_VALUE ? -1 : dist[N - 1][N - 1];
    }
}

/*
n x n 이진 행렬 그리드가 주어졌을 때, 행렬에서 가장 짧은 명확한 경로의 길이를 반환합니다.
명확한 경로가 없으면 -1을 반환합니다.

명확한 경로: 왼쪽 상단 셀(즉, (0, 0)) -> 오른쪽 하단 셀(즉, (n - 1, n - 1))로 가는 경로이며, 다음과 같은 조건을 만족해야 합니다: 
1. 경로의 모든 방문 셀은 0입니다. 
2. 경로의 모든 인접 셀은 8방향으로 연결되어 있습니다(즉, 서로 다르고 가장자리나 코너를 공유합니다).
3. 명확한 경로의 길이는 이 경로의 방문한 셀의 수입니다.


*/
