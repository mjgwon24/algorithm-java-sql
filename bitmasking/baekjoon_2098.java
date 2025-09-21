import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static int start = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시의 수
        W = new int[N][N];
        dp = new int[N][1 << N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        
        // 외판원 순회에 필요한 최소 비용 출력
        System.out.println(tsp(1, 0));
    }

    static int tsp(int visited, int cur) {
        // 전부 순회하면 현재 위치에서 출발 위치의 비용 반환
        if (visited == (1 << N) - 1) {
            if (W[cur][start] == 0) return 987654321;
            return W[cur][start];
        }

        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }

        int min = 987654321;
        for (int next = 0; next < N; next++) {
            // 다음 경로가 이미 방문한 경로면 패스
            if ((visited & (1 << next)) != 0) continue;
            if (W[cur][next] == 0) continue;

            int cost = W[cur][next] + tsp(visited | (1 << next), next);
            min = Math.min(min, cost);
        }

        dp[cur][visited] = min;
        return min;
    }
}
