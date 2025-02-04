import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, M, G, R;
    static int[][] map;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<int[]> canStartPositions = new ArrayList<>();
    static boolean[] selected;
    static int result;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정원 행 개수 (2 ≤ N ≤ 50)
        M = Integer.parseInt(st.nextToken()); // 정원 열 개수 (2 ≤ M ≤ 50)
        G = Integer.parseInt(st.nextToken()); // 초록 배양액 개수 (1 ≤ G ≤ 5)
        R = Integer.parseInt(st.nextToken()); // 빨강 배양액 개수 (1 ≤ R ≤ 5)
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < M; j++) {
                // 0 can't spread area, 1 can't start energy, 2 can start energy
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) canStartPositions.add(new int[]{i, j}); // (canStartPositions.size() <= 10)
            }
        }
        selected = new boolean[canStartPositions.size()];
        
        // 1. make combination - start energy position 
        selectPositions(0, 0, new ArrayList<>());
        System.out.println(result);
    }
    

    // 1. G + R 개의 배양 위치 선택
static void selectPositions(int depth, int start, List<Integer> selectedPos) {
    if (depth == G + R) {
        assignColors(0, 0, selectedPos, new boolean[selectedPos.size()]);
        return;
    }
    for (int i = start; i < canStartPositions.size(); i++) {
        selectedPos.add(i);
        selectPositions(depth + 1, i + 1, selectedPos);
        selectedPos.remove(selectedPos.size() - 1);
    }
}

// 2. 선택된 위치에 초록/빨강 배양액 배치
static void assignColors(int gCount, int index, List<Integer> selectedPos, boolean[] isGreen) {
    if (gCount == G) {
        spreadEnergy(selectedPos, isGreen);
        return;
    }
    if (index >= selectedPos.size()) return;

    isGreen[index] = true;
    assignColors(gCount + 1, index + 1, selectedPos, isGreen);

    isGreen[index] = false;
    assignColors(gCount, index + 1, selectedPos, isGreen);
}

    
    // 2. run spread energy -> make flowers
    static void spreadEnergy(List<Integer> positions, boolean[] isGreen) {
    int[][] time = new int[N][M];
    int[][] color = new int[N][M];
    Queue<int[]> q = new LinkedList<>();
    int flowerCount = 0;

    // 초기 배양액 배치
    for (int i = 0; i < positions.size(); i++) {
        int[] pos = canStartPositions.get(positions.get(i));
        int x = pos[0], y = pos[1];
        color[x][y] = isGreen[i] ? 3 : 4; // 초록 3, 빨강 4
        q.offer(new int[]{x, y, color[x][y], 0});
    }

    while (!q.isEmpty()) {
        int[] cur = q.poll();
        int x = cur[0], y = cur[1], type = cur[2], t = cur[3];

        if (color[x][y] == -1) continue; // 꽃이면 스킵

        for (int[] d : move) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (map[nx][ny] == 0) continue; // 호수

            if (color[nx][ny] == 0) {
                color[nx][ny] = type;
                time[nx][ny] = t + 1;
                q.offer(new int[]{nx, ny, type, t + 1});
            } 
            // 다른 배양액과 만났을 때 꽃 생성
            else if (color[nx][ny] != type && time[nx][ny] == t + 1 && color[nx][ny] != -1) {
                flowerCount++;
                color[nx][ny] = -1; // 꽃으로 표시
            }
        }
    }

    result = Math.max(result, flowerCount);
}

}

// print number of flowers

// 1. make combination - start energy position 
//    caution) match the number of each color - mark on the map. green 3, red 4.
// 2. run spread energy -> make flowers

// 시간제한 2초 (2 * 10^8 번 연산 가능)
