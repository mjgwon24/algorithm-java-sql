import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<Integer>[] childrens;
    static List<Integer>[] parents;
    static int[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        Arrays.fill(dp, -1);
        
        childrens = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            childrens[i] = new ArrayList<>();
        }

        parents = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            parents[i] = new ArrayList<>();
        }
        
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());

            if (v == -1) continue;
            
            childrens[v].add(i);
            parents[i].add(v);
        }
        
        // 모든 소식을 전하는데 걸리는 최솟값 출력
        System.out.println(dfs(0));
    }
    
    static int dfs(int cur) {    
        if (dp[cur] != -1) return dp[cur];
        
        // 리프 노드일 경우
        if (childrens[cur].isEmpty()) {
            dp[cur] = 0;
            return 0;
        }

        // 자식이 있다면, 각 자식들이 본인의 자식들에게 전파하는 시간 도출
        List<int[]> times = new ArrayList<>();
        for (int next : childrens[cur]) {
            times.add(new int[]{next, dfs(next)});
        }
        
        // 시간이 오래걸리는 자식부터 먼저 탐색
        times.sort((a, b) -> b[1] - a[1]);
        
        int time = 0;
        for (int i = 0; i < times.size(); i++) {
            time = Math.max(time, dfs(times.get(i)[0]) + i + 1);
        }

        dp[cur] = time;
        return time;
    }
}
