import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int minLength = s.length(); 
        
        // 1. 단위를 늘려가며 압축 (1 ~ s.length / 2)
        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder compressSb = new StringBuilder(); // 압축 문자열
            int cnt = 1; // 반복 횟수
            
            // 대상 문자열
            String initStr = s.substring(0, i); // a -> aa -> aab
            
            
            // 단위 끊어가며 압축 여부 체크
            // s = aabbaccc
            // i = 1, j = 1 -> 2 -> 3 ..., cur = a, b, c, ...
            for (int j = i; j <= s.length(); j += i) {
                // System.out.printf("현재 대상 문자열: %s, 단위 길이: %d, 인덱스 위치: %d\n", initStr, i, j);
                
                String cur;
                
                if (j + i <= s.length()) cur = s.substring(j, j + i); // a -> b
                else cur = s.substring(j);
                
                // System.out.printf("현재 비교 문자열: %s\n", cur);
                
                // 압축 대상일시, 반복 횟수 갱신
                if (initStr.equals(cur)) cnt++;
                else {
                    // 압축이 완료되었을 경우, 압축 문자열 갱신
                    if (cnt > 1) compressSb.append(cnt).append(initStr);
                    else compressSb.append(initStr);
                    
                    // 압축 대상이 아닐시, 대상 문자열과 반복 횟수 갱신
                    initStr = cur;
                    cnt = 1;
                }
                
                // System.out.printf("압축 문자열 현황: %s\n", compressSb.toString());
            }
            
            if (cnt > 1) compressSb.append(cnt).append(initStr);
            else compressSb.append(initStr);
            
            // System.out.printf("[최종] 단위 %d에서, 최종 압축 문자열: %s\n", i, compressSb.toString());
            
            minLength = Math.min(minLength, compressSb.length());
        }
        
        
        
        // 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중
        // 가장 짧은 것의 길이를 return
        return minLength;
    }
}

/***
String s  압축할 문자열 (1 이상 1,000 이하)

• 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법
• 문자열은 제일 앞부터 정해진 길이만큼 잘라야 합니다.

[ex1]
s "aabbaccc"	
1개 단위: 2a2ba3c => 7

[ex2]
s "ababcdcdababcdcd"	
8개 단위: 2ababcdcd => 9

[ex3]
s "abcabcdede"	
3개 단위: 2abcdede => 8

[ex5]
s "xababcdcdababcdcd"	
=>  x / ababcdcd / ababcdcd 로 자르는 것은 불가능
=> 17

[sol]
1. 단위를 늘려가며 압축 (1 ~ s.length / 2)
2. 가장 짧은 문자열 길이 도출




***/
