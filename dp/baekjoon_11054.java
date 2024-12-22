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
        arr = new int[N + 1];
        dp = new int[N + 1][3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
                if (arr[i] < arr[j]) dp[i][2] = Math.max(dp[i][2], Math.max(dp[j][1], dp[j][2]) + 1);
            }
        }

        int max = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 3; j++) {
                if (max < dp[i][j]) max = dp[i][j];
            }
        }
        System.out.println(max);
    }
}

// 1. 상태 정의
// dp[i][k] = i번째 숫자까지 탐색했을 경우, 증가하는지 감소하는지(k) 수열의 길이.
// k = 1 : 증가하고있는 경우
// k = 2 : 감소하고있는 경우. 

// 2. 선택지 정의
// 2.1 수열의 길이 증가 
// - 현재 증가하고 있는 경우라면, 이전에 증가하고있을때만 +1.
// - 현재 감소하고 있는 경우라면, 이전에 증가하고있을 때, 감소하고있을 때 모두 +1.
// 2.2 수열의 길이 증가하지 않음
// - 현재 증가하고있는 경우, 이전보다 값이 큰 경우에만 가능
// - 현재 감소하고있는 경우, 이전보다 값이 작은 경우에만 가능

// 3. 선택지 관계 도출 (이전 관계와 고려)
// 3.1 수열의 길이 증가 
// arr[i] > arr[j], dp[i][1] = max(dp[i][1], dp[j][1] + 1)
// arr[i] < arr[j], dp[i][2] = max(dp[i][2], max(dp[j][1], dp[j][2]) + 1)
// 3.2 수열의 길이 증가하지 않음

// 4. 초기화.
// dp[1][1] = dp[1][2] = 1
