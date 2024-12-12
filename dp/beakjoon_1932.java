import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                // 맨 처음, 맨 끝 값 초기화
                if (j == 0) {
                    arr[i][j] = arr[i - 1][j] + arr[i][j];
                } else if (j == i) {
                    arr[i][j] = arr[i - 1][j-1] + arr[i][j];
                } else {
                    arr[i][j] = Math.max(arr[i-1][j-1] + arr[i][j], arr[i-1][j] + arr[i][j]);
                }
            }
        }

        int max = arr[N-1][0];
        for (int i = 0; i < N; i++) {
            if (max < arr[N-1][i]) {
                max = arr[N-1][i];
            }
        }

        System.out.println(max);
    }
}

// max = 7 + 3 + 8 + 7 + 5
// possible approach: [i][j]일 경우, [i+1][j], [i+1][j+1] 접근 가능

// 7
// 10 15
// 11 max(11,16) 15
// 2 7 4 4
// 4 5 2 6 5

// arr[0][0] = 7
// arr[1][0] = arr[0][0] + 3, arr[1][1] = arr[0][0] + 8
// arr[2][0] = arr[1][0] + 8, arr[2][1] = max(arr[1][0] + 1, arr[1][1] + 1), arr[1][1] + 0
// 2 7 4 4
// 4 5 2 6 5
