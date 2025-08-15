import java.util.*;

public class Main {
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] grid;
    static boolean[][] visited;
    static Set<String> answerSet = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n x n 격자
        int k = sc.nextInt(); // 시작점 개수

        grid = new int[n][n]; // 이동 가능 0, 이동 불가 1
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // 각 시작점의 위치 (1-index -> 0-index)
        visited = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            bfs(x, y, n);
        }

        // 시작점으로부터 방문 가능한 서로 다른 칸 수 출력
        System.out.println(answerSet.size());
    }

    // 시작점으로부터 방문 가능한 칸 도출
    static void bfs(int startX, int startY, int N) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        StringBuilder sb = new StringBuilder();
        sb.append(startX).append(",").append(startY);
        answerSet.add(sb.toString());

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || grid[nx][ny] == 1) continue;

                sb = new StringBuilder();
                sb.append(nx).append(",").append(ny);
                answerSet.add(sb.toString());
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
