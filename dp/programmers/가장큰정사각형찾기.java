import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int [][]board) {
        int N = board.length;
        int M = board[0].length;
        
        int[][] dp = new int[N][M]; // (i, j) 위치를 오른쪽 하단 꼭짓점이라고 할 때, 나올 수 있는 한 변 최대 길이
        
        // 점화식: dp[i][j] = Math.min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1]))
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (board[i][j] != 0) {
                    board[i][j] = Math.min(board[i - 1][j], Math.min(board[i][j - 1], board[i - 1][j - 1])) + 1;
                }
            }
        }
        
        int max = 0;
        for (int[] b: board) {
            for (int bb: b) {
                if (max < bb) max = bb;
            }
        }


        return max * max;
    }
}
