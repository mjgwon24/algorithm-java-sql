class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int answer = 0;
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) triangle[i][0] += triangle[i - 1][0];
                else if (j == triangle[i].length - 1) triangle[i][j] += triangle[i - 1][j - 1];
                else triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }
        
        for (int n: triangle[N - 1]) answer = Math.max(answer, n);
        
        // 거쳐간 숫자의 최댓값 return
        return answer;
    }
}
