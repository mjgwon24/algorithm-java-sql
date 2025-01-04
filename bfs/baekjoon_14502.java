import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static int[][] copy;
    static int[][] move = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    static int maxSafeArea;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0);
        System.out.println(maxSafeArea);
    }

    // 1. 벽 세우기
    static void buildWall(int count) {
        // 벽을 다 세운 경우 
        if (count == 3) {
            // 바이러스 퍼뜨리기
            virus();
            return;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 빈 칸일 경우 벽을 세움 
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    buildWall(count + 1);
                    map[i][j] = 0; // 백트래킹 
                }
            }
        }
    }

    // 2. 바이러스 퍼뜨리기
    static void virus() {
        copy = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            System.arraycopy(map[i], 1, copy[i], 1, M);
        }

        // 바이러스 위치 저장 
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (copy[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] m : move) {
                int nx = x + m[0];
                int ny = y + m[1];

                if (nx <= 0 || ny <= 0 || nx > N || ny > M) continue;

                // 바이러스 확산
                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // 확산이 완료되면 안전 영역 크기 계산 
        safezone();
    }

    // 3. 안전 영역 크기 계산
    static void safezone() {
        int safeArea = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (copy[i][j] == 0) safeArea++;
            }
        }

        maxSafeArea = Math.max(safeArea, maxSafeArea);
    }
}

// 벽 3개 무조건 세우기 
// 1. 벽 3개를 모든 위치마다 모두 다르게 세우기
// 2. 바이러스 퍼뜨리기
// 3. 안전 영역(0) 크기 계산
// 4. 최댓값 출력 
