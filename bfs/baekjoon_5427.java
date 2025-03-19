import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 너비
            int H = Integer.parseInt(st.nextToken()); // 높이
            int[][] map = new int[H][W];
            int[] startPoint = new int[2];
            Queue<int[]> fireQueue = new LinkedList<>();
            Queue<int[]> sQueue = new LinkedList<>();

            // w개의 문자, 빌딩의 지도
            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                
                for (int j = 0; j < W; j++) {
                    String s = str.substring(j, j + 1);
                    if (s.equals(".")) map[i][j] = 0;
                    else if (s.equals("@")) {
                        map[i][j] = 1;
                        startPoint[0] = i;
                        startPoint[1] = j;
                        sQueue.add(new int[]{i, j, 0});
                    }
                    else if (s.equals("#")) map[i][j] = 2;
                    else {
                        map[i][j] = 3;
                        fireQueue.add(new int[]{i, j});
                    }
                }
            }
    
            // 0. 예외 처리 - 출발지 주변이 전부 불3/벽2일 경우 탈출 불가능
            // 출발지 주변 상하좌우 탐색
            boolean isImpossible = true; // 탈출 불가능
            boolean isReturn = false;
            for (int[] mv : move) {
                int nx = startPoint[0] + mv[0];
                int ny = startPoint[1] + mv[1];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                    // 탈출 바로 가능
                    isImpossible = false;
                    isReturn = true;
                    break;
                }
                if (map[nx][ny] != 3 && map[nx][ny] != 2) {
                    // 탈출 가능 가능성 있음
                    isImpossible = false;
                    break;
                }
            }

            if (isImpossible) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            if (isReturn) {
                System.out.println(1);
                continue;
            }

            // 1. 불과 상근이의 이동 실행 (queue, bfs)
            int result = bfs(map, W, H, fireQueue, sQueue);
            
            // 빌딩을 탈출하는데 가장 빠른 시간
            // 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"
            System.out.println(result == -1 ? "IMPOSSIBLE" : result);
        }
    }

    // 불과 상근이의 이동 실행 (queue, bfs)
    // 탈출 시간 RETURN (탈출하지 못했으면 -1 RETURN)
    static int bfs(int[][] map, int W, int H, Queue<int[]> fireQueue, Queue<int[]> sQueue) {
        int[][] fireTime = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }
        
        // 불 퍼뜨리기
        while (!fireQueue.isEmpty()) {
            int[] current = fireQueue.poll();
            int x = current[0];
            int y = current[1];

            // 불 시간 초기화
            if (fireTime[x][y] == Integer.MAX_VALUE) fireTime[x][y] = 0;

            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                // 벽이거나 불이 이미 퍼진 경우 이동 불가
                if (map[nx][ny] == 2 || map[nx][ny] == 3) continue;

                // 이동 및 퍼진 시간 저장
                fireQueue.add(new int[]{nx, ny});
                map[nx][ny] = 3;
                fireTime[nx][ny] = fireTime[x][y] + 1;
            }
        }

        // 상근이 이동
        while(!sQueue.isEmpty()) {
            int[] current = sQueue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            // System.out.printf("현재 상근이 (%d, %d)위치, 현재 시간은 %d\n", x, y, time);

            // 탈출
            if (x == 0 || y == 0 || x == H - 1 || y == W - 1) return time + 1;

            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                // 벽이거나 상근이가 이미 이동한 경우 이동 불가
                if (map[nx][ny] == 2 || map[nx][ny] == 1) {
                    // System.out.printf("1. 이동불가 (%d, %d)로\n", nx, ny);
                    continue;
                }
                // 불이 퍼진 시간 <= 상근이 이동 시간 일 경우, 이동 불가
                if (map[nx][ny] == 3 && fireTime[nx][ny] <= time + 1) {
                    // System.out.printf("2. 이동불가 (%d, %d)로\n", nx, ny);
                    continue;
                }

                // 이동
                sQueue.add(new int[]{nx, ny, time + 1});
                map[nx][ny] = 1;
                // System.out.printf("상근이 (%d, %d)로 이동, 이동했을때 시간은 %d\n", nx, ny, time + 1);
            }
            // System.out.println();
        }

        return -1;
    }
}

/***
'.': 빈 공간 0
'@': 상근이의 시작 위치 1
'#': 벽 2
'*': 불 3
=> @의 개수는 하나

• 매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다.
• 상근이 이동 1초
• 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다
• 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.

[ solution ]
0. 예외 처리 - 출발지 주변이 전부 불/벽일 경우 탈출 불가능
1. 불과 상근이의 이동 실행 (queue, bfs)
    1.1 상근이의 다음 위치에 불/벽이 있을 경우 이동 불가
    1.2 상근이가 map 범위를 벗어나면 탈출 성공
    1.3 queue가 비었음에도 탈출하지 못했다면 탈출 실패
2. 탈출했을때의 시간 중 최솟값 도출


4 3
####
#*@.
####
=> 2초

7 6
###.###
#*#.#*#
#.....#
#.....#
#..@..#
#######
=> 5초


5 5
.....
.***.
.*@*.
.***.
.....
=> IMPOSSIBLE (상근이 주변에 전부 불임)

3 3
###
#@#
###
=> IMPOSSIBLE (전부 벽)

7 4
###.###
#....*#
#@....#
.######
=> IMPOSSIBLE (출구로 가는 길에 불 전염됨)

***/
