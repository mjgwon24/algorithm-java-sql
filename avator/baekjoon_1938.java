import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static State start, target;

    static class State {
        int x, y, ori, moves; // x, y: 중앙 좌표, ori: 0(가로), 1(세로), 이동량

        public State(int x, int y, int ori, int moves) {
            this.x = x;
            this.y = y;
            this.ori = ori;
            this.moves = moves;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int[][] B = new int[3][2];
        int[][] E = new int[3][2];
        int bIdx = 0, eIdx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);

                if (c == '0') map[i][j] = 0;
                else if (c == '1') map[i][j] = 1;
                else if (c == 'B') {
                    map[i][j] = 2;
                    B[bIdx][0] = i;
                    B[bIdx][1] = j;
//                    System.out.printf("B[%d] = %d, %d\n", bIdx, i, j);
                    bIdx++;
                }
                else if (c == 'E') {
                    map[i][j] = 3;
                    E[eIdx][0] = i;
                    E[eIdx][1] = j;
//                    System.out.printf("E[%d] = %d, %d\n", eIdx, i, j);
                    eIdx++;
                }
            }
        }

        // 시작 상태 (0: 가로, 1: 세로)
        int oriB = (B[0][0] == B[1][0]) ? 0 : 1;
        start = new State(B[1][0], B[1][1], oriB, 0);

        // 목적지 상태
        int oriE = (E[0][0] == E[1][0]) ? 0 : 1;
        target = new State(E[1][0], E[1][1], oriE, 0);
//        System.out.printf("target.x = %d, %d\n", target.x, target.y);

        // 최소 값 도출
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        boolean[][][] visited = new boolean[N][N][2]; // x, y, 방향
        Queue<State> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y][start.ori] = true;
//        System.out.println("시작");
//        System.out.printf("start.x = %d, start.y = %d\n", start.x, start.y);
//        System.out.printf("target.x = %d, target.y = %d\n", target.x, target.y);

        while (!q.isEmpty()) {
            State cur = q.poll(); // x, y, ori, moves

            // 목적지 방문 여부 판단 - 가장 빠르게 도착한 값이 가장 작은 값
            if (cur.x == target.x && cur.y == target.y && cur.ori == target.ori) {
//                System.out.println("??");
                return cur.moves;
            }

            // 상하좌우 이동
            for (int[] mv: move) {
                int nx = cur.x + mv[0];
                int ny = cur.y + mv[1];

//                System.out.println("이동");

                if (!canMove(cur, nx, ny)) continue;
                if (visited[nx][ny][cur.ori]) continue; // 중복 방문 금지

                visited[nx][ny][cur.ori] = true;
//                System.out.printf("다음 목적지 중심: %d, %d\n", nx, ny);
                q.add(new State(nx, ny, cur.ori, cur.moves + 1));
            }

            // 회전 (90도)
            if (canRotate(cur)) {
                int newOri = cur.ori == 0 ? 1 : 0;

                if (!visited[cur.x][cur.y][newOri]) {
                    visited[cur.x][cur.y][newOri] = true;
                    q.add(new State(cur.x, cur.y, newOri, cur.moves + 1));
                }
            }
        }

        // 목적지에 도달하지 못했을 경우 0 Return
        return 0;
    }

    // 중심을 기준으로 이동 가능 여부 체크
    static boolean canMove(State s, int nx, int ny) {
        if (s.ori == 0) {
            // 가로 (nx, ny - 1 ~ ny + 1)
            for (int j = ny - 1; j <= ny + 1; j++) {
                if (!inRange(nx, j) || map[nx][j] == 1) return false;
            }
        } else {
            // 세로 (nx - 1 ~ nx + 1, ny)
            for (int i = nx - 1; i <= nx + 1; i++) {
                if (!inRange(i, ny) || map[i][ny] == 1) return false;
            }
        }

        return true;
    }

    // 회전 시 중심을 기준으로 3x3 영역 장애물 체크
    static boolean canRotate(State s) {
        for (int i = s.x - 1; i <= s.x + 1; i++) {
            for (int j = s.y - 1; j <= s.y + 1; j++) {
                if (!inRange(i, j) || map[i][j] == 1) return false;
            }
        }

        return true;
    }

    // 범위 벗어남 여부 판단
    static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}

/*
0. 통나무가 목적지에 도달했는지 확인
   -> 최소 도달 시간 도출
1. 통나무를 상하좌우로 옮김
2. 통나무를 90도로 옮김
 */
