import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int N, M;
    static int numberOfArea, maxSizeOfOneArea;
    
    public int[] solution(int n, int m, int[][] picture) {
        N = n; M = m;
        numberOfArea = 0; maxSizeOfOneArea = 0;
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    bfs(picture, i, j, picture[i][j]);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        // 몇 개의 영역이 있는지, 가장 큰 영역은 몇 칸인지 return
        // 영역: 상하좌우로 연결된 같은 색상의 공간
        return answer;
    }
    
    static void bfs(int[][] picture, int x, int y, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        int size = 1;
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int[] dr: directions) {
                int nx = cur[0] + dr[0];
                int ny = cur[1] + dr[1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (picture[nx][ny] != color || visited[nx][ny]) continue;
                
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                size++;
            }
        }
        
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
    }
}

/*
m, n 그림 크기
picture 그림

picture
[[1, 1, 1, 0], 
[1, 2, 2, 0], 
[1, 0, 0, 1], 
[0, 0, 0, 1], 
[0, 0, 0, 3], 
[0, 0, 0, 3]]	

[[1, 1, 1, 0], 
[1, 1, 1, 0], 
[0, 0, 0, 1], 
[0, 0, 0, 1], 
[0, 0, 0, 1], 
[0, 0, 0, 1]]

*/
