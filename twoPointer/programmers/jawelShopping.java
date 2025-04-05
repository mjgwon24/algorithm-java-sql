import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> typeSet = new HashSet<>(Arrays.asList(gems));
        int totalKinds = typeSet.size();
        
        HashMap<String, Integer> jawelMap = new HashMap<>();
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while (right < gems.length) {
            // 오른쪽 보석 추가
            jawelMap.put(gems[right], jawelMap.getOrDefault(gems[right], 0) + 1);
            right++;
            
            // 현재 윈도우가 모든 종류를 포함하면, 왼쪽 줄이기
            while (jawelMap.size() == totalKinds) {
                // 갱신
                if (right - left < minLen) {
                    answer[0] = left + 1;
                    answer[1] = right;
                    minLen = right - left;
                }
                
                // 왼쪽 보석 제거
                jawelMap.put(gems[left], jawelMap.get(gems[left]) - 1);
                if (jawelMap.get(gems[left]) == 0) {
                    jawelMap.remove(gems[left]);
                }
                left++;
            }
        }
        
        return answer;
    }
}
