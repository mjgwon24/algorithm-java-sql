import java.lang.*;
import java.util.*;

class Solution {
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;
    static int[][] visited; // (x, y) cnt
    static int startX, startY, targetX, targetY;
    static int answer = Integer.MAX_VALUE;
    static int N, M;
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        map = new int[N][M];
        visited = new int[N][M];
        for (int[] v: visited) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < N;i++) {
            for (int j = 0; j < M; j++) {
                char c = board[i].charAt(j);
                if (c == 'D') map[i][j] = 1; // 장애물
                else if (c == 'G') { // 목적지
                    map[i][j] = 2;
                    targetX = i;
                    targetY = j;
                } else if (c == 'R') { // 출발지
                    map[i][j] = 3;
                    startX = i;
                    startY = j;
                }
            }
        }
        
        visited[startX][startY] = 0;
        bfs();
        
        
        
        // 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 필요한 최소 이동 도출
        // 목표 위치에 도달할 수 없을 경우 -1 return
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // map 0 빈칸, 1 장애물, 2 목적지, 3 출발지
    static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{startX, startY, 0}); // x, y, cnt
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            // if (cur[2] == 10) break;
            // System.out.printf("현재 이동 시작 지점: (%d, %d) , 현재까지 이동 횟수: %d, visited[%d][%d] = %d\n", cur[0], cur[1], cur[2], cur[0], cur[1], visited[cur[0]][cur[1]]);
            
            for (int i = 0; i < 4; i++) {
                int x = cur[0];
                int y = cur[1];
                int cnt = cur[2];
                
                // 이동할 방향
                int[] mv = move[i];
                
                // 한 방향으로 이동
                while (true) {
                    x += mv[0];
                    y += mv[1];
                    
                    // 미끄러짐 끝
                    if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 1) {
                        x -= mv[0];
                        y -= mv[1];
                        break;
                    }
                }
                
                // 이동하지 못했다면, 중지
                if (x == cur[0] && y == cur[1]) continue;
                
                // 다시 startX, startY로 돌아왔다면, 중지
                if (x == startX && y == startY) continue;
                if (visited[x][y] <= cnt) continue;
                
                // 목적지에 도달했다면, 중지
                if (x == targetX && y == targetY) {
                    answer = Math.min(answer, cnt + 1);
                    visited[x][y] = cnt + 1;
                    // System.out.printf("목적지 도착!: (%d, %d), 현재까지 최소 이동 횟수: %d\n", x, y, answer);
                    continue;
                }
                
                visited[x][y] = cnt + 1;
                pq.add(new int[]{x, y, cnt + 1});
            }
        }
    }
}

/***
• 한 번의 이동: 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것
• 목표 지점에 도착한다고 끝이 아님. 목표 지점에서 "멈춰야" 성공

• 도달 여부/가능성 판단 - DFS (X)
• 최단 경로 도출 - BFS

1. BFS - 출발 지점, 방향, 이동 횟수 넣기
2. 미끄러짐이 멈춘 곳이 도착지점인지 판단하고 종료

[ex]
board
["...D..R", 
".D.G...", 
"....D.D", 
"D....D.", 
"..D...."]	
result 7


***/
