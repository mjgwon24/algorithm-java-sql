import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        // numbers에서 서로 다른 인덱스에 있는 두 수를 뽑아 더해서 만들 수 있는 모든 수 도출
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        List<Integer> list = new ArrayList<>(set);
        list.sort((a, b) -> a - b);
        
        int[] answer = new int[list.size()];
        int idx = 0;
        for (int n: list) {
            answer[idx++] = n;
        }
        return answer;
    }
}
