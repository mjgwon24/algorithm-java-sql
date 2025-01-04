import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static int[][][] dist;
    static int[][] move = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1][2];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 1, 0});
        dist[1][1][0] = 1; // 시작 지점 1부터 거리 매김

        while (!queue.isEmpty()) {
            int[] nowPosition = queue.poll();
            int nowX = nowPosition[0];
            int nowY = nowPosition[1];
            int moveYn = nowPosition[2];

            // 목표위치에 도착했을 경우
            if (nowX == N && nowY == M) {
                return dist[nowX][nowY][moveYn];
            }

            // 아직 도달 전이라면 이동
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + move[i][0];
                int nextY = nowY + move[i][1];

                if (nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) continue;

                // 이동 가능한 경우 (이미 간 곳은 다시 가지 않음)
                if (map[nextX][nextY] == 0 && dist[nextX][nextY][moveYn] == 0) {
                    dist[nextX][nextY][moveYn] = dist[nowX][nowY][moveYn] + 1;
                    queue.offer(new int[]{nextX, nextY, moveYn});
                }

                // 벽을 만난 경우 (이미 부순 경우라면 이동 불가) (이미 벽을 한번 부순 뒤 간 곳은 다시 가지 않음)
                if (map[nextX][nextY] == 1 && moveYn == 0 && dist[nextX][nextY][1] == 0) {
                    dist[nextX][nextY][1] = dist[nowX][nowY][0] + 1;
                    queue.offer(new int[]{nextX, nextY, 1});
                }
            }
        }

        // 상단 if문을 통해 목표 지점 도달 return을 만나지 못했을 경우
        // 즉 도달하지 못하고 큐가 전부 비워진 경우 -> 도달 불가
        return -1;
    }
}

// 0은 이동가능, 1은 벽, 1을 한번 0으로 만들 수 있음
// (1,1) -> (N, M) 도달 불가능하면 -1 출력
// 벽을 한번 부수고 이동하는 경우와 한번도 부수지 않으며 이동하는 경우 모두 고려
// bfs(Queue<int[]>) 사용
// 벽 부숨 여부 판단 방법.. 큐에 break값도 함께 전달? 
// moveYn0: 아직안부숨, moveYn1: 부숨(벽 만나면 이동 불가) 
