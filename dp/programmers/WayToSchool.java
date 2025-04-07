import java.lang.*;
import java.util.*;

class Solution {
    static Long MOD = 1000000007L;
    static int[][] map;
    static long[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        map = new int[n][m];
        dp = new long[n][m];
        
        // 장애물 map에 지정
        for (int[] puddle: puddles) {
            int a = puddle[1] - 1;
            int b = puddle[0] - 1;
            
            map[a][b] = 1;
        }
        
        // for (int[] mm: map) {
        //     for (int mmm: mm) System.out.print(mmm);
        //     System.out.println();
        // }
        // System.out.println();
        
        dp[0][0] = 1;
        
        // DP + DFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) dp[i][j] = 0; // 장애물
                else {
                    if (i > 0) dp[i][j] += dp[i - 1][j] % MOD;
                    if (j > 0) dp[i][j] += dp[i][j - 1] % MOD;
                }
            }
        }
        
        // for (long[] ddd: dp) {
        //     for (long dd: ddd) System.out.print(dd);
        //     System.out.println();
        // }
        // System.out.println();
        
        return (int) (dp[n - 1][m - 1] % MOD);
    }
}

/***
0000
0100
0000

• 오른쪽 또는 아래로만 이동 가능

dp[i][j]: (i, j) 위치까지 도달하는 경로 수

이전 상태와 현재 상태 고려
• 위에서 (i, j)로 오는 경우: dp[i][j] = dp[i - 1][j]
• 왼쪽에서 (i, j)로 오는 경우: dp[i][j] = dp[i][j - 1]
=> 최종 점화식: dp[i][j] = dp[i - 1][j] + dp[i][j - 1]


***/
