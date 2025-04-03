import java.util.*;

class Solution {
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 우, 좌, 하, 상
    
    public int solution(int[][] board) {
        int N = board.length;
        int[][][] cost = new int[N][N][4]; // x, y, 방향 (최소 비용 DP)
        
        // 비용 초기값 설정 (최소 비용 탐색을 위해 MAX값으로 초기화)
        for (int[][] arr: cost) {
            for (int[] a: arr)
                Arrays.fill(a, Integer.MAX_VALUE);
        }
        
        
        int answer = bfs(board, cost);
        return answer;
    }
    
    static int bfs(int[][] board, int[][][] cost) {
        int N = board.length;
        
        // 우선순위 큐 - 비용이 작은 경로를 우선 탐지
        PriorityQueue<int[]>pq = new PriorityQueue<>(Comparator.comparingInt(n -> n[3]));
        pq.add(new int[]{0, 0, -1, 0}); // x, y, dir(방향), cost
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int amount = cur[3];
            
            // 도착점 도달 시 최소 비용 반환 -> 우선순위 큐를 사용하였으므로 가장 적은 비용을 가진 경로가 먼저 탐색됨
            if (x == N - 1 && y == N - 1) return amount;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                
                // 경계값 검사 + 벽(1) 여부 확인
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) continue;
                
                int newAmount = amount + 100; // 직선 도로 비용
                // 방향이 바뀌면 코너 비용 추가
                // dir: 이전에 이동한 방향, i: 현재 이동하려는 방향
                // dir이 i와 같지 않다면, 방향이 바뀌었다는 의미이므로 코너 비용 추가
                // 첫 방향(-1)은 어떤 방향이든 직선 이동이므로 코너 예외
                if (dir != -1 && dir != i) newAmount += 500;
                
                // 최소 비용 갱신 시만 큐에 삽입 (중복 방문 x)
                if (newAmount < cost[nx][ny][i]) {
                    cost[nx][ny][i] = newAmount;
                    pq.add(new int[]{nx, ny, i, newAmount});
                }
            }
        }
        
        return -1;
    }
}

/***
DP + BFS
1. 코너 여부 판단해가며 금액 갱신
2. 목적지에 도착했다면, 금액 반영
3. 최종 최소 금액 도출
***/
