import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Main.solution();
    }
    
    static int n;
    static int[][] map, dp;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int result;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 대나무 숲 크기 (1 ≤ n ≤ 500)
        map = new int[n][n];
        dp = new int[n][n];
        
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 대나무 양 (<= 10^7)
            }
        }
        
        // (25 * 10^4)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, canMoveCnt(i, j));
            }
        }
        
        System.out.println(result);
    }
    
    // 이동 가능 칸 수 도출 
    static int canMoveCnt(int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];
        
        dp[x][y] = 1;
        
        for (int[] mv : move) {
            int nx = x + mv[0];
            int ny = y + mv[1];
                
            if (nx < 0|| ny < 0 || nx >= n || ny >= n) continue;
            if (map[nx][ny] <= map[x][y]) continue;
                
            if (dp[nx][ny] != 0) dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
            else dp[x][y] = Math.max(dp[x][y], canMoveCnt(nx, ny) + 1);
        }
        
        return dp[x][y];
    }
}

// 판다가 이동할 수 있는 칸의 수의 최댓값 도출
// 이동 조건: 현재 위치 대나무 개수 < 이동 위치 대나무 개수
// 시작 지점은 자유
// 시간제한: 2 * 10^8 회 연산 가능

// 가장 작은 값부터 탐색하는게 유리
// 최적화: 현재 최댓값(result) 이후의 인덱스는 탐색 필요 없음

// dp[i][j]: (i, j) 인덱스에서 시작했을 때, 이동할 수 있는 최댓값
// 초기값 1
// 반복적으로 일어나는 연산을 dp로 메모제이션

// canMoveCnt(0,0)
// dp[0][0] = 1
// return 1

// canMoveCnt(0,1) = 3
// dp[0][1] = max(dp[0][1], canMoveCnt(1, 1) + 1) = 3
// dp[0][1] = max(dp[0][1], canMoveCnt(0, 2) + 1) = 3
// canMoveCnt(1, 1) = 2
// dp[1][1] = max(dp[1][1], canMoveCnt(2, 1) + 1) = 2
// canMoveCnt(2, 1) = 1
// dp[2][1] = 1

// canMoveCnt(0, 2)
// dp[0][2] = 1
