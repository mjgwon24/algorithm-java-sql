import java.util.*;

public class Main {
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // n x m 크기 이차원 영역
        int n = sc.nextInt();
        int m = sc.nextInt();

        grid = new int[n][m]; // 이동 가능 1, 이동 불가 0
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();


        
        // 뱀에게 물리지 않고 탈출 가능한 경로가 있는지 여부 판별
        System.out.println(bfs(n, m) ? 1 : 0); // 가능 1, 불가능 0
    }

    // 탈출 여부 판별
    static boolean bfs(int N, int M) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == N - 1 && y == M - 1) return true;

            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || grid[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return false;
    }
}
