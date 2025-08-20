import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static int[] startInfo = new int[3];
    static int[] endInfo = new int[3];
    // 방향: 동 0, 서 1, 남 2, 북 3
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int answer = Integer.MAX_VALUE;
    static int[][] canDirSwitch = {{2, 3}, // 0
                                   {2, 3}, // 1
                                   {0, 1}, // 2
                                   {0, 1}}; // 3
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 길이 100
        M = Integer.parseInt(st.nextToken()); // 가로 길이 100
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < M; j++) {
                // 0 갈수있음, 1 갈수없음
                map[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }

        st = new StringTokenizer(br.readLine());
        // 출발 지점 위치, 바라보는 방향(1-idx -> 0-idx)
        for (int i = 0; i < 3; i++) {
            startInfo[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        // 도착 지점 위치, 바라보는 방향(1-idx -> 0-idx)
        for (int i = 0; i < 3; i++) {
            endInfo[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        bfs();

        // 로봇을 도착 지점에 원하는 방향으로 이동시키는데 필요한 최소 명령 횟수 출력
        System.out.println(answer);
    }

    static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        pq.add(new int[]{startInfo[0], startInfo[1], startInfo[2], 0});
        
        int[][][] dist = new int[N][M][4];
        for (int[][] dis : dist) 
            for (int[] ds : dis) 
                Arrays.fill(ds, Integer.MAX_VALUE);
        dist[startInfo[0]][startInfo[1]][startInfo[2]] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            int c = cur[3];
            // System.out.printf("\n\n시작점 x : %d, y : %d, d : %d, c : %d\n", x, y, d, c);

            if (x == endInfo[0] && y == endInfo[1] && d == endInfo[2]) {
                answer = Math.min(answer, c);
                continue;
            }

            // 앞으로 갈 수 있는 횟수 1, 2, 3
            int nx = x;
            int ny = y;
            for (int i = 1; i <= 3; i++) {
                nx += dir[d][0];
                ny += dir[d][1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 1) {
                    // System.out.println("범위 이탈 또는 다음 경로는 1");
                    break;
                }
                if (dist[nx][ny][d] <= c + 1) {
                    // System.out.printf("더 적은 메모제이션 존재 (dist[%d][%d][%d] = %d\n", nx, ny, d, dist[nx][ny][d]);
                    continue;
                }
                
                // System.out.printf("앞으로 nx : %d, ny : %d, d : %d, c : %d\n", nx, ny, d, c + 1);
                dist[nx][ny][d] = c + 1;
                pq.add(new int[]{nx, ny, d, c + 1});
            }

            // 방향 전환 가능 순서1 : 0 2 1 3 0 ...
            // 방향 전환 가능 순서2 : 0 3 1 2 0 ...
            for (int nextDir : canDirSwitch[d]) {
                if (dist[x][y][nextDir] <= c + 1) continue;

                // System.out.printf("방향 전환 x : %d, y : %d, nextDir : %d, c : %d\n", x, y, nextDir, c + 1);
                
                dist[x][y][nextDir] = c + 1;
                pq.add(new int[]{x, y, nextDir, c + 1});
            }
        }
    }
}
