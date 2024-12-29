import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[][] move = {
        {-1, 0}, {1, 0},
        {0, 1}, {0, -1}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        dp = new int[M+1][N+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dp[M][N] = 1;
        System.out.println(dfs(1, 1));
    }

    static int dfs(int y, int x) {
        if (y == M && x == N) {
            return dp[y][x];
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0; // 탐색중 상태로 변경
            
        for (int i = 0; i < 4; i++) {
            int nextY = y + move[i][0];
            int nextX = x + move[i][1];
                
            if (nextY <= 0 || nextY > M || nextX <= 0 || nextX > N) continue;
            if (map[y][x] > map[nextY][nextX]) {
                dp[y][x] += dfs(nextY, nextX);
                // System.out.printf("dp[%d][%d] = %d\n", y, x, dp[y][x]);
            }
        }

        return dp[y][x];
    }
}

// 1. 상태 정의
// dp[y][x]: 지점(y, x)에서 끝 지점(m-1, n-1)까지 갈 수 있는 경로의 개수
// dp[y][x] = -1: 아직 탐색하지 않은 상태
// dp[y][x] = 0: 탐색중, 끝 지점까지 갈 수 있는 경로가 0개
// dp[y][x] = k: 끝 지점까지 갈 수 있는 경로가 k개

// 2. 선택지 정의/도출
// 현재 지점에서 상,하,좌,우 이동 가능 (이동할 좌표: ny, nx)
// dp[y][x] = dp[ny][nx]의 합
// 2.1 이미 dp[ny][nx]가 계산되어있는 경우, 메모제이션을 이용해 값을 바로 더하기
// 2.2 아직 dp[ny][nx]가 계산되지 않은 경우, 재귀적으로 탐색(DFS)하여 값 갱신 후 더하기.

// 3. 초기화
// 목적지에서 출발하는 경우는 경우의 수가 1
// dp[M][N] = 1
// 탐색하지 않은 지점은 -1로 초기화
// dp[y][x] = -1 (y < M, x < N)
