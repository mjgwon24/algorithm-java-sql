class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length, M = board[0].length;
        int[][] acc = new int[N + 1][M + 1];
        
        // Imos
        // [0 type, 1 r1, 2 c1, 3 r2, 4 c2, 5 degree]
        int degree = 0;
        for (int[] s: skill) {
            if (s[0] == 1) degree = -s[5];
            else degree = s[5];
            
            acc[s[1]][s[2]] += degree;
            acc[s[3] + 1][s[2]] -= degree;
            acc[s[1]][s[4] + 1] -= degree;
            acc[s[3] + 1][s[4] + 1] += degree;
        }
        
        // 누적합
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                acc[i][j] += acc[i][j - 1];
            }
        }
        
        for (int j = 0; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                acc[i][j] += acc[i - 1][j];
            }
        }
        
        // 파괴되지 않은 건물 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += acc[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
