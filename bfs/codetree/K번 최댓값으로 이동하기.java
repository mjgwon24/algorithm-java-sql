import java.util.*;

public class Main {
    static int answerX, answerY;
    static int startX, startY;
    static int N, K;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // N x N 격자
        K = sc.nextInt(); // 이동을 반복하는 횟수

        grid = new int[N][N]; // 해당 칸의 정수값
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = sc.nextInt();
        
        // 초기 시작 위치 (1-index -> 0-index)
        startX = sc.nextInt() - 1;
        startY = sc.nextInt() - 1;

        bfs();

        // 이동을 K번 반복한 후 위치를 공백으로 구분하여 출력
        System.out.println(answerX + " " + answerY);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0}); // x, y, cnt
        int lastX = startX;
        int lastY = startY;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];
            // System.out.printf("(%d, %d) 시작\n", x, y);
            
            lastX = x;
            lastY = y;

            if (cnt == K) {
                break;
            }

            Map<Integer, List<int[]>> nextPositionsMap = new HashMap<>();
            boolean[][] visited = new boolean[N][N];
            visited[x][y] = true;
            int maxValue = dfs(x, y, grid[x][y], 0, nextPositionsMap, visited);

            // 후보 리스트 정렬
            // 4. 행 번호가 같은 경우가 여러개일 경우, 열 번호가 가장 작은 곳으로 이동
            List<int[]> nextPositions = nextPositionsMap.getOrDefault(maxValue, new ArrayList<>());
            // System.out.println("4. 다음 이동 후보 리스트: ");
            // for (int[] nn : nextPositions) System.out.println(nn[0] + ", " + nn[1]);
            nextPositions.sort((a, b) -> {
                // 3. 다음 이동 후보가 여러개일 경우, 행(row) 번호가 가장 작은 곳으로 이동
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            });

            // 이동
            if (nextPositions.size() == 0 || nextPositions.isEmpty()) continue;
            int[] next = nextPositions.get(0);
            // System.out.printf("다음 이동: (%d, %d)\n", next[0], next[1]);
            q.add(new int[]{next[0], next[1], cnt + 1});
        }

        answerX = lastX + 1;
        answerY = lastY + 1;
    }

    // 이동 가능한 구간의 map 채우고, 최댓값 반환
    static int dfs(int x, int y, int originValue, int maxValue, Map<Integer, List<int[]>> nextPositionsMap, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= N || y >= N) return maxValue;

        for (int[] dr : dir) {
            int nx = x + dr[0];
            int ny = y + dr[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
            
            int nextValue = grid[nx][ny];
            if (originValue <= nextValue) continue;

            if (maxValue < nextValue) maxValue = nextValue;

            List<int[]> nextPositions = nextPositionsMap.getOrDefault(nextValue, new ArrayList<>());
            nextPositions.add(new int[]{nx, ny});
            nextPositionsMap.put(nextValue, nextPositions);
            // System.out.println("nextPositionsMap.size() = " + nextPositionsMap.size());
            visited[nx][ny] = true;
            
            maxValue = Math.max(maxValue, dfs(nx, ny, originValue, maxValue, nextPositionsMap, visited));
        }

        return maxValue;
    }
}
