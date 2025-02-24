import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int i = 0; i < N; i++) {
            // System.out.println("i = " + i);
            if (arr[i] == 0) continue; // 점프 불가능한 칸
            if (dp[i] == -1) continue; // 도달 불가능한 칸

            for (int j = i + 1; j <= i + arr[i]; j++) {
                if (j >= N) break;

                // 첫 방문
                if (dp[j] == -1) dp[j] = dp[i] + 1;
                else dp[j] = Math.min(dp[j], dp[i] + 1);
                // System.out.println("dp[" + j + "] = " + dp[j]);
            }

            // System.out.println("dp[" + i + "] = " + dp[i]);
            // System.out.println();
            // System.out.println();
        }
        

        // 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력
        // 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력
        System.out.println(dp[N - 1]);
    }
}

// dp[i]: i 위치로 도달하는데 필요한 점프 횟수
// 초기화 - dp[0] = 0, Arrays.fill(dp, -1)

// 1 2 0 1 3 2 1 5 4 2
// i = 0, arr[0] = 1, dp[i + arr[0]] = dp[i] + 1, dp[1] = 1
// i = 1, arr[1] = 2, dp[i + arr[1]] = dp[i] + 1 = dp[1] + 1, dp[2] = 2, dp[3] = 2
// i = 2, arr[2] = 0, if arr[i] == 0, continue;
// i = 3, arr[3] = 1, dp[i + arr[3]] = dp[i] + 1 = dp[3] + 1, dp[4] = 3
// ...
// dp[9] = 5

// 0 0 5 => -1
// 1 1 0 1 => -1
// 0 => 1
// 0 0 0 0 => -1
// 5 1 1 1 1 => 1
