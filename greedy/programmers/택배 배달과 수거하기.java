import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 가장 뒷부분에 있는 인덱스
        int dIdx = n - 1;
        int pIdx = n - 1;
        
        while (dIdx >= 0 || pIdx >= 0) {
            // 값이 존재하는 인덱스 갱신
            while (dIdx >= 0 && deliveries[dIdx] == 0) dIdx--;
            while (pIdx >= 0 && pickups[pIdx] == 0) pIdx--;
            
            if (dIdx < 0 && pIdx < 0) break;
            
            answer += (Math.max(dIdx, pIdx) + 1) * 2L; // 1-index
            
            // System.out.printf("[start]\n");
            // System.out.printf("dIdx: %d, pIdx: %d\n", dIdx, pIdx);
            
            // 택배/수거에 사용되는 트럭 용량
            int dWeights = 0;
            int pWeights = 0;
            
            // 택배 배달
            while (dIdx >= 0 && dWeights < cap) {
                // 배달 진행
                if (dWeights + deliveries[dIdx] <= cap) {
                    dWeights += deliveries[dIdx];
                    deliveries[dIdx] = 0;
                    dIdx--;
                } else {
                    deliveries[dIdx] -= (cap - dWeights);
                    dWeights = cap;
                }
            }
            
            // 수거 진행
            while (pIdx >= 0 && pWeights < cap) {
                // 배달 진행
                if (pWeights + pickups[pIdx] <= cap) {
                    pWeights += pickups[pIdx];
                    pickups[pIdx] = 0;
                    pIdx--;
                } else {
                    pickups[pIdx] -= (cap - pWeights);
                    pWeights = cap;
                }
                
                // System.out.printf("(수거 진행) pIdx = %d, pWeights = %d \n", pIdx, pWeights);
            }
            
            // System.out.println("deliveries: " + Arrays.toString(deliveries));
            // System.out.println("pickups: " + Arrays.toString(pickups));
            // System.out.println("answer: " + answer);
        }
        
        return answer;
    }
}
