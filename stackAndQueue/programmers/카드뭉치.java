import java.util.*;

class Solution {
    
    // cards1, cards2 문자열로 이루어진 배열, goal 원하는 단어 배열
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        Queue<String> q3 = new LinkedList<>();
        
        for (String c : cards1) {
            q1.add(c);
        }
        
        for (String c : cards2) {
            q2.add(c);
        }
        
        for (String c : goal) {
            q3.add(c);
        }
        
        boolean flag = true;
        while (!q3.isEmpty()) {
            String target = q3.poll();
            
            if (!q1.isEmpty() && q1.peek().equals(target)) {
                q1.poll();
                continue;
            }
            
            if (!q2.isEmpty() && q2.peek().equals(target)) {
                q2.poll();
                continue;
            }
            
            flag = false;
            break;
        }
        
        // goal을 만들 수 있다면 Yes, 없다면 No 반환
        return flag ? "Yes" : "No";
    }
}
