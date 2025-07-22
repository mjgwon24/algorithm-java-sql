import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int K = key.length; int L = lock.length;
        int B = L + ((K - 1) * 2);
        int[][] board = new int[B][B];
        
        // board 중앙에 lock 배치
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                board[i + K - 1][j + K - 1] = lock[i][j];
            }
        }
        
        // 90도 회전 4회
        for (int r = 0; r < 4; r++) {
            // 이동 (열쇠를 board 첫 부분부터 마지막까지 전부 위치시켜보며 검증)
            for (int offsetX = 0; offsetX < L + K - 1; offsetX++) {
                for (int offsetY = 0; offsetY < L + K - 1; offsetY++) {
                    if (isValid(key, board, offsetX, offsetY)) return true;
                }
            }
            
            key = rotate(key);
        }
        
        // 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return
        return false;
    }
    
    // 유효성 검사 (위치가 옮겨진 key를 board에 합쳐서, lock 범위가 모두 1로 채워져있으면 true)
    static boolean isValid(int[][] key, int[][] board, int offsetX, int offsetY) {
        int[][] temp = new int[board.length][board.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = board[i].clone();
        }
        
        // key를 temp에 합치기 (합치면서 바로 1 아니면 false 반환)
        // offsetX, offsetY 고려
        for (int i = key.length - 1; i < temp.length - (key.length - 1); i++) {
            for (int j = key.length - 1; j < temp.length - (key.length - 1); j++) {
                // key 범위를 벗어났다면, temp값만으로 판단
                if (i - offsetX < 0 || j - offsetY < 0 || i - offsetX >= key.length || j - offsetY >= key.length) {
                    if (temp[i][j] != 1) return false;
                } else {
                    if (temp[i][j] + key[i - offsetX][j - offsetY] != 1) return false;
                }
            }
        }
        
        return true;
    }
    
    // 90도 회전 (열쇠)
    static int[][] rotate(int[][] key) {
        int length = key.length;
        int[][] temp = new int[length][length];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[i][j] = key[length - j - 1][i];
            }
        }
        
        return temp;
    }
}

/*
- 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태, 열쇠는 M x M 크기인 정사각 격자 형태 : 이중배열, map
- 열쇠는 회전과 이동이 가능 : 회전 함수, 이동 offset 필요
- 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조 : isValid 유효성 검사, 자물쇠와 열쇠를 합한 배열이 모두 1로 되어야함
- M은 항상 N 이하 : 열쇠가 항상 자물쇠보다 작음, 열쇠는 움직임 -> 열쇠가 이동하는 만큼 자물쇠의 기존 배열에서 크기 늘린 새로운 board 이중배열 필요
*/
