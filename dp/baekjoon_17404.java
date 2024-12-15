import java.util.*;

class Main {
    static int[][] dp;
    static int[][] cost;
    static int N, firstColor;
    static int maxValue = 1000 * 1000;
    static int answer = 1000 * 1000;
    
    public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N][3];
        cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        // k = 0 -> RED, 1 -> GREEN, 2 -> BLUE로 첫 집을 칠하는 경우
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (i == k) dp[0][i] = cost[0][i];
                else dp[0][i] = maxValue;
            }

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (i != k) answer = Math.min(answer, dp[N-1][i]);
            }
        }

        System.out.println(answer);
    }
}

// 첫 집이 red인 경우 -> 첫 집의 green, blue의 dp값을 무한대로 설정
// 첫 집이 green인 경우 -> 첫 집의 red, blue의 dp값을 무한대로 설정
// 첫 집이 blue인 경우 -> 첫 집의 red, green의 dp값을 무한대로 설정

// 첫 집이 red인 경우 -> 마지막 집을 green, blue로 칠한 dp값 중 최솟값 구하기
// 첫 집이 green인 경우 -> 마지막 집을 red, blue로 칠한 dp값 중 최솟값 구하기
// 첫 집이 blue인 경우 -> 마지막 집을 red, green으로 칠한 dp값 중 최솟값 구하기
