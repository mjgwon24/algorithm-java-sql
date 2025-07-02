import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long l = 0; // 최소 시간
        long r = 400_000_000_000_000L; // 최대 시간 (최악: 10^9 양을 1 씩 편도 이동시간 10^5(왕복 *2)(금,은따로 *2))
        int T = t.length; // 트럭 개수
        long answer = -1;
        
        while (l <= r) {
            long m = (l + r) / 2; // 현재 시간
            long totalGold = 0, totalSilver = 0, totalAll = 0; // 옮김 현황
            
            // 모든 트럭 순회하며 현재 시간동안 전달할 수 있는 자원 도출
            for (int i = 0; i < T; i++) {
                long cnt = m / (t[i] * 2); // 왕복으로 돌 수 있는 횟수
                if (m % (t[i] * 2) >= t[i]) cnt++; // 시간 남으면 편도 추가
                
                // 전달할 수 있는 최대 자원량 도출
                long max = cnt * w[i];
                long gold = Math.min(g[i], max); // 도출할 수 있는 최대 금 (10)
                long silver = Math.min(s[i], max); // 도출할 수 있는 최대 은 (10)
                long total = Math.min(g[i] + s[i], max); // 도출할 수 있는 최대 금+은 (7+3)
                
                // 옮김 현황 갱신
                totalGold += gold; totalSilver += silver; totalAll += total;
                // System.out.printf("최대 횟수: %d, 시간: %d, 옮김 현황: G %d, S %d, T %d\n", cnt, m, totalGold, totalSilver, totalAll);
            }
            
            // 옮김이 완료되었다면, 정답 갱신하고 탐색 범위 축소
            if (totalGold >= a && totalSilver >= b && totalAll >= a + b) {
                answer = m;
                r = m - 1;
            } else l = m + 1;
        }

        // 금, 은 전달할 수 있는 최소 시간 반환
        return answer;
    }
}

/*
- 도시를 짓기 위해 금 a, 은 b 필요
- 도시 i에 있는 자원: 금 g[i] 보유, 은 s[i] 보유, 편도 이동 시간 t[i], 최대 w[i] 양 운반 가능




*/
