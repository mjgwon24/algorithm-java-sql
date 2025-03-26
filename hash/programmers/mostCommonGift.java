import java.lang.*;
import java.util.*;

class Solution {
    static Map<String, Integer> giftScores = new HashMap<>(); // 선물 지수 맵
    // static Map<String, Map<String, Integer>> history = new HashMap<>(); // 선물 주고받은 기록 - 준사람, (받은 사람, 횟수)
    // static Map<String, List<String>> history = new HashMap<>(); // 선물 주고받은 기록 - 준사람, (받은 사람1, 받은사람2, ...)
    static Map<String, List<String>> history = new HashMap<>(); // 선물 주고받은 기록 - 준사람, (받은 사람1, 받은사람2, ...)
    static Map<String, Integer> nextGifts = new HashMap<>(); // 사람별 다음달 받을 선물의 개수
    
    public int solution(String[] friends, String[] gifts) {
        // 맵 초기화
        for (String friend: friends) {
            giftScores.put(friend, 0);
            history.put(friend, new ArrayList<>());
        }
        
        // 1. gifts 배열 기반으로 선물 주고받은 현황 초기화 - 선물지수, 선물 주고받은 기록
        for (String gift: gifts) {
            String[] giftArr = gift.split(" ");
            String A = giftArr[0]; // 준 사람
            String B = giftArr[1]; // 받은 사람
            
            giftScores.put(A, giftScores.get(A) + 1);
            giftScores.put(B, giftScores.get(B) - 1);
            
            history.get(A).add(B);
        }
        
        // System.out.println("선물지수 현황");
        // for (String s: friends) {
        //     System.out.printf("%s 점수: %d\n", s, giftScores.get(s));
        // }
        
        // 2. 친구들 중 2명의 사람을 선택 (순서 신경쓰지 않음)
        // 이중포문이라 순서가 반영된건가
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String f1 = friends[i];
                String f2 = friends[j];
                // System.out.printf("현재 선택된 사람: %s, %s\n", f1, f2);
                
                if (f1.equals(f2)) continue;
                int s1 = giftScores.get(f1);
                int s2 = giftScores.get(f2);
                
                // 두 사람이 선물을 주고받은 기록 X
                if (!history.get(f1).contains(f2) && !history.get(f2).contains(f1)) {
                    if (s1 > s2) {
                        // s1이 s2에게 선물 받음
                        nextGifts.put(f1, nextGifts.getOrDefault(f1, 0) + 1);
                    } else if (s1 < s2) {
                        // s2가 s1에게 선물 받음
                        nextGifts.put(f2, nextGifts.getOrDefault(f2, 0) + 1);
                    } 
                    
                    // System.out.println("선물받은 기록 없음. 선물지수로 비교.");
                } else {
                    // 두 사람이 선물을 주고받은 기록 O -> 이번 달까지 "두 사람 사이"에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음
                    // 선물지수가 높은사람이 아님. history 개수 기반으로 비교해야한다. 두 사람 사이!!!!
                    // f1(준사람) List<String>을 꺼냄 -> 해당 String에서 f2가 몇개있는지 계산해서 반환
                    int p1 = 0;
                    int p2 = 0;
                    
                    for (String s: history.get(f1)) {
                        if (s.equals(f2)) p1++;
                    }
                    
                    for (String s: history.get(f2)) {
                        if (s.equals(f1)) p2++;
                    }
                    
                    // System.out.println("선물받은 기록 있음");
                    // System.out.printf("%s가 %s에게 %d개의 선물을 줌\n",f1, f2, p1);
                    // System.out.printf("%s가 %s에게 %d개의 선물을 줌\n",f2, f1, p2);
                    
                    if (p1 > p2) {
                        nextGifts.put(f1, nextGifts.getOrDefault(f1, 0) + 1);
                    } else if (p1 < p2) {
                        nextGifts.put(f2, nextGifts.getOrDefault(f2, 0) + 1);
                    } else {
                        if (s1 > s2) nextGifts.put(f1, nextGifts.getOrDefault(f1, 0) + 1);
                        else if (s1 < s2) nextGifts.put(f2, nextGifts.getOrDefault(f2, 0) + 1);
                    }
                }
                // for (String f: friends) {
                //     System.out.println(f);
                //     System.out.println(nextGifts.getOrDefault(f, 0));
                // }
                // System.out.println();
            }
        }
        
// "muzi", "ryan" O -> ryan = 1
// "muzi", "frodo" O -> muzi = 1
// "muzi", "neo" O -> neo = 1
        
// "ryan", "frodo" O -> frodo = 1
        
// "ryan", "neo" X -> ryan = 2
// "frodo", "neo" X -> neo = 1
        
        // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
        int answer = 0;
        for (String f: friends) {
            // System.out.println(f);
            // System.out.println(nextGifts.getOrDefault(f, 0));
            answer = Math.max(answer, nextGifts.getOrDefault(f, 0));
            // System.out.println();
        }
        return answer;
    }
}

/***
[ 요구사항 ]
다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수 return

[ 조건 ]
친구들이 이번 달까지 선물을 주고받은 기록을 바탕으로 다음 달에 누가 선물을 많이 받을지 예측
선물지수: 이번 달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수

• 두 사람이 선물을 주고받은 기록 O
    • 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음
    
• 두 사람이 선물을 주고받은 기록 X or 주고받은 수가 같다면
    • 선물 지수가 더 큰 사람(선물을 많이 준 사람)이 
      선물 지수가 더 작은 사람에게 선물을 하나 받음
    => 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않음
    
[ 입력 ]
• String[] friends: 친구들의 이름을 담은 1차원 문자열 배열 
    (알파벳 소문자로 이루어진 길이가 10 이하인 문자열)
• String[] gifts: 이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열
    "A B": 선물을 준 친구의 이름, 선물을 받은 친구의 이름

[ 도출 과정 ]
friends = ["muzi", "ryan", "frodo", "neo"]	
gifts	= [
"muzi frodo",  // muzi -> frodo, 선물지수 muzi++, frodo--
"muzi frodo",  // muzi -> frodo, 선물지수 muzi++, frodo--
"ryan muzi",   // 선물지수 ryan++, muzi--
"ryan muzi",    // 선물지수 ryan++, muzi--
"ryan muzi",    // 선물지수 ryan++, muzi--
"frodo muzi",    // 선물지수 frodo++, muzi--
"frodo ryan",    // 선물지수 frodo++, ryan--
"neo muzi"       // 선물지수 neo++, muzi--
]	

사람별 선물지수 Map<String, Integer>
선물 주고받은 기록 Map<String, String??? 준사람 받은사람 횟수
사람별 다음달 받을 선물의 개수 history
muzi, (frodo, frodo) 2
ryan, (muzi, muzi, muzi) 3

"muzi", "ryan" O -> ryan = 1
"muzi", "frodo" O -> muzi = 1
"muzi", "neo" O -> neo = 1
"ryan", "frodo" O -> frodo = 1
"ryan", "neo" X -> ryan = 2
"frodo", "neo" X -> neo = 1

["joy", "brad", "alessandro", "conan", "david"]
["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]
alessandro brad 
alessandro joy
alessandro conan
david alessandro
alessandro david


1. gifts 배열 기반으로 선물 주고받은 현황 초기화 - 선물지수, 선물 주고받은 기록
2. 친구들 중 2명의 사람을 선택 (순서 신경쓰지 않음)
3. 선택된 2명의 친구간 선물 주고받은 기록을 기반으로 누가 다음달에 선물을 받을지 또는 아무도 받지 않을지 계산
4. 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수 도출

result = 2
***/
