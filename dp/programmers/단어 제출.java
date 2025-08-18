import java.util.*;

class Solution {
    static int[] dp;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String[] strs, String t) {
        int N = t.length();
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        // i 탐색 위치
        for (int i = 1; i <= N; i++) {
            for (String str : strs) {
                // str의 글자수가 길면 패스
                if (i - str.length() < 0) continue;
                
                // str 글자수 전 dp가 존재하지 않으면 패스 (이전 경로가 존재하지 않음을 의미)
                if (dp[i - str.length()] == Integer.MAX_VALUE) continue;
                
                // 위치와 문자가 일치하면 dp 갱신
                if (t.substring(i - str.length(), i).equals(str))
                    dp[i] = Math.min(dp[i], dp[i - str.length()] + 1);
            }
        }

        // 문장을 완성하기 위해 사용해야 하는 단어조각 개수 최솟값 반환
        // 문장 완성이 불가능하다면 -1 반환
        return dp[N] == Integer.MAX_VALUE ? -1 : dp[N];
    }
}
