import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int solution(int[][] maps) {
        
        dijkstra(maps);
      
        // 상대 팀 진영에 도착하기 위해 지나가야 하는 칸 개수 최솟값 반환
        // 도착할 수 없을 경우 -1 반환
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void dijkstra(int[][] maps) {
        int N = maps.length; int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];
            
            // System.out.printf("현재 위치: %d, %d, 거리: %d\n", x, y, dist);
            
            if (x == N - 1 && y == M - 1) {
                answer = Math.min(answer, dist);
                continue;
            }
            
            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || maps[nx][ny] == 0) continue;
                
                // System.out.printf("다음 탐색 예정위치: %d, %d, 거리: %d\n", nx, ny, dist + 1);
                visited[nx][ny] = true;
                pq.add(new int[]{nx, ny, dist + 1});
            }
        }
    }
}
