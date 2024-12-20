import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n;
    static int[] arr;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1][1] = arr[1];
        
        if (n >= 2) {
            dp[2][1] = arr[2];
            dp[2][2] = dp[1][1] + arr[2];
        }

        for (int i = 3; i <= n; i++) {
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + arr[i];
            dp[i][2] = dp[i-1][1] + arr[i];
        }
        
        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}

// 마지막 계단부터 밟기. 총 점수의 최댓값 구하기.
// 1. 상태 정의
// dp[i][j]: i번째 계단까지 탐색, 연속해서 j개의 계단을 밟았을 경우 얻을 수 있는 최대 점수
// j = 1: 계단을 연속해서 밟지 않은 경우(초기 계단 or 이전에 2칸을 뛴 경우)
// j = 2: 계단을 연속해서 밟은 경우. 이 경우에는 다음 움직임때 무조건 2칸을 올라가야 함.

// 2. 선택지 정의
// 1) 한칸 움직임 - 이전 계단에서 연속해서 밟기때문에 j는 2가 된다. j가 2인 경우의 이전 계단은 밟을 수 없다.
// 2) 두칸 움직임 - 연속해서 계단을 밟는것이 아니기 때문에 j는 1로 초기화

// 3. 선택지 관계 도출
// 1) 한칸 움직임 
// => dp[i][2] = dp[i-1][1] + arr[i]
// 2) 두칸 움직임
// => dp[i][1] = max(dp[i-2][1], dp[i-2][2]) + arr[i]
// 3) 최종 관계
// => dp[i] = max(dp[i][1], dp[i][2])

// 4. 초기값 정의
// dp[1][1] = arr[1], dp[1][2] = 0 (불가능)
// dp[2][1] = arr[2], dp[2][2] = dp[1][1] + arr[2]

