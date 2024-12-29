import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] arr;
    static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = arr[i]; j <= K; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }

        System.out.println(dp[K]);
    }
}

// 1. 상태 정의
// dp[i][j]: i번째 동전까지 탐색했을 때, 금액 j를 만들 수 있는 경우의 수

// 2. 선택지 정의
// 2.1 현재 동전을 포함하지 않고 금액 j를 만드는 경우.
// dp[i][j] = dp[i-1][j]
// 2.2 현재 동전을 포함해서 금액 j를 만드는 경우
// dp[i][j] = dp[i][j-arr[i]]

// 3. 선택지 관계도출
// dp[i][j] = dp[i-1][j] + dp[i][j-arr[i]]

// 4. 초기화
// 금액 0을 만드는 경우
// dp[i][0] = 1
// 아무 동전도 탐색하지 않는 경우
// dp[0][j] = 0 (j > 0)

// 1차원 배열로 최적화
// dp[j]: 금액 j를 만드는 경우의 수
// 외부 루프 i: 동전의 인덱스
// 내부 루프 j: 만들 수 있는 금액의 최솟값 ~ 목표 금액
// dp[j] = dp[j] + dp[j - arr[i]]
// dp[0] = 1
