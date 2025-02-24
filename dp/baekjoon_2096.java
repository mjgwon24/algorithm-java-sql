import java.util.*;
import java.lang.*;
import java.io.*;

// 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 100,000
        boolean allZero = true;

        int[][] arr = new int[N][3];
        StringTokenizer st;
        // 숫자가 세 개씩
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) allZero = false;
            }
        }

        if (allZero) {
            System.out.println("0 0");
            return;
        }

        int[][][] dp = new int[N][3][2];

        // 초기화
        for (int i = 0; i < 3; i++) {
            dp[0][i][0] = arr[0][i];
            dp[0][i][1] = arr[0][i];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][0]) + arr[i][0];
            dp[i][0][1] = Math.min(dp[i - 1][0][1], dp[i - 1][1][1]) + arr[i][0];

            dp[i][1][0] = Math.max(dp[i - 1][0][0], Math.max(dp[i - 1][1][0], dp[i - 1][2][0])) + arr[i][1];
            dp[i][1][1] = Math.min(dp[i - 1][0][1], Math.min(dp[i - 1][1][1], dp[i - 1][2][1])) + arr[i][1];

            dp[i][2][0] = Math.max(dp[i - 1][1][0], dp[i - 1][2][0]) + arr[i][2];
            dp[i][2][1] = Math.min(dp[i - 1][1][1], dp[i - 1][2][1]) + arr[i][2];
        }
        
        int max = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            if (max < dp[N-1][i][0]) max = dp[N-1][i][0];
            if (min > dp[N-1][i][1]) min = dp[N-1][i][1];
        }

        // 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력
        // 점수: 원룡이가 위치한 곳의 수의 합
        System.out.println(max + " " + min);
    }
}

// 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작
// 그리고 다음 줄로 내려가는데, 
// 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다
// 숫자의 크기는 이동 제약에 관련이 없음!!!!

// dp[i][j][0]: (i, j)위치에서 얻을 수 있는 최댓값
// dp[i][j][1]: (i, j)위치에서 얻을 수 있는 최솟값

// 1 2 3
// 4 5 6
// 4 9 0

// i = 0, 초기화
// dp[0][0][0] = arr[0][0] = 1
// dp[0][0][1] = arr[0][0] = 1
// dp[0][1][0] = arr[0][1] = 2
// dp[0][1][1] = arr[0][1] = 2
// dp[0][2][0] = arr[0][2] = 3
// dp[0][2][1] = arr[0][2] = 3

// i = 1
// 최댓값, dp[1][0][0] = max(dp[0][0][0], dp[0][1][0]) + arr[1][0] = 2 + 4 = 6
// 최솟값, dp[1][0][1] = min(dp[0][0][1], dp[0][1][1]) + arr[1][0] = 1 + 4 = 5
// 최댓값, dp[1][1][0] = max(dp[0][0][0], dp[0][1][0], dp[0][2][0]) + arr[1][1] = 3 + 5 = 8
// 최솟값, dp[1][1][1] = min(dp[0][0][1], dp[0][1][1], dp[0][2][0]) + arr[1][1] = 1 + 5 = 6
// 최댓값, dp[1][2][0] = max(dp[0][1][0], dp[0][2][0]) + arr[1][2] = 3 + 6 = 9
// 최솟값, dp[1][2][1] = min(dp[0][1][1], dp[0][2][1]) + arr[1][2] = 2 + 6 = 8

// ...
