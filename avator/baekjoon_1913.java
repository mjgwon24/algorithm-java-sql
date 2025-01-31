import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, findN;
    static int[][] arr;
    static int[][] move = {
        {-1, 0}, // top
        {0, 1}, // right
        {1, 0}, // bottom
        {0, -1} // left
    };
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 홀수 자연수 (3 ≤ N ≤ 999)
        findN = Integer.parseInt(br.readLine()); // 위치를 찾고자 하는 N^2 이하 자연수
        arr = new int[N][N];
        
        int startX = N / 2;
        int startY = (N - 1) / 2;
        
        arr[startX][startY] = 1;
        
        int cnt = 1;
        int x = startX;
        int y = startY;
        int innerMaxCnt = 1;
        int isTwo = 0;
        
        while (cnt < N * N) {
            for (int[] mv : move) {
                
                // ((cnt + 1) / 2)
                int innerCnt = 0;
                if (isTwo == 2) {
                    innerMaxCnt++;
                    isTwo = 0;
                }
                
                while (innerCnt < innerMaxCnt) {
                    int nx = x + mv[0];
                    int ny = y + mv[1];
                
                    arr[nx][ny] = arr[x][y] + 1;
                    
                    x = nx;
                    y = ny;
                    cnt++;
                    innerCnt++;
                    if (cnt >= N * N) break;    
                }
                
                isTwo++;
                
                if (cnt >= N * N) break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int a = 0;
        int b = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (findN == arr[i][j]) {
                    a = i + 1;
                    b = j + 1;
                } 
                
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
        System.out.println(a + " " + b);
    }
}

// N개의 줄 - 걸쳐 달팽이 표 출력. 
// N + 1번쨰 줄 - 입력받은 자연수 좌표 위치 출력

// N = 1, arr[0][0] = 1, 0/2 = 0
// N = 2, arr[1][0] = 1, 1/2 = 0
// N = 3, arr[1][1] = 1, 2/2 = 1
// N = 4, arr[2][1] = 1, 3/2 = 1
// N = 5, arr[2][2] = 1, 4/2 = 2
// N = 6, arr[3][2] = 1

// start, arr[N / 2][(N - 1) / 2] = 1
// cnt = 1 => 1 ((cnt + 1) / 2)
// cnt = 2 => 1
// --inner--
// cnt = 3 => 2
// cnt = 4 
// --inner--
// cnt = 5 => 2
// cnt = 6 
// --inner--
// cnt = 7 => 3
// cnt = 8 
