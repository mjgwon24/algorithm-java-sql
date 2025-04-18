import java.util.*;
import java.io.*;

// 승객 정보를 저장하는 클래스
class Passenger {
    int sx, sy, tx, ty;
    public Passenger(int sx, int sy, int tx, int ty) {
        this.sx = sx; this.sy = sy; this.tx = tx; this.ty = ty;
    }
}

public class Main {
    static int N, M, amount;
    static int[][] map;
    static List<Passenger> passengers = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // (startX, startY)에서 각 칸까지 최단거리 배열 반환
    static int[][] bfs(int startX, int startY) {
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, -1);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        dist[startX][startY] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] == 1) continue;
                if (dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        amount = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 시작 위치 (1-indexed → 0-indexed)
        st = new StringTokenizer(br.readLine());
        int texiX = Integer.parseInt(st.nextToken()) - 1;
        int texiY = Integer.parseInt(st.nextToken()) - 1;

        // 승객 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            passengers.add(new Passenger(a, b, c, d));
        }

        // 승객을 모두 태울 때까지 반복
        for (int t = 0; t < M; t++) {
            // 1. 택시 위치에서 모든 칸까지의 최단거리 구하기 (BFS 함수 활용)
            int[][] dist = bfs(texiX, texiY);

            // 2. 조건에 맞는(최단거리, 행, 열) 승객 선택
            Passenger select = null;
            int minDist = Integer.MAX_VALUE;
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
          
            for (Passenger p : passengers) {
                int d = dist[p.sx][p.sy];
                if (d == -1) continue;
                if (d < minDist ||
                    (d == minDist && p.sx < minX) ||
                    (d == minDist && p.sx == minX && p.sy < minY)) {
                    minDist = d;
                    select = p;
                    minX = p.sx;
                    minY = p.sy;
                }
            }
          
            // 승객 선택 불가
            if (select == null || minDist > amount) {
                System.out.println(-1);
                return;
            }

            // 3. 승객 위치로 이동 (연료 차감, 택시 위치 갱신)
            amount -= minDist;
            texiX = select.sx;
            texiY = select.sy;

            // 4. 승객 목적지까지 이동 (BFS 함수 활용)
            int[][] dist2 = bfs(texiX, texiY);
            int goDist = dist2[select.tx][select.ty];
          
            if (goDist == -1 || goDist > amount) {
                System.out.println(-1);
                return;
            }
          
            amount -= goDist;
            amount += goDist * 2;
            texiX = select.tx;
            texiY = select.ty;
            passengers.remove(select);
        }
        // 모든 승객 이동 완료 후 남은 연료 출력
        System.out.println(amount);
    }
}

/***
손님을 목적지로 데려다줄 때마다 연료 충전
연료 바닥나면 그 날의 업무는 끝

1. 승객 선택 기준: 현재 위치에서 최단거리가 가장 짧은 승객
    -> 승객이 여러명이면, 행(x) 번호가 가장 작은 승객
    -> 승객이 여러명이면, 열(y) 번호가 가장 작은 승객
2. 이동완료: 승객을 태워 이동하면서 소모한 연료 양의 두 배 충전
3. 이동하는 도중 연료가 바닥나면 이동 실패 - 바로 return
    -> 목적지로 이동시킨 동시에 연료 바닥나는 경우, 실패 아님.

• 승객 선택:
    최단 거리 -> BFS
    여러 명이면 행, 열 -> BFS 탐색 순서 및 비교 필요
• 이동 경로:
    최단 거리 -> BFS 두 번 필요 (택시 -> 승객, 승객 -> 목적지)
• 연료 관리:
    이동마다 연료 차감, 목적지 도달 시 연료 보상
• 종료 조건:
    연료 부족, 승객 목적지 이동 불가, 승객 전부 이동 완료

***/
