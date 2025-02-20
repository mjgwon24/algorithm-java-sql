import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N, M;
    static List<Integer>[] subordinates; // 부하 리스트
    static int[] compliments; // 내가 칭찬받은 양 리스트
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 회사 직원 수 (2 ≤ N, M ≤ 10ˆ5)
        M = Integer.parseInt(st.nextToken()); // 최초 칭찬 횟수 (2 ≤ N, M ≤ 10ˆ5)

        subordinates = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            subordinates[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            // 상사 번호 (중복 가능)
            int n = Integer.parseInt(st.nextToken()); // 상사
            if (n == -1) n = 0; // 상사는 subordinates[0]
            subordinates[n].add(i);
        }

        compliments = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 상사에게 칭찬받은 직원 번호
            int w = Integer.parseInt(st.nextToken()); // 칭찬 수치

            // 칭찬값은 합산됨
            compliments[n] += w;
        }

        dp = new int[N + 1];

        // 칭찬 dfs
        dfs(1, 0);

        // 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    static void dfs(int start, int master) {
        // 사장(START = 1)일 경우, 칭찬받지 않음
        if (start == 1) {
            // 부하로 넘어감
            for (int n : subordinates[start]) {
                dfs(n, start);
            }
            return;
        }

        // 칭찬을 받지 않은 경우
        if (compliments[start] == 0) dp[start] = dp[master];
        // 상사에게 칭찬을 받음
        else dp[start] = dp[master] + compliments[start];

        for (int n : subordinates[start]) {
            dfs(n, start);
        }
    }
}

// 상사에게 칭찬을 받지 않음
// => dp[i] = dp[직속상사]
// 상사에게 칭찬을 받음
// => dp[i] = dp[직속상사] + ∑ compliments[i]
