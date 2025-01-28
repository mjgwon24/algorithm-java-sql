import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, M;
    static int[][] map;
    static int[][] move = {
        {0, -1}, // left
        {0, 1}, // right
        {-1, 0}, // top
        {1, 0} // bottom
    };
    static List<int[]> cctvPositions = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사무실 세로 크기 (1 ≤ N, M ≤ 8)
        M = Integer.parseInt(st.nextToken()); // 사무실 가로 크기 (1 ≤ N, M ≤ 8)
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) cctvPositions.add(new int[]{i, j, map[i][j]});
            }
        }
        
        explore(0, map);
        System.out.println(result);
    }
    
    // 0. 방향 경우의 수 조합 탐색
    static void explore(int depth, int[][] currentMap) {
        if (depth == cctvPositions.size()) {
            // 3. 사각지대 영역 크기 구하기
            calculateBlindSpot(currentMap);
            return;
        }
        
        int[] cctv = cctvPositions.get(depth);
        int x = cctv[0];
        int y = cctv[1];
        int type = cctv[2];
        
        // 1. 각 CCTV 종류에 따라 가능한 방향 조합 탐색
        int[][][] cctvDirections = getCCTVDirections(type);
        
        // int[][][]{{{0, -1}}, {move[1]}, {move[2]}, {move[3]}};
        for (int[][] directionSet : cctvDirections) { // {{0, -1}}
            int[][] copiedMap = copyMap(currentMap);
            
            for (int[] mv : directionSet) {
                // CCTV 동작 - 감시 영역 퍼뜨리기
                extendVision(x, y, mv, copiedMap);
            }
            
            explore(depth + 1, copiedMap);
        }
    }
    
    // CCTV 동작 - 감시 영역 퍼뜨리기
    static void extendVision(int x, int y, int[] mv, int[][] cpMap) {
        int nx = x + mv[0];
        int ny = y + mv[1];
        
        while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
            if (cpMap[nx][ny] == 6) break; // 벽
            if (cpMap[nx][ny] == 0) {
                cpMap[nx][ny] = -1; // 감시 영역
            }
            nx += mv[0];
            ny += mv[1];
        }
    }
    
    // 각 CCTV 종류별 방향 조합 반환
    // 1 한 방향 탐색 - 4가지 경우 ({0}, {1}, {2}, {3})
    // 2 양 방향 탐색 - 2가지 경우 ({0, 1}, {2, 3})
    // 3 직각 방향 탐색 - 4가지 경우 ({1, 2}, {1, 3}, {0, 3}, {0, 2})
    // 4 세 방향 탐색 - 4가지 경우 ({0, 1, 3}, {1, 2, 3}, {0, 1, 2}, {0, 2, 3})
    // 5 네 방향 탐색 - 1가지 경우 ({0, 1, 2, 3})
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
    
    // 배열 복사
    static int[][] copyMap(int[][] map) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copied[i], 0, M);
        }
        
        return copied;
    }
    
    // 사각지대 영역 크기 계산
    static void calculateBlindSpot(int[][] copy) {
        int n = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    n++;
                }
                // System.out.print(copy[i][j] + " ");
            }
            // System.out.println();
        }
        // System.out.println();
        // System.out.println();
        
        result = Math.min(result, n);
    }
}

// 선택의 조합을 넘겨주기

// 0. 방향 경우의 수 조합 탐색
// 1. cctv 동작 시키기
// 2. 감시 영역 퍼뜨리기
//    위, 아래, 오른쪽, 왼쪽
// 3. 사각지대 영역 크기 구하기
// 4. 최종 사각지대 영역 최소 크기 출력
