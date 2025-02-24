import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 집의 크기 N(3 ≤ N ≤ 16)
        map = new int[N][N];
            
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                // 집의 상태
                // 빈 칸은 0, 벽은 1
                // (1, 1)과 (1, 2)는 항상 빈 칸 0
                map[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }

        // 초기화 - 첫 위치
        int[][][] dp = new int[N][N][3];
        dp[0][1][0] = 1;
        

        // 파이프 이동
        for (int i = 0; i < N; i++) {
            
            for (int j = 1; j < N; j++) {
                // 벽이면 이동 불가
                if (map[i][j] == 1) continue;
                
                // 가로(0) 이동
                // 이전에 가로(0), 대각선(2)이었을 경우에만 현재 가로로 이동 가능
                dp[i][j][0] += dp[i][j - 1][0];
                if (i > 0) {
                    dp[i][j][0] += dp[i][j - 1][2];
                }
                    
                // 세로(1) 이동
                // 이전에 세로(1), 대각선(2)이었을 경우에만 현재 세로로 이동 가능
                if (i > 0) {
                    dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
                }

                // 대각선(2) 이동
                // 이전에 가로(0), 세로(1), 대각선(2)이었을 경우에만 현재 대각선으로 이동 가능
                // i - 1 > 0
                // i > 1
                if (i > 0 && map[i - 1][j] != 1 && map[i][j - 1] != 1) {
                    dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }

                // System.out.printf("dp[%d][%d][%d] = %d\n", i, j, 0, dp[i][j][0]);
                // System.out.printf("dp[%d][%d][%d] = %d\n", i, j, 1, dp[i][j][1]);
                // System.out.printf("dp[%d][%d][%d] = %d\n", i, j, 2, dp[i][j][2]);
                // System.out.println();
            }
        }

        // 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력
        // 이동시킬 수 없는 경우에는 0을 출력
        int result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(result);
    }
}

// 가장 처음에 파이프는 (0, 0)와 (0, 1)를 차지하고 있고, 방향은 가로
// 가장 처음 꼭짓점 위치: (0, 1)

// 파이프의 면적중 map이 1이면 해당 위치로 이동 불가

// type == 0(가로), 이전에 가로(0), 대각선(2)이었을 경우에만 현재 가로로 이동 가능
// type == 1(세로), 이전에 세로(1), 대각선(2)이었을 경우에만 현재 세로로 이동 가능
// type == 2(대각선), 이전에 가로(0), 세로(1), 대각선(2)이었을 경우에만 현재 대각선으로 이동 가능

// type == 0(가로), (i, j - 1)
// type == 1(세로), (i - 1, j)
// type == 2(대각선), (i - 1, j - 1)

// dp[i][j][0]: 파이프의 꼭짓점이 (i, j) 위치이고, 가로(0)일 경우 해당 위치로 올 수 있는 경우의 수
// dp[i][j][1]: 파이프의 꼭짓점이 (i, j) 위치이고, 세로(1)일 경우 해당 위치로 올 수 있는 경우의 수
// dp[i][j][2]: 파이프의 꼭짓점이 (i, j) 위치이고, 대각선(2)일 경우 해당 위치로 올 수 있는 경우의 수

// 초기화 - 첫 위치
// Arrays.fill(dp, -1) => 이동 불가능 상태
// dp[0][1][0] = 1 

// 가로(0)로 이동하려면, map[i][j] != 1
// 세로(1)로 이동하려면, map[i][j] != 1
// 대각선(2)으로 이동하려면, map[i][j] != 1 && map[i - 1][j] != 1 && map[i][j - 1] != 1
// dp[0][2][0] = dp[0][1 - 1][0] + dp[0 - 1][2 - 1][2] = 1
