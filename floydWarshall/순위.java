class Solution {
    public int solution(int n, int[][] results) {
        // 1. i가 j를 이기는 2중 배열 초기화 (1-index)
        boolean[][] winArr = new boolean[n + 1][n + 1];
        for (int[] result : results) {
            winArr[result[0]][result[1]] = true;
        }
        
        // 경유지도 포함하여 배열 초기화
        for (int k = 1; k <= n; k++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    if (winArr[x][k] && winArr[k][y])
                        winArr[x][y] = true;
                }
            }
        }
        
        // 2. 초기화한 배열을 바탕으로 확정난 선수의 수 도출
        int answer = 0;
        
        for (int x = 1; x <= n; x++) {
            int knownCnt = 0;
            
            for (int y = 1; y <= n; y++) {
                if (x == y) continue;
                
                // x가 y를 이기거나, y가 x를 이기는 둘 중 하나를 알고있다면, x에 대한 정보를 알고있음을 의미
                if (winArr[x][y] || winArr[y][x])
                    knownCnt++;
            }
            
            if (knownCnt == n - 1) answer++;
        }
        
        return answer;
    }
}

/*
플로이드 워셜 : 경유지를 포함하여, 경로를 도출하는 상황 (3중 배열)
1. i가 j를 이기는 2중 배열 초기화
2. 초기화한 배열을 바탕으로 확정난 선수의 수 도출
*/
