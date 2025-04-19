import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int t = 0; t < commands.length; t++) {
            int i = commands[t][0]; // 2
            int j = commands[t][1]; // 5
            int k = commands[t][2]; // 3
            
            // 1. i부터 j까지 자르기
            int[] arr = new int[j - i + 1]; // 4
            int idx = 0;
            
            // 1, 2, 3, 4
            for (int tt = i - 1; tt < j; tt++) {
                arr[idx++] = array[tt];
            }
            
            // 2. 정렬
            Arrays.sort(arr);
            
            // 3. k번째 숫자 넣기
            answer[t] = arr[k - 1];
        }
        
        
        
        
        return answer;
    }
}

/***
array [1, 5, 2, 6, 3, 7, 4]	
commands [[2, 5, 3], [4, 4, 1], [1, 7, 3]]	
return [5, 6, 3]


***/
