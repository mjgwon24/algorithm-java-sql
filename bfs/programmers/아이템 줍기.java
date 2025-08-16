import java.util.*;

class Solution {
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102];
        
        // 1. 좌표 바탕으로 그래프 초기화 (테두리 경계 구분을 위해 사이즈 2배)
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            fill(x1, y1, x2, y2);
        }
        
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            hollowOut(x1, y1, x2, y2);
        }
        
        // 디버깅
        // for (int[] mp : map) {
        //     for (int m : mp) System.out.print(m + " ");
        //     System.out.println();
        // }
        
        // 2. 경로 탐색
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        
        // 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리 반환 (/2)
        return answer / 2;
    }
    
    static void bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{characterX, characterY, 0});
        
        int[][] dist = new int[102][102];
        for (int[] ds : dist) Arrays.fill(ds, Integer.MAX_VALUE);
        dist[characterX][characterY] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            
            if (x == itemX && y == itemY) {
                answer = Math.min(answer, d);
                continue;
            }
            
            for (int[] dr : dir) {
                int nx = x + dr[0];
                int ny = y + dr[1];
                
                if (nx < 0 || ny < 0 || map[nx][ny] == 0 || dist[nx][ny] <= d + 1) continue;
                
                dist[nx][ny] = d + 1;
                q.add(new int[] {nx, ny, d + 1});
            }
        }
    }
    
    static void fill(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                map[i][j] = 1;
            }
        }
    }
    
    static void hollowOut(int x1, int y1, int x2, int y2) {
        for (int i = x1 + 1; i < x2; i++) {
            for (int j = y1 + 1; j < y2; j++) {
                map[i][j] = 0;
            }
        }
    }
}
