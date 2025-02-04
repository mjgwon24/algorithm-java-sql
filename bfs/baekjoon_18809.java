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
        comb(0, 0, 0);
        System.out.println(result);
    }
    
    // 1. make combination - start energy position / pruning(start)
    // time = C(10, 5) * C(5, 5) / 2 = 252 / 2
    static void comb(int gDepth, int rDepth, int start) {
        if (gDepth == G && rDepth == R) {
            // 2. run spread energy -> make flowers
            spreadEnergy();
            return;
        }
        
        // each care green depth, red depth
        // make on the map - green, red area
        for (int i = start; i < canStartPositions.size(); i++) {
            int[] current = canStartPositions.get(i);
            int x = current[0];
            int y = current[1];
            
            if (!selected[i]) {
                selected[i] = true;
                
                // green 3
                map[x][y] = 3;
                comb(gDepth + 1, rDepth, i + 1);
                
                // red 4
                map[x][y] = 4;
                comb(gDepth, rDepth + 1, i + 1);
                
                // backtracking
                selected[i] = false;
                map[x][y] = 2;
            }
        }
        
    }
    
    // 2. run spread energy -> make flowers
    static void spreadEnergy() {
        int[][][] copy = new int[N][M][2];
        for (int i  = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j][0] = map[i][j];
                copy[i][j][1] = 0;
            }
        }
        
        int flowerCnt = 0;
        Queue<int[]> q = new LinkedList<>();
        
        // queue initialize
        for (int i = 0; i < canStartPositions.size(); i++) {
            if (selected[i]) {
                int[] current = canStartPositions.get(i);
                int x = current[0];
                int y = current[1];
                
                q.offer(new int[]{x, y, copy[x][y][0], 0}); // x, y, type, time
            }
        }
        
        // spread
        // 0 can't spread, 1 can spread, 2 can spread, 3 green energy, 4 red energy, -1 flower
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int type = current[2];
            int time = current[3];
            
            if (copy[x][y][0] == -1) continue;
            
            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (copy[nx][ny][0] == 0 || copy[nx][ny][0] == -1 || copy[nx][ny][0] == type) continue;
                
                if (copy[nx][ny][0] == 1 || copy[nx][ny][0] == 2) {
                    copy[nx][ny][0] = type;
                    copy[nx][ny][1] = time + 1;
                    q.offer(new int[]{nx, ny, type, time + 1});
                    continue;
                } else {
                    // meet different type
                    // flower? or none
                    if (time + 1 == copy[nx][ny][1]) {
                        copy[nx][ny][0] = -1;
                        flowerCnt++;
                    }
                }
            }
            
        }
        
        // if (flowerCnt == 6) {
        //     System.out.println("=== before ===");
        // for (int[] m : map) {
        //     for (int n : m) {
        //         System.out.print(n + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
        // System.out.println("=== spread ===");
        // for (int[][] m : copy) {
        //     for (int[] n : m) {
        //         System.out.print(n[0] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("flowerCnt: " + flowerCnt);
        // System.out.println();
        // }
      
        result = Math.max(result, flowerCnt);
    }
}

// print number of flowers

// 1. make combination - start energy position 
//    caution) match the number of each color - mark on the map. green 3, red 4.
// 2. run spread energy -> make flowers

// 시간제한 2초 (2 * 10^8 번 연산 가능)
