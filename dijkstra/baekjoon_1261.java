import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            
            for (int j = 0; j < M; j++) {
                // 0 빈 방, 1 벽
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        // for (int[] mm: map) {
        //     for (int n: mm)
        //         System.out.print(n + " ");
        //     System.out.println();
        // }
        // System.out.println();


        // (N - 1, M - 1) 에 도달하기 위해 벽을 최소 몇 개 부수어야 하는지 출력
        System.out.println(dijkstra());
    }

    static int dijkstra() {
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n[2]));
        pq.add(new int[]{0, 0, 0}); // x, y, cnt
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2]; // 벽을 부순 횟수

            // 도착 - 바로 반환(가장 빨리 도착한 경우임)
            if (x == N - 1 && y == M - 1) return cnt;

            for (int[] mv: move) {
                int nx = x + mv[0];
                int ny = y + mv[1];
                int temp = cnt;

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                
                // 벽일 경우, 벽을 부순다
                if (map[nx][ny] == 1) {
                    temp++;
                }

                pq.add(new int[]{nx, ny, temp});
                visited[nx][ny] = true;
            }
        }

        return 0;
    }
}

/***
최단 경로 도출



***/
