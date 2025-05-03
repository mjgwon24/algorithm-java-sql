import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, totalAmount, texiX, texiY;
    static int[][] map;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵 크기 (N * N)
        M = Integer.parseInt(st.nextToken()); // 손님 수
        totalAmount = Integer.parseInt(st.nextToken()); // 초기 연료량
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                // 0 빈칸, 1 벽
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 출발지점
        st = new StringTokenizer(br.readLine());
        texiX = Integer.parseInt(st.nextToken()) - 1; 
        texiY = Integer.parseInt(st.nextToken()) - 1;

        // 손님 (승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호)
        int[][] customerInfo = new int[M][4];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            customerInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            customerInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
            customerInfo[i][2] = Integer.parseInt(st.nextToken()) - 1;
            customerInfo[i][3] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 각 손님 -> 모든 위치 dist
        List<int[][]> PtDdist = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            PtDdist.add(dijkstra(customerInfo[i][0], customerInfo[i][1]));
        }

        // 목적지 -> 각 손님 dist
        List<int[][]> DtPdist = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            DtPdist.add(dijkstra(customerInfo[i][2], customerInfo[i][3]));
        }

        // 택시에서 가장 가까이있는 손님에게 먼저 간다
        boolean[] visited = new boolean[M];
        int totalCustomers = 0;

        while (totalCustomers < M) {
            int minCustomerIdx = 0;
            int minDist = Integer.MAX_VALUE;
            int idx = 0;
            
            for (int[][] dist: PtDdist) {
                if (visited[idx]) {
                    idx++;
                    continue;
                }
                
                // 손님과 택시가 도달할 수 없으면, 바로 -1
                if (dist[texiX][texiY] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                    return;
                }
                
                // 각 손님들 중 어느 손님이 택시와 가장 가까운지 확인
                if (dist[texiX][texiY] < minDist) {
                    minDist = dist[texiX][texiY];
                    minCustomerIdx = idx;
                } else if (dist[texiX][texiY] == minDist) {
                    // 최단거리가 같을 경우, 행/열이 작은 손님 선택
                    if (customerInfo[idx][0] < customerInfo[minCustomerIdx][0])
                        minCustomerIdx = idx;
                    else if (customerInfo[idx][0] == customerInfo[minCustomerIdx][0] 
                             && customerInfo[idx][1] < customerInfo[minCustomerIdx][1])
                        minCustomerIdx = idx;
                }
                
                idx++;
            }
            // System.out.printf("선택된 승객: %d\n", minCustomerIdx);
    
            // minCustomerIdx에게로 택시가 이동, 연료 소모
            int[][] nowDist = PtDdist.get(minCustomerIdx);
            totalAmount -= nowDist[texiX][texiY];
    
            if (totalAmount <= 0) {
                System.out.println(-1);
                return;
            }
    
            texiX = customerInfo[minCustomerIdx][0];
            texiY = customerInfo[minCustomerIdx][1];

            // System.out.printf("승객 %d번까지 택시가 이동하고 남은 연료: %d\n", minCustomerIdx, totalAmount);
            
            // 손님을 태우고 목적지로 이동, 연료 소모
            int[][] destDist = DtPdist.get(minCustomerIdx);
            totalAmount -= destDist[texiX][texiY];
    
            if (totalAmount < 0) {
                System.out.println(-1);
                return;
            }
    
            totalAmount += destDist[texiX][texiY] * 2;
    
            texiX = customerInfo[minCustomerIdx][2];
            texiY = customerInfo[minCustomerIdx][3];
    
            // 도착한 승객은 리스트에서 제거
            visited[minCustomerIdx] = true;
            totalCustomers++;

            // System.out.printf("승객 %d번을 완료시키고 남은 연료: %d\n", minCustomerIdx, totalAmount);
            // for (boolean b: visited) System.out.print(b + " ");
            // System.out.println();
            // System.out.println();
        }
        

        // 모든 손님을 이동시키고 연료를 충전했을 때 남은 연료의 양을 출력
        // 이동 도중에 연료가 바닥나서 다음 출발지나 목적지로 이동할 수 없으면 -1
        // 모든 손님을 이동시킬 수 없는 경우에도 -1
        System.out.println(totalAmount);
    }

    static int[][] dijkstra(int startX, int startY) {
        int[][] dist = new int[N][N];
        for (int[] d: dist) Arrays.fill(d, Integer.MAX_VALUE);
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, 0}); //x, y, d(거리)
        dist[startX][startY] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];

            for (int[] mv: move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) continue;

                int newDist = d + 1;

                if (dist[nx][ny] <= newDist) continue;

                q.offer(new int[]{nx, ny, newDist});
                dist[nx][ny] = newDist;
            }
        }

        return dist;
    }
}

/*
## 자료형
1. 각 손님 -> 목적지 dist
    PtDdist.get(0)[][]: 0번째 손님이 모든 위치로 가는 최단거리
2. 목적지 -> 각 손님 dist
    DtPdist.get(0)[][]: 0번째 목적지에서 모든 위치로 가는 최단거리


택시에서 가장 가까이있는 손님에게 먼저 간다
1. (최초 1회) 최초 택시 위치에서 각 손님까지의 dist 도출

각 손님들이 목적지까지 가는 최단거리는 정해져있다
2. 손님을 태우고 목적지까지 가는 dist (PtDdist)

목적지에서 손님을 내리고, 연료를 채워서 다음 손님을 태우러간다 -> 최단 거리에 위치해있는 손님을 태우러간다
3. 각 목적지에서 다음 손님에게 가는 dist (DtPdist)

*/
