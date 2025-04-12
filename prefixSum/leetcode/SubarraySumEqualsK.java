import java.lang.*;
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        
        // 맵 초기화 - 첫 sum은 0이고, 1개 있음
        sumCountMap.put(0, 1);

        int sum = 0;
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sumCountMap.containsKey(sum - k)) {
                answer += sumCountMap.get(sum - k);
            }

            sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
        }

        return answer;
    }
}

/***
int[] nums 
int k 찾아야하는 부분배열의 합

[ex]
nums [1,1,1]
k 2
output: 2

sum[j] - sum[i] = k 가 되는 개수 찾기
sum[j] - k = sum[i]
=> j 이전의 부분합 sum[i]중, sum[j] - k가 되는 i의 개수 도출

[필요 변수]
• HashMap<Integer, Integer> sumCountMap
    • key: j 인덱스보다 작은 i 중, 누적합 sum[i]
    • value: sum[i](key)의 개수
 */
