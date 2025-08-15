import java.util.*;

class Solution {
    static int N;
    static Set<String> answerSet = new HashSet<>();

    /*
    정수들을 더하거나 빼서 target 만들기
    */
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        
        dfs(0, 0, target, new StringBuilder(), numbers);
        
        // target을 만드는 방법의 수 도출
        return answerSet.size();
    }
    
    static void dfs(int cur, int idx, int target, StringBuilder sb, int[] numbers) {
        if (idx == N) {
            if (target == cur) answerSet.add(sb.toString());
            return;
        }
        
        int n = numbers[idx];
    
        // +
        StringBuilder temp1 = new StringBuilder(sb.toString());
        temp1.append("+").append(n);
        dfs(cur + n, idx + 1, target, temp1, numbers);
        
        // -
        StringBuilder temp2 = new StringBuilder(sb.toString());
        temp2.append("-").append(n);
        dfs(cur - n, idx + 1, target, temp2, numbers);
    }
}
