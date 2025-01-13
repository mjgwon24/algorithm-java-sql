import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 1. 0,1,2로 구성된 map 배열 생성
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.substring(j, j + 1).equals("R")) map[i][j] = 0;
                else if (str.substring(j, j + 1).equals("G")) map[i][j] = 1;
                else map[i][j] = 2;
            }
        }

        visited = new boolean[N][N];
        int result = calZone(0);
        
        visited = new boolean[N][N];
        int colorResult = calZone(1);

        System.out.println(result);
        System.out.println(colorResult);
    }

    static int calZone(int normalYn) {
        int result = 0;
        int visitNum = 0;
        int x = 0;
        int y = 0;
        
        // 한가지 색만 탐색 시작 
        while (visitNum < N*N) {
            // visited = false인 부분 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        x = i;
                        y = j;
                    }
                }
            }

            // 3. 구역을 탐색하며, 다른 색이 나올 시 result를 증가시키고 새로운 구역 탐색 시작 
            visitNum = dfs(x, y, map[x][y], visitNum + 1, normalYn);
            result++;
        }

        return result;
    }

    // 2. 구역 탐색 - 전부 탐색해야하므로 DFS(스택) 사용
    static int dfs(int x, int y, int nowCol, int visitNum, int normalYn) {
        visited[x][y] = true;

        for (int[] mv : move) {
            int nx = x + mv[0];
            int ny = y + mv[1];

            // 경계 / 방문여부 체크
            if (nx < 0 || ny < 0|| nx >= N || ny >= N) continue;
            if (visited[nx][ny]) continue;
          
            // 색맹 체크
            if (normalYn == 0) {
              if (map[nx][ny] != nowCol) continue;
            } else {
              // 색맹일 경우 조건 체크
              if ((nowCol == 0 || nowCol == 1) && !(map[nx][ny] == 0 || map[nx][ny] == 1)) continue;
              if (nowCol == 2 && map[nx][ny] != 2) continue;
            }

            visited[nx][ny] = true;
            visitNum = dfs(nx, ny, nowCol, visitNum + 1, normalYn);
        }

        return visitNum;
    }
}

// 0: R(색맹은 G과 동일), 1: G(색맹은 R과 동일), 2: B 
// 1. 0,1,2로 구성된 map 배열 생성
// 2. 구역 탐색 - 전부 탐색해야하므로 DFS(스택) 사용, 방문 처리 
// 3. 구역을 탐색하며, 다른 색이 나올 시 result를 증가시키고 새로운 구역 탐색 시작 
