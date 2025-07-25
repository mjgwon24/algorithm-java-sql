import java.util.*;

class Solution {
    static Set<Integer>[] dp = new HashSet[9];
    
    public int solution(int N, int number) {
        int sum = 0;
        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
            sum = sum * 10 + N;
            dp[i].add(sum);
            if (dp[i].contains(number)) return i;
        }
        
        // +,-,*,/ 연산
        // dp[2] = dp[1] + dp[1], dp[1] - dp[1], dp[1] * dp[1], dp[1] / dp[1]
        // dp[3] = dp[1] + dp[2], ...
        // dp[4] = dp[1] + dp[3], dp[2] + dp[2]
        // dp[5] = dp[1] + dp[4], dp[2] + dp[3]
        // ...
        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (int a : dp[j]) {
                    for (int b : dp[i - j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                        if (dp[i].contains(number)) return i;
                    }
                }
            }
        }
        
        
        // N 사용횟수의 최솟값을 return
        return -1;
    }
}

/*
- 55 : N, NN, NNN, ... 표현
- N과 사칙연산만 사용해서 표현 할 수 있는 방법 : 사칙연산 계산
- number는 1 이상 32,000 이하 : 숫자가 매우 큼.
- 최솟값이 8보다 크면 -1을 return : 8개까지는 완전 탐색 가능. Set<Integer>[] dp (idx : N개수)
*/
