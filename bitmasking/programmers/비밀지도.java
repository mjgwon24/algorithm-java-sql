import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            // arr1 row
            StringBuilder b = new StringBuilder(Integer.toBinaryString(arr1[i]));
            while (b.length() != n) {
                b.insert(0, '0');
            }
            
            for (int j = 0; j < n; j++) {
                map1[i][j] = b.toString().charAt(j) - '0';
            }
            
            // arr2 row
            b = new StringBuilder(Integer.toBinaryString(arr2[i]));
            while (b.length() != n) {
                b.insert(0, '0');
            }
            
            for (int j = 0; j < n; j++) {
                map2[i][j] = b.toString().charAt(j) - '0';
            }
            
            // - 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분: 전체 지도에서도 벽 ('#')
            // - 지도 1과 지도 2에서 모두 공백인 부분: 전체 지도에서도 공백
            StringBuilder answerRow = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((map1[i][j] | map2[i][j]) == 1) answerRow.append('#');
                else answerRow.append(" ");
            }
            
            answer[i] = answerRow.toString();
        }
      
        // 해독 결과
        return answer;
    }
}

/*
- 지도: 각 칸은 "공백"(" ") 또는 "벽"("#") 두 종류
- 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분: 전체 지도에서도 벽
- 지도 1과 지도 2에서 모두 공백인 부분: 전체 지도에서도 공백
- "지도 1"과 "지도 2": 각각 정수 배열로 암호화
- 암호화된 배열: 지도의 각 가로줄에서 벽 부분을 1, 공백 부분을 0으로 부호화했을 때 얻어지는 이진수에 해당하는 값의 배열

n 5
arr1 [9, 20, 28, 18, 11]
arr2 [30, 1, 21, 17, 28]
result ["#####","# # #", "### #", "# ##", "#####"]


*/
