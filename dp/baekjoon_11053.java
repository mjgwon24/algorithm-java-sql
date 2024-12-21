import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    int maxN = 0;
                    for (int n : dp[j]) {
                        if (maxN < n) maxN = n;
                    }
                    
                    dp[i][j] = maxN + 1;
                }
                else dp[i][j] = 1;
            }
        }

        int max = 1;
        for (int i = 1; i <= N; i++) {
            for (int num : dp[i]) {
                if (max < num) max = num;
            }
        }
        
        
        System.out.println(max);
    }
}

// 증가하는 수열의 길이 출력
// 1. 상태 정의
// dp[i][j] : i번째까지 탐색했을 경우, j번째 수가 가장 큰 수일 경우의 증가 수열 길이

// 2. 선택지 정의
// 2.1 증가하는 수의 초기값으로 선택하는 경우 - j가 i번째 수보다 크거나 같은 경우
// 2.2 증가하는 수의 과정으로 선택하는 경우 - j가 i번째 수보다 작을 경우

// 3. 선택지 관계 도출
// 3.1 증가하는 수의 초기값으로 선택하는 경우
// dp[i][j] = 1
// 3.2 증가하는 수의 과정으로 선택하는 경우
// dp[i][j] = dp[j][j-1] + 1

// 4. 초기값 설정
// dp[1][] = 1

// i = 2, j = {1}, arr[i] = 20
// j = 1, if (arr[1] < arr[2]) dp[2][1]] = dp[1][0] + 1 = 2

// i = 3, j = {1, 2}, arr[i] = 10
// j = 1, if (arr[1] >= arr[3]) dp[3][1] = 1
// j = 2, dp[3][2] = 1

// i = 4, j = {1,2,3}, arr[i] = 30
// j = 1, if (arr[1] < arr[4]) dp[4][1] = dp[1][0] + 1 = 2
// j = 2, dp[4][2] = dp[2][1] + 1 = 3
// j = 3, dp[4][3] = dp[3][2] + 1 = 2

// i = 5, j = {1,2,3,4}, arr[i] = 20
// j = 1, (arr[1] < arr[5]) dp[5][1] = dp[1][0] + 1 = 2
// j = 2, (arr[2] >= arr[5]) dp[5][2] = 1
// j = 3, (arr[3] < arr[5]) dp[5][3] = dp[3][2] + 1 = 2
// j = 4, dp[5][4] = 1

// i = 6, j = {1,2,3,4,5}, arr[i] = 50
// j = 1, (arr[1] < arr[6]) dp[6][1] = dp[1][0] + 1 = 2
// j = 2, 
// j = 3,
// j = 4, (arr[4] < arr[6]) dp[6][4] = max(dp[4]) + 1 = 3
// j = 5, (arr[5] < arr[6]) dp[6][5] = dp[5][4] + 1 = 3
