import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == 'R') map[i][j] = 0;
                else if (str.charAt(j) == 'G') map[i][j] = 1;
                else map[i][j] = 2;
            }
        }
        
        visited = new boolean[N][N];
        System.out.println(findNotVisit(0, 0, true));
        visited = new boolean[N][N];
        System.out.println(findNotVisit(0, 0, false));
    }
    
    // 0. 방문하지 않은 지점 찾기 (type true: 정상, false: 색맹)
    static int findNotVisit(int x, int y, boolean type) {
        int visitN = 0;
        int result = 1;
        visitN = visitArea(x, y, map[x][y], 0, type);
        
        while (visitN < N * N) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visitN = visitArea(i, j, map[i][j], visitN, type);
                        result++;
                    }
                }
            }
        }
        
        return result;
    }
    
    // 1. 같은 색을 가진 구역을 모두 방문처리하며 탐색
    // BFS(Queue)
    static int visitArea(int x, int y, int nowCol, int cnt, boolean type) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        cnt++;
        queue.offer(new int[]{x, y});
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for (int[] mv : move) {
                int nx = now[0] + mv[0];
                int ny = now[1] + mv[1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                    
                if (type && nowCol == map[nx][ny]) {
                    visited[nx][ny] = true;
                    cnt++;
                    queue.offer(new int[]{nx, ny});
                } else if (!type && nowCol == 2 && map[nx][ny] == 2) {
                    visited[nx][ny] = true;
                    cnt++;
                    queue.offer(new int[]{nx, ny});
                } else if (!type && nowCol != 2 && map[nx][ny] != 2) {
                    visited[nx][ny] = true;
                    cnt++;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        return cnt;
    }
}

// R: 0 , G: 1, B: 2로 map에 저장
// 구역 수 출력
// 1. 같은 색을 가진 구역을 모두 방문처리하며 탐색
// 2. 같은 색 방문이 모두 끝나면, 영역의 수 추가하고 다른 색 영역 탐색
// 3. 영역의 수 출력
