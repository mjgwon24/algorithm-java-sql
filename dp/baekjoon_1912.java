import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    static int max;
    
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
        max = dp[1];
        
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}

// 상태정의
// dp[i]: i번째 배열까지 탐색했을 경우의 최대 합.
// 선택지 1. 이전 부분합에 현재 값을 추가
// dp[i-1] + arr[i]
// 선택지 2. 현재 값만을 시작으로 새로운 부분합 구성
// arr[i]
// dp[i] = 1,2 중 더 큰 값 선택
// 초기화
// dp[1] = arr[1]
