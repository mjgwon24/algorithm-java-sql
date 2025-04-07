import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (stack.isEmpty()) stack.push(c);
            else if (stack.peek() == c) stack.pop();
            else stack.push(c);
        }   
        
        // 짝지어 제거하기를 성공적으로 수행할 수 있는지 반환
        return stack.isEmpty() ? 1 : 0;
    }
}

/***
String s 

1. 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾습니다
2. 그 둘을 제거한 뒤, 앞뒤로 문자열을 이어 붙입니다.
3. 반복... 문자열을 모두 제거한다면 짝지어 제거하기가 종료

Stack
1. 하나씩 push, 만약 짝지어진 문자열이라면 넣지않고 이전 값 pop
2. 최종 스택이 비었을 경우 true

[ex]
s = baabaa

1. b aa baa
2. bb aa
3. aa



***/
