import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }

    static int N, M;
    static int[][] map;
    static int[][] move = {
        {0, -1}, // left
        {0, 1},  // right
        {-1, 0}, // top
        {1, 0}   // bottom
    };
    static List<int[]> cctvPositions = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    
    // DP를 위한 메모이제이션 (현재 CCTV 인덱스, 감시된 좌표 집합)
    static Map<String, Integer> dpCache = new HashMap<>();

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvPositions.add(new int[]{i, j, map[i][j]});
                }
            }
        }

        explore(0, new HashSet<>());
        System.out.println(result);
    }

    // CCTV 방향을 탐색하는 DFS + DP
    static void explore(int depth, Set<Integer> watched) {
        if (depth == cctvPositions.size()) {
            calculateBlindSpot(watched);
            return;
        }

        int[] cctv = cctvPositions.get(depth);
        int x = cctv[0], y = cctv[1], type = cctv[2];

        // DP 체크: (현재 CCTV 인덱스 + 감시 좌표 상태)를 문자열로 저장
        String key = depth + ":" + watched.toString();
        if (dpCache.containsKey(key)) return; // 이미 계산된 상태라면 중복 계산 방지
        dpCache.put(key, 1); // 방문 체크

        int[][][] cctvDirections = getCCTVDirections(type);

        for (int[][] directionSet : cctvDirections) {
            Set<Integer> newWatched = new HashSet<>(watched);

            for (int[] mv : directionSet) {
                extendVision(x, y, mv, newWatched);
            }

            explore(depth + 1, newWatched);
        }
    }

    // 감시 영역 확장 (맵 복사 대신 좌표 Set에 저장)
    static void extendVision(int x, int y, int[] mv, Set<Integer> watched) {
        int nx = x + mv[0], ny = y + mv[1];

        while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
            if (map[nx][ny] == 6) break; // 벽
            watched.add(nx * M + ny); // 좌표를 숫자로 변환하여 저장 (nx, ny → 1차원 인덱스)
            nx += mv[0];
            ny += mv[1];
        }
    }

    // 감시되지 않은 사각지대 계산
    static void calculateBlindSpot(Set<Integer> watched) {
        int blindSpots = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !watched.contains(i * M + j)) {
                    blindSpots++;
                }
            }
        }
        result = Math.min(result, blindSpots);
    }

    // CCTV 방향 반환
    static int[][][] getCCTVDirections(int type) {
        switch (type) {
            case 1: return new int[][][]{{move[0]}, {move[1]}, {move[2]}, {move[3]}};
            case 2: return new int[][][]{{move[0], move[1]}, {move[2], move[3]}};
            case 3: return new int[][][]{{move[0], move[2]}, {move[0], move[3]}, {move[1], move[2]}, {move[1], move[3]}};
            case 4: return new int[][][]{{move[0], move[1], move[2]}, {move[0], move[1], move[3]}, {move[0], move[2], move[3]}, {move[1], move[2], move[3]}};
            case 5: return new int[][][]{{move[0], move[1], move[2], move[3]}};
            default: return new int[][][]{};
        }
    }
}
