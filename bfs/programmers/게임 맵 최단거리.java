import java.util.*;

class Solution {
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = Integer.MAX_VALUE;
    
    /**
    * 출발지(0, 0)에서 도착지(N - 1, M - 1)로 가는 칸의 개수 최솟값 도출
    * @param maps N x M 크기 게임맵 상태 (1이상 100이하 자연수), 0 이동불가, 1 이동가능
    **/
    public int solution(int[][] maps) {
        bfs(maps);
        
        // 도착할 수 없을 때는 -1 반환
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void bfs(int[][] maps) {
        int N = maps.length; int M = maps[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 1}); // x, y, dist
        int[][] dist = new int[N][M];
        for (int[] ds : dist) Arrays.fill(ds, Integer.MAX_VALUE);
        dist[0][0] = 1;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            
            if (x == N - 1 && y == M - 1) {
                answer = Math.min(answer, d);
                continue;
            }
            
            for (int[] dr : dir) {
                int nx = x + dr[0];
                int ny = y + dr[1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || maps[nx][ny] == 0) continue;
                if (dist[nx][ny] <= d + 1) continue;
                
                dist[nx][ny] = d + 1;
                pq.add(new int[]{nx, ny, d + 1});
            }
        }
    }
}
