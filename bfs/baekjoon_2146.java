import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map, islandMap; // 지도, 섬의 번호 저장 맵
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int result = Integer.MAX_VALUE;
    static int islandSize;
    static List<List<int[]>> boundaries = new ArrayList<>();
    static boolean[][] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 100이하의 자연수
        map = new int[N][N];
        islandMap = new int[N][N];
        visited = new boolean[N][N];
        
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // 0은 바다, 1은 육지
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initialIslandInfo();

        // 디버깅 - 섬의 경계, 섬 인덱스가 잘 저장되는지 확인
        // for (int i = 0; i < islandSize; i++) {
        //     for (int[] arr: boundaries.get(i)) {
        //         System.out.print("("+arr[0] + ", " + arr[1] + ")"+" ");
        //     }
        //     System.out.println();
        // }
        // for (int[] nn: islandMap) {
        //     for (int n: nn) {
        //         System.out.print(n);
        //     }
        //     System.out.println();
        // }

        findShortest();

        // 가장 짧은 다리의 길이를 출력
        System.out.println(result);
    }

    // 섬의 개수와 경계 초기화 - islandSize, boundaries
    static void initialIslandInfo() {
        int cnt = 0;
        // boolean b = false; // 섬을 만났는지 여부

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 처음 1을 만난 경우 - 상하좌우 탐색하며 해당 섬의 범위를 탐색
                if (!visited[i][j] && map[i][j] == 1) {
                    boundaries.add(new ArrayList<>());
                    calIslandArea(i, j, cnt++);
                }
            }
        }

        islandSize = cnt;
    }

    // 섬의 범위 탐색
    static void calIslandArea(int x, int y, int islandIdx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        islandMap[x][y] = islandIdx + 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            boolean isBoundary = false;
            
            for (int[] mv: move) {
                int nx = cx + mv[0];
                int ny = cy + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 0) {
                    isBoundary = true;
                    continue;
                }

                // 해당 위치가 경계일 경우, boundaries에 추가
                // if (isBoundary) boundaries[islandIdx].add(new int[]{nx, ny});
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                islandMap[nx][ny] = islandIdx + 1;
            }

            if (isBoundary) boundaries.get(islandIdx).add(new int[]{cx, cy});
        }
    }

    // 섬의 경계에서 다른 섬으로 가는 가장 빠른 길이 도출
    static void findShortest() {
        for (int i = 0; i < islandSize; i++) {
            bfs(boundaries.get(i), i + 1);
        }
    }

    // 경계에서 다른 섬으로 가는 경우 탐색
    static void bfs(List<int[]> boundary, int islandIdx) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int[] b: boundary) {
            q.add(new int[]{b[0], b[1], 0}); // x, y, dist
            visited[b[0]][b[1]] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            for (int[] mv: move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

                // 이동중 섬을 만난 경우
                // 다른 섬의 경계를 만나야지만 가능
                if (map[nx][ny] == 1 && islandMap[nx][ny] != islandIdx) {
                    result = Math.min(result, dist);
                    return;
                }

                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
    }
}

/***
항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어짐
가장 짧은 다리: 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리

1. 섬의 개수와 경계 초기화
2. 섬의 경계에서 다른 섬으로 가는 가장 빠른 길이 도출
3. 도출된 길이들 중 최솟값 출력

섬의 경계 기준: 1의 주변에 0이 존재하는 경우
본인의 섬이 아닌, 다른 섬으로 도달해야함.
각 섬마다 본인 섬의 기준을 저장할 필요 있음.
List<List<int[]>> boundaries
boundaries[0] = ...

탐색을 하다가 다른 섬의 boundaries를 만날 경우 성공



***/
