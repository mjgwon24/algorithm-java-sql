import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, M;
    static int[][] map;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<int[]> virusPositions = new ArrayList<>();
    static boolean[] selected;
    static int emptyCnt;
    static int minTime = Integer.MAX_VALUE;
    static boolean success = false;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 연구소 크기 (가로, 세로)
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    emptyCnt++;
                    virusPositions.add(new int[]{i, j});
                } else if (map[i][j] == 0) emptyCnt++;
            }
        }
        
        emptyCnt -= M;
        selected = new boolean[virusPositions.size()];
        
        combination(0, 0);
        System.out.println(success == true ? minTime : -1);
    }
    
    // 2. bfs 탐색
    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int filled = 0; // 바이러스가 채운 빈 칸 수
        int time = 0; // 이동하는데 걸린 시간
        
        // 선택된 바이러스 위치와 시간을 큐에 추가
        for (int i = 0; i < virusPositions.size(); i++) {
            if (selected[i]) {
                int[] virus = virusPositions.get(i);
                q.offer(new int[]{virus[0], virus[1], 0});
                visited[virus[0]][virus[1]] = true;
            }
        }
        
        // 탐색
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int t = now[2];
            
            if (time < t) time = t;
            if (time > minTime) break;
            
            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];
                
                if (nx < 0|| ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;
                
                // 이동
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, t + 1});
                filled++;
            }
        }
        
        if (emptyCnt == filled) {
            minTime = Math.min(minTime, time);
            success = true;
        }
    }
    
    // 1. M개의 바이러스를 고를 수 있는 조합 탐색
    static void combination(int depth, int start) {
        if (depth == M) {
            bfs();
            return;
        }
        
        for (int i = start; i < virusPositions.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                combination(depth + 1, i + 1);
                selected[i] = false;
            }
        }
    }
}

// 1. M개의 바이러스를 고를 수 있는 조합 탐색
// 2. bfs 탐색
