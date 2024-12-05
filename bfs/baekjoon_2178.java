import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        bfs(0, 0);

        System.out.println(map[N-1][M-1]);
    }

    static void bfs(int i, int j) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {i, j});
        visited[i][j] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (map[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        map[x][y] = map[now[0]][now[1]] + 1; // depth update
                        que.offer(new int[] {x, y});
                    }
                }

            }
        }
        
    }
}
