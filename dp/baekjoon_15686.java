import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static List<int[]> stores = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int minStoreDistance = Integer.MAX_VALUE;
    
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

        selectStores(new ArrayList<>(), 0);

        System.out.println(minStoreDistance);
    }

    // 2. 선택된 치킨집으로 치킨거리 합 계산
    static void calStoreDistSum(List<Integer> selected) {
        int totalDist = 0;

        for (int[] house : houses) {
            int minDist = Integer.MAX_VALUE;
            for (int idx : selected) {
                int[] store = stores.get(idx);
                int dist = Math.abs(house[0] - store[0]) + Math.abs(house[1] - store[1]);
                minDist = Math.min(dist, minDist);
            }
            totalDist += minDist;
        }

        minStoreDistance = Math.min(totalDist, minStoreDistance);
    }
    

    // 1. 치킨집 M개 선택
    static void selectStores(List<Integer> selected, int start) {
        if (selected.size() == M) {
            // 2. 합 계산
            calStoreDistSum(selected);
            return;
        }

        // 스택 원리 
        for (int i = start; i < stores.size(); i++) {
            selected.add(i);
            selectStores(selected, i + 1);
            // 백트래킹 - 가장 마지막에 추가된 요소 제거
            selected.remove(selected.size() - 1);
        }
    }
}

// 0 빈칸, 1 집, 2 치킨집 
// 치킨거리 = |r1-r2| + |c1-c2|
// 도시 크기 N x N, 선택할 치킨집 수 M
// 치킨집 M개를 선택해 치킨거리의 합 출력 

// dist[i][j]: i번째 집과 j번째 치킨집 사이 거리 
// 1. 치킨집 M개 선택
// 2. 선택된 치킨집으로 치킨거리 합 계산
// 각 집당 치킨집 하나를 방문하는 최소 거리 합 
