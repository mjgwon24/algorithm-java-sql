import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static List<int[]> stores = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int minStoreDistance = Integer.MAX_VALUE;
    static int[][] allDist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) houses.add(new int[]{i, j});
                if (map[i][j] == 2) stores.add(new int[]{i, j});
            }
        }

        allDist = new int[houses.size()][stores.size()];
        calDist();
        selectStores(new boolean[stores.size()], 0, 0);

        System.out.println(minStoreDistance);
    }

    // 2. 선택된 치킨집으로 치킨거리 합 계산 (최적화 - dist[][] 이용)
    static void calStoreDistSum(boolean[] selected) {
        int totalDist = 0;

        for (int i = 0; i < houses.size(); i++) {
            int minDist = Integer.MAX_VALUE;
            int[] house = houses.get(i);
            
            for (int j = 0; j < stores.size(); j++) {
                if (selected[j]) {
                    int[] store = stores.get(j);
                    minDist = Math.min(minDist, allDist[i][j]);
                }
            }
            totalDist += minDist;
            
            // 최적화 - 거리 합계가 이미 초과되었다면, 더이상 계산할 필요 없음
            if (totalDist > minStoreDistance) return;
        }

        minStoreDistance = Math.min(totalDist, minStoreDistance);
    }
    

    // 1. 치킨집 M개 선택 (최적화 - boolean[]을 통해 표시)
    static void selectStores(boolean[] selected, int start, int cnt) {
        if (cnt == M) {
            // 2. 합 계산
            calStoreDistSum(selected);
            return;
        }

        // 스택 원리 
        for (int i = start; i < stores.size(); i++) {
            selected[i] = true;
            selectStores(selected, i + 1, cnt + 1);
            // 백트래킹 - 탐색한 위치 false
            selected[i] = false;
        }
    }

    // 0. 각 집과 치킨집 사이의 거리 배열 계산 (최적화)
    static void calDist() {
        for (int i = 0; i < houses.size(); i++) {
            int[] house = houses.get(i);
            
            for (int j = 0; j < stores.size(); j++) {
                int[] store = stores.get(j);
                allDist[i][j] = Math.abs(house[0] - store[0]) + Math.abs(house[1] - store[1]);
            }
        }
    }
}

// 0 빈칸, 1 집, 2 치킨집 
// 치킨거리 = |r1-r2| + |c1-c2|
// 도시 크기 N x N, 선택할 치킨집 수 M
// 치킨집 M개를 선택해 치킨거리의 합 출력 

// 0. 각 집과 치킨집 사이의 거리 배열 계산 (최적화)
// dist[i][j]: i번째 집과 j번째 치킨집 사이 거리 
// 1. 치킨집 M개 선택 (최적화 - boolean[]을 통해 표시)
// 2. 선택된 치킨집으로 치킨거리 합 계산 (최적화 - dist[][] 이용)
// 각 집당 치킨집 하나를 방문하는 최소 거리 합 
