import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        TreeSet<Integer> ts = new TreeSet<>();
        
        // 서로 다른 인덱스 두 수 합산
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                ts.add(numbers[i] + numbers[j]);
            }
        }
        
        int[] answer = new int[ts.size()];
        int idx = 0;
        while (!ts.isEmpty()) {
            answer[idx++] = ts.pollFirst();
        }
        return answer;
        
    }
}
