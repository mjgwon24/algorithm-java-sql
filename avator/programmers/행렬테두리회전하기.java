import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 1. rows, columns 원본 배열 생성
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = i * columns + j + 1;
            }
        }
        
        List<Integer> minList = new ArrayList<>();
        
        // 2. query별로 시계방향 회전
        for (int[] query : queries) {
            // 1-index -> 0-index
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;
            
            int min = arr[x1][y1];
            int temp = arr[x1][y1];
            
            // 회전
            // top
            for (int y = y1 + 1; y <= y2; y++) {
                int next = arr[x1][y];
                arr[x1][y] = temp;
                temp = next;
                min = Math.min(min, temp);
            }
            
            // right
            for (int x = x1 + 1; x <= x2; x++) {
                int next = arr[x][y2];
                arr[x][y2] = temp;
                temp = next;
                min = Math.min(min, temp);
            }
            
            // bottom
            for (int y = y2 - 1; y >= y1; y--) {
                int next = arr[x2][y];
                arr[x2][y] = temp;
                temp = next;
                min = Math.min(min, temp);
            }
            
            // left
            for (int x = x2 - 1; x >= x1; x--) {
                int next = arr[x][y1];
                arr[x][y1] = temp;
                temp = next;
                min = Math.min(min, temp);
            }
            
            // 최솟값 넣기
            minList.add(min);
        }
        
        // List -> int
        return minList.stream().mapToInt(i -> i).toArray();
    }
}
