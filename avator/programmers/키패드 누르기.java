import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        HashMap<Integer, int[]> keyMap = new HashMap<>();
        int[] leftPosition = {3, 0};
        int[] rightPosition = {3, 2};
        
        keyMap.put(1, new int[]{0, 0});
        keyMap.put(2, new int[]{0, 1});
        keyMap.put(3, new int[]{0, 2});
        keyMap.put(4, new int[]{1, 0});
        keyMap.put(5, new int[]{1, 1});
        keyMap.put(6, new int[]{1, 2});
        keyMap.put(7, new int[]{2, 0});
        keyMap.put(8, new int[]{2, 1});
        keyMap.put(9, new int[]{2, 2});
        keyMap.put(-1, new int[]{3, 0});
        keyMap.put(0, new int[]{3, 1});
        keyMap.put(-2, new int[]{3, 2});
        
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                // 왼손으로 누름
                sb.append("L");
                
                // 왼손 위치 변경
                leftPosition = keyMap.get(numbers[i]);
                
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                // 오른손으로 누름
                sb.append("R");
                
                // 오른손 위치 변경
                rightPosition = keyMap.get(numbers[i]);
            } else {
                // 거리가 더 가까운 손
                int[] keyPosition = keyMap.get(numbers[i]);
                int leftDist = Math.abs(leftPosition[0] - keyPosition[0])
                   + Math.abs(leftPosition[1] - keyPosition[1]);
                int rightDist = Math.abs(rightPosition[0] - keyPosition[0])
                   + Math.abs(rightPosition[1] - keyPosition[1]);
                
                if (leftDist < rightDist) {
                    // 왼손이 더 짧다면 왼손 이동
                    sb.append("L");
                    leftPosition = keyMap.get(numbers[i]);
                } else if (leftDist > rightDist) {
                    // 오른손이 더 짧다면 오른손 이동
                    sb.append("R");
                    rightPosition = keyMap.get(numbers[i]);
                } else {
                    // 거리가 같다면, hand로 판단
                    if (hand.equals("right")) {
                        sb.append("R");
                        rightPosition = keyMap.get(numbers[i]);
                    } else {
                        sb.append("L");
                        leftPosition = keyMap.get(numbers[i]);
                    }
                }
            }
        }
        
        // 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 
        // 연속된 문자열 형태로 return
        return sb.toString();
    }
}

/*
처음 왼손 엄지손가락은 * 키패드
오른손 엄지손가락은 # 키패드 위치에서 시작

numbers 순서대로 누를 번호가 담긴 배열 
hand 왼손잡이인지 오른손잡이인 지
*/
