import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(); // (1 ≤ K ≤ 45)
        int[][] dp = new int[K + 1][2];

        // 초기화
        // i = 0, A, dp[0][0] = 1, dp[0][1] = 0
        dp[0][0] = 1;

        for (int i = 1; i <= K; i++) {
            // A
            // dp[i - 1][0](A) = a
            // dp[i][0] = dp[i - 1][0] - a, dp[i - 1][1] + a
            int a = dp[i - 1][0];
            dp[i][0] = dp[i - 1][0] - a;
            dp[i][1] = dp[i - 1][1] + a;

            // B
            // dp[i - 1][1](B) = b
            // dp[i][1] = dp[i - 1][0] + b
            int b = dp[i - 1][1];
            dp[i][0] = dp[i][0] + b;

            // System.out.print("i = " + i + ", ");
            // System.out.println(dp[i][0] + " " + dp[i][1]);
            // System.out.println();
        }
        

        // A의 개수와 B의 개수를 공백으로 구분해 출력
        System.out.println(dp[K][0] + " " + dp[K][1]);
    }
}

/***
A(최초 화면) -> B -> BA -> BA B -> BA B BA

[ 규칙 ]
B -> BA
A -> B

dp[i][j]: i번 화면을 눌렀을 때, j = 0) A 개수, j = 1) B 개수, 

// 초기화
// i = 0, A, dp[0][0] = 1, dp[0][1] = 0

i = 1, B, 0 1
dp[0][0](A) 개수만큼 dp[0][0]--, dp[0][1]++
dp[1][0] = dp[0][0] - 1 = 0, dp[1][1] = dp[0][1] + 1 = 1

i = 2, BA, 1 1
dp[1][0](A) 개수만큼 dp[1][0]--, dp[1][1]++
dp[1][1](B) 개수만큼 dp[1][0]++
dp[2][0] = 1, dp[2][1] = 1

i = 3, BAB 1 2
dp[1][0](A) 개수만큼 dp[1][0]--, dp[1][1]++
dp[1][1](B) 개수만큼 dp[1][0]++
dp[2][0] = 1 - 1 + 1 = 1, dp[2][1] = 1 + 1 = 2

***/
