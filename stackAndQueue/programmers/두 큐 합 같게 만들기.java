import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> dq1 = new ArrayDeque<>();
        Deque<Integer> dq2 = new ArrayDeque<>();
        
        // 두 큐의 총 합 도출
        long sum1 = 0; long sum2 = 0; long sum = 0;
        int max1 = 0; int max2 = 0;
        for (int n: queue1) {
            sum1 += n;
            max1 = Math.max(max1, n);
            dq1.addLast(n);
        }
        for (int n: queue2) {
            sum2 += n;
            max2 = Math.max(max2, n);
            dq2.addLast(n);
        }
        sum = sum1 + sum2;
        
        if (sum % 2 != 0) return -1;
        sum /= 2;
        if (max1 > sum || max2 > sum) return -1;
        // System.out.println(sum1);
        // System.out.println(sum2);
        
        // 두 큐의 합이 같아질때까지
        int answer = 0;
        while (sum1 != sum2) {
            if (answer > (queue1.length + queue2.length) * 2) return -1;
            
            if (sum1 > sum2) {
                // if (dq1.isEmpty()) return -1;
                
                int dq1Cnt = dq1.pollFirst();
                dq2.addLast(dq1Cnt);
                sum1 -= dq1Cnt;
                sum2 += dq1Cnt;
            } else if (sum1 < sum2) {
                // if (dq2.isEmpty()) return -1;
                
                int dq2Cnt = dq2.pollFirst();
                dq1.addLast(dq2Cnt);
                sum1 += dq2Cnt;
                sum2 -= dq2Cnt;
            }
            
            answer++;
        }
        
        // 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수
        // 어떤 방법으로도 각 큐의 원소 합을 같게 만들 수 없는 경우, -1
        return answer;
    }
}

/*
투포인터, 스택
1. 두 큐의 총 합 / 2 -> 하나의 큐의 합 (300,000 * 2 = 600,000)
2. 투포인터로 총 합이 같아질 때 까지
*/
