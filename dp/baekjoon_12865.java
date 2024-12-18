import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[] V;
    static int[] W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        V = new int[N+1];
        W = new int[N+1];
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j - W[i] >= 0) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - W[i]] + V[i]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);
    }
}

/** 점화식 도출
 *  1. 상태 정의
 *   dp[i][j]: i 번째 물건까지 탐색했을 때, j 무게 만큼 소지 가능한 상태에서의 최대 가치값
 * 2. 선택지 정리 및 관계 도출
 *   선택1: 현재 인덱스의 물건을 담는다 -> i번째 물건까지 탐색했을 때 도달할 수 있는 가치값1 = 이전 물건까지 탐색한 경우 중 현재 물건을 담기 전 무게에서 가능했던 최대 가치값 + 현재 물건의 가치
 *   선택2: 현재 인덱스의 물건을 담지 않는다 -> i번째 물건까지 탐색했을 때 도달할 수 있는 가치값2 = 현재 물건보다 한 단계 이전 인덱스까지 탐색했던 최대 가치값
 *   선택지 중 최댓값 선택 -> 선택지 둘 중 더 큰 값 선택
 * 3. 선택지 관계 도출
 *   선택1: 현재 인덱스의 물건을 담는다 -> dp[i][j] = dp[i-1][j - W[i]] + V[i]
 *   선택2: 현재 인덱스의 물건을 담지 않는다 -> dp[i][j] = dp[i-1][j]
 *   선택지 중 최댓값 선택 -> dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - W[i]] + V[i])
 * 4. 초기값 설정
 *   아무 물건도 고려하지 않는 경우, dp[0][j] = 0
 *   배낭에 넣을 수 있는 최대 무게가 0인 경우, dp[i][0] = 0
 ** /

 /**
 * 대입해보기. 최대 용량 7kg, W[] = {6, 4, 3, 5}, V[] = {13, 8, 6, 12}
 * - 초기값 설정
 *     dp[i][0] = 0, dp[0][j] = 0
 * - 1번째 물건을 탐색하고, 배낭 용량 1~5Kg인 경우 최대 가치 => 못담음. 이전 탐색에서 최대 가치 가져옴.
 *     dp[1][1~5] = 0
 * - 1번째 물건을 탐색하고, 배낭 용량 6~7Kg인 경우 최대 가치 => 담음. 이전 탐색 최대 가치 vs 담을 경우 가치 비교
 *     dp[1][6~7] = dp[0][j-6] + V[1] = 13
 * - 2번째 물건을 탐색하고, 배낭 용량 1~3Kg인 경우 최대 가치 => 못담음. 이전 탐색에서 최대 가치 가져옴.
 *     dp[2][1~3] = dp[1][1~3] = 0
 * - 2번째 물건을 탐색하고, 배낭 용량 4~7Kg인 경우 최대 가치 => 담음. 이전 탐색 최대 가치 vs 담을 경우 가치 비교
 *     dp[2][4] = max(dp[1][4], dp[1][4-4] + V[2]) = 8
 *     dp[2][5] = max(dp[1][5], dp[1][5-4] + V[2]) = 8
 *     dp[2][6] = max(dp[1][6], dp[1][6-4] + V[2]) = 13
 *     dp[2][7] = max(dp[1][7], dp[1][7-4] + V[2]) = 13
 * - 3번째 물건을 탐색하고, 배낭 용량 1~2Kg인 경우 최대 가치 => 못담음. 이전 탐색에서 최대 가치 가져옴.
 *     dp[3][1~2] = dp[2][1~2] = 0
 * - 3번째 물건을 탐색하고, 배낭 용량 3~7Kg인 경우 최대 가치 => 담음. 이전 탐색 최대 가치 vs 담을 경우 가치 비교
 *     dp[3][3] = max(dp[2][3], dp[2][0] + V[3]) = 6
 *     dp[3][4] = max(dp[2][4], dp[2][1] + V[3]) = 8
 *     dp[3][5] = max(dp[2][5], dp[2][2] + V[3]) = 8
 *     dp[3][6] = max(dp[2][6], dp[2][3] + V[3]) = 13
 *     dp[3][7] = max(dp[2][7], dp[2][4] + V[3]) = 14
 * - 4번째 물건을 탐색하고, 배낭 용량 1~4Kg인 경우 최대 가치 => 못담음. 이전 탐색에서 최대 가치 가져옴.
 *     dp[4][1~4] = dp[3][1~4] = 0, 0, 6, 8
 * - 4번째 물건을 탐색하고, 배낭 용량 5~7Kg인 경우 최대 가치 => 담음. 이전 탐색 최대 가치 vs 담을 경우 가치 비교
 *     dp[4][5] = max(dp[3][5], dp[3][0] + V[4]) = 8
 *     dp[4][6] = max(dp[3][6], dp[3][1] + V[4]) = 13
 *     dp[4][7] = max(dp[3][7], dp[3][2] + V[4]) = 14
 */
