import java.lang.*;
import java.util.*;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);

        int answer = 0;
        int l = 1, r = piles[piles.length - 1];

        while (l <= r) {
            int m = (l + r) / 2;
            long clearTime = 0; // 다 먹는 시간

            // 1. 한 번에 m개의 바나나를 먹으면 다 먹는 시간이 얼마나 걸리는지 도출
            for (int i = 0; i < piles.length; i++) {
                clearTime += piles[i] / m;
                clearTime += piles[i] % m != 0 ? 1 : 0;
            }

            // 2. 다 먹는 시간이 요구하는 시간과 같거나 작을 경우, 정답 갱신하고 한 번에 먹는 바나나 개수 줄이기( r = m - 1 )
            // 3. 다 먹는 시간이 요구하는 시간보다 클 경우, 한 번에 먹는 바나나 개수 늘리기 (l = m + 1)
            if (clearTime <= h) {
                answer = m;
                r = m - 1;
            } else l = m + 1;
        }
        
        return answer;
    }
}

/*
코코는 바나나를 먹는 것을 좋아해요. 
바나나가 n 더미 있습니다. 
i 번째 더미에 복구된 바나나가 있습니다. 
경비원들은 떠났고 h 시간 후에 돌아올 것입니다. 

코코는 매 시간 선택한 바나나 더미에서 k개의 바나나를 먹기로 결정할 수 있습니다. 
더미에 k개보다 적은 바나나가 있을 경우, 그녀는 그 더미에서 모든 바나나를 먹고 해당 시간 동안 더 이상 바나나를 먹지 않습니다. 
코코는 경비원이 돌아오기 전에 바나나를 전부 끝내고 싶어합니다. 
그녀가 h 시간 내에 모든 바나나를 먹을 수 있도록 최소한의 정수 k를 반환하세요.

piles 바나나 더미 [3,6,7,11]
h 경비원이 다시 돌아오는 시간 8
output 4

매 시간 4개 이하의 바나나를 한 더미에서 먹는다

h 시간 내에 한 더미 내에서 먹을 수 있는 바나나 최소 개수 도출

결정 문제 - h시간동안 먹는 바나나 최대 개수
한번에 먹을 수 있는 바나나 개수 최솟값: 1, 최댓값: piles.max

l = 1, r = piles.max
int clearTime = 0; // 다 먹는 시간

1. 한 번에 m개의 바나나를 먹으면 다 먹는 시간이 얼마나 걸리는지 도출
2. 다 먹는 시간이 요구하는 시간과 같거나 작을 경우, 정답 갱신하고 한 번에 먹는 바나나 개수 줄이기( r = m - 1 )
3. 다 먹는 시간이 요구하는 시간보다 클 경우, 한 번에 먹는 바나나 개수 늘리기 (l = m + 1)



 */
