import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] customers, prefixSum;
    static int maxSelectCnt;
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 객차 수 (50000 이하)
        customers = new int[N + 1]; // 객차별 손님 수 (100 이하)
        prefixSum = new int[N + 1]; //누적합
        dp = new int[4][N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + customers[i];
        }

        // 소형 기관차가 최대로 끌 수 있는 객차 수
        maxSelectCnt = Integer.parseInt(br.readLine());

        // dp[i][j] = Math.max(dp[i][j - 1], 
        //            (prefixSum[j] - prefixSum[j - maxSelectCnt]) + dp[i - 1][j - maxSelectCnt])
        // j = 1(maxSelectCnt * 0)부터 가능
        for (int i = 1; i <= 3; i++) {
            for (int j = maxSelectCnt * i; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], 
                                    (prefixSum[j] - prefixSum[j - maxSelectCnt]) + dp[i - 1][j - maxSelectCnt]);
                // System.out.printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
            }
            // System.out.println();
        }
        

        // 소형 기관차 3대를 이용하여 최대로 운송할 수 있는 손님 수
        System.out.println(dp[3][N]);
    }
}

/***
[ 요구사항 ]
소형 기관차 3대를 이용하여 최대로 운송할 수 있는 손님 수 도출

[ 조건 ]
1. 3대의 소형 기관차가 최대로 끌 수 있는 객차의 수는 서로 같다. (maxSelectCnt)
2. 최대한 많은 손님을 목적지까지 운송
3. 각 소형 기관차는 번호가 연속적으로 이어진 객차를 끌게 한다.

[ 입력 ]
7
35 40 50 10 30 45 60
2

[ 출력 ]
240

- prefixSum
35 75 125 135 165 210 270

- i번째 객차를 가장 마지막 객차로 선택한 경우
수용 손님 수 = prefixSum[i] - prefixSum[i - maxSelectCnt]

창 크기 = maxSelectCnt (2)

dp[i][j]: i인덱스의 소형 기관차를 선택한 경우, j인덱스를 끝 지점으로 객차를 선택한 경우 운송 손님 최댓값

[ 선택지 정의 ]
(1) 현재 기관차가 j번째 객차를 선택하지 않음 -> j - 1번째 객차까지 중 최댓값
    dp[i][j] = dp[i][j - 1]
(2) 현재 기관차가 j번째 객차를 선택 -> 현재 기관차의 수용인원 + 이전 기관차의 최대 수용 인원
    dp[i][j] = (prefixSum[j] - prefixSum[j - maxSelectCnt]) + dp[i - 1][j - maxSelectCnt]
=> dp[i][j] = Math.max(dp[i][j - 1], (prefixSum[j] - prefixSum[j - maxSelectCnt]) + dp[i - 1][j - maxSelectCnt])

j = 2(maxSelectCnt * 1)부터 가능
dp[1][1] = 0
dp[1][2] = prefixSum[1] - prefixSum[-1] (x) = 75
dp[1][3] = prefixSum[2] - prefixSum[0] = 90
...

0번째 기관차가 가장 첫번째것을 선택한 경우를 고려해 j = 3(maxSelectCnt * 1 + maxSelectCnt - 1)부터 가능
dp[2][1] = 0
dp[2][2] = 0
dp[2][3] = 0
dp[2][4] = Math.max(dp[1][2], 60 + dp[0][1] 75) = 135
dp[2][5] = Math.max(dp[1][3] 135, 40 + dp[0][2] 90) = 135


0, 1번째 기관차가 가장 첫번째것을 선택한 경우를 고려해 j = 5(maxSelectCnt * 2 + maxSelectCnt - 1)부터 가능
dp[3][6] = Math.max(dp[2][4], 75 + dp[1][3] 135) = 210
dp[3][7] = Math.max(dp[2][5] 210, 105 + dp[1][4] 135) = 240


***/
