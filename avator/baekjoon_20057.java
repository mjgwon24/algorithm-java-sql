import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, totalResult;
    static int[][] map;
    // 시계방향: 왼 -> 아래 -> 오른쪽 -> 위
    static int[][] move = {
        {0, -1}, // 왼
        {1, 0}, // 아래
        {0, 1}, // 오른
        {-1, 0} // 위
    };
    static int[][][] directions = {
        // 왼
        {
            {-2, 0, 2},
            {-1, -1, 10},
            {-1, 0, 7},
            {-1, 1, 1},
            {0, -2, 5},
            // {0, -1, 55},
            {1, -1, 10},
            {1, 0, 7},
            {1, 1, 1},
            {2, 0, 2}
        },
        // 아래
        {
            {-1, -1, 1},
            {-1, 1, 1},
            {0, -2, 2},
            {0, -1, 7},
            {0, 1, 7},
            {0, 2, 2},
            {1, -1, 10},
            {1, 1, 10},
            // {1, 0, 55},
            {2, 0, 5}
        },
        // 오른쪽
        {
            {-2, 0, 2},
            {-1, -1, 1},
            {-1, 0, 7},
            {-1, 1, 10},
            {0, 2, 5},
            // {0, 1, 55},
            {1, -1, 1},
            {1, 0, 7},
            {1, 1, 10},
            {2, 0, 2}
        },
        // 위
        {
            {-2, 0, 5},
            // {-1, 0, 55},
            {-1, -1, 10},
            {-1, 1, 10},
            {0, -2, 2},
            {0, -1, 7},
            {0, 1, 7},
            {0, 2, 2},
            {1, -1, 1},
            {1, 1, 1}
        }
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 격자 크기
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 시작지점으로부터 시계방향으로 이동
        int startIdx = N / 2;
        gogo(startIdx);
        
        // 4. 최종적으로 빠져나간 모래 모두 합한 값 출력
        System.out.println(totalResult);
    }

    // 1. 시작지점으로부터 시계방향으로 이동
    // 1, 1, 2, 2, 3, 3, 4, 4, ...
    static void gogo(int startIdx) {
        int cnt = 1;
        int c = 0;
        int x = startIdx;
        int y = startIdx;

        while (x >= 0 && y >= 0 && x < N && y < N) {
            // 시계방향: 왼 -> 아래 -> 오른쪽 -> 위
            for (int i = 0; i < 4; i++) {
                int temp = 0;
                int[] mv = move[i];
                
                while (temp < cnt) {
                    // System.out.println("x: " + x + ", y: " + y);
                    x += mv[0];
                    y += mv[1];
                    
                    // 범위를 벗어남.
                    if (x < 0 || y < 0 || x >= N || y >= N) break;

                    // 2. 이동하면서 퍼지는 모래 분산시키기 (totalResult)
                    int tempAmount = 0;
                    
                    for (int[] d: directions[i]) {
                        //         {-2, 0, 2} x, y, %
                        int nx = x + d[0];
                        int ny = y + d[1];
                        int percent = d[2];
                        int moveAmount = map[x][y] * percent;
                        moveAmount = (int)((double)moveAmount * 0.01);
                        tempAmount += moveAmount;

                        // System.out.printf("퍼지는 모래(%d,%d) = %d\n", nx, ny, moveAmount);
                        
                        // 3. 퍼뜨리면서 빠져나가는 모래 갱신
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                            totalResult += moveAmount;
                            continue;
                        }
                        // 벗어나지 않는다면 분산
                        map[nx][ny] += moveAmount;
                    }

                    // 다 퍼뜨렸으면 남은 모래 a로 이동
                    int nx = x + mv[0];
                    int ny = y + mv[1];
                    int moveAmount = map[x][y] - tempAmount;

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        totalResult += moveAmount;
                    } else map[nx][ny] += moveAmount;

                    // 다 퍼뜨렸으면 현재위치는 모래 없애기
                    map[x][y] = 0;

                    temp++;
                }

                // 모래를 흩뿌리고 목적지에 도달하면 소멸
                if (x == 0 && y == 0) break;
                
                // 범위를 벗어남
                if (x < 0 || y < 0 || x >= N || y >= N) break;
    
                c++;
                
                if (c == 2) {
                    c = 0;
                    cnt++;
                }
            }

            // 모래를 흩뿌리고 목적지에 도달하면 소멸
            if (x == 0 && y == 0) break;
        }
    }
}

/***
토네이도는 (1, 1)까지 이동한 뒤 소멸

이동
시계방향: 왼 -> 아래 -> 오른쪽 -> 위

왼쪽으로 갈 경우 이동하는 모래 (이동하고 도착한 지점 기준)
전부 이동하고 남은 모래는 a 위치로 이동
100% - 45% = 55%

1. 시작지점으로부터 시계방향으로 이동
2. 이동하면서 퍼지는 모래 분산시키기
3. 퍼뜨리면서 빠져나가는 모래 갱신
4. 최종적으로 빠져나간 모래 모두 합한 값 출력


***/
