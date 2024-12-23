import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                else dp[i] = Math.max(arr[i], dp[i]);
                // System.out.printf("j = %d, dp[%d] = %d\n", j, i, dp[i]);
            }
        }

        int max = dp[1];
        for (int n : dp) {
            if (max < n) max = n;
        }
        System.out.println(max);
    }
}

// 1. 상태정의
// dp[i]: i번째까지 탐색했을 경우 최대 합.

// 2. 선택지 정의 (이전 상태와 관계 고려)
// 2.1 증가하는 수열의 과정으로 판단하여 현재값을 이전까지의 합에 더하기
// 2.2 증가하는 수열이 아니므로 현재값이 초기 합이 됨

// 3. 선택지 관계 도출
// 3.1 증가하는 수열의 과정으로 판단하여 현재값을 이전까지의 합에 더하기
// (arr[i] > arr[j]) dp[i] = max(dp[i], dp[j]) + arr[i]
// 3.2 증가하는 수열이 아니므로 현재값이 초기 합이 됨
// (arr[i] <= arr[j]) dp[i] = arr[i]

// 4. 초기값 정의
// dp[1] = arr[1]

// dp[1] = 1
// dp[2] = 102
// j = 1, dp[3] = 3
// j = 2, dp[3] = 3
// j = 1, dp[4] = 201
// j = 2, dp[4] = dp[2] + arr[4] = 302

