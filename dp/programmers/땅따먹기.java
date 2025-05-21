class Solution {
    int solution(int[][] dp) {
        int answer = 0;
        
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] += Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][1] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][2] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][3]));
            dp[i][3] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
        }

        for (int n: dp[dp.length - 1]) answer = Math.max(answer, n);
        // 최고점 Return
        return answer;
    }
}

/*
dp[0][0] = 1
dp[0][1] = 2
dp[0][2] = 3
dp[0][3] = 5

dp[1][0] = max(dp[0][1], dp[0][2], dp[0][3])
dp[1][1] = 2
dp[1][2] = 3
dp[1][3] = 5

dp[2][0] = 1
dp[2][1] = 2
dp[2][2] = 3
dp[2][3] = 5


*/
