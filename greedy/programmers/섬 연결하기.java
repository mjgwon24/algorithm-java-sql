import java.util.*;

class Solution {
    static int[] path;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int totalCost = 0;
        int edges = 0;
        
        // 1. path 초기화 - 초기에는 어느 섬도 연결되어있지 않음
        path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = i;
        }
        
        // 2. 최소 비용 순으로 다리 순회
        Arrays.sort(costs, Comparator.comparingInt((int[] a) -> a[2]));
        for (int[] cs : costs) {
            int from = cs[0];
            int to = cs[1];
            int cost = cs[2];
            
            // 3. 사이클이 발생하지 않는 경우에만 다리 건설
            if (find(from) != find(to)) {
                union(from, to);
                totalCost += cost;
                edges++;
            }
        }
        
        // 다리를 최소 개수로 전부 지었다면 최소 비용 반환
        if (edges == n - 1) return totalCost;
        
        // 최소 비용 반환
        return answer;
    }
    
    static int find(int x) {
        if (path[x] == x) return x;
        return path[x] = find(path[x]);
    }
    
    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        
        if (px != py) path[py] = px;
    }
}

/*
최소 비용, 모든 섬 -> MST(그리디, 크루스칼)

*/
