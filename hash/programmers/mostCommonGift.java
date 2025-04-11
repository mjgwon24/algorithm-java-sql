import java.lang.*;
import java.util.*;

class Solution {
    static HashMap<String, List<String>> historySet = new HashMap<>();
    static HashMap<String, Integer> giftScoreSet = new HashMap<>();
    static HashMap<String, Integer> nextGiftSet = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        // 0. historySet, giftScoreSet 초기화
        for (String friend: friends) {
            historySet.put(friend, new ArrayList<>());
            giftScoreSet.put(friend, 0);
        }
        
        // 1. gifts 배열을 순회하며 historySet, giftScoreSet 초기화
        for (String gift: gifts) {
            String[] giftArr = gift.split(" ");
            String sender = giftArr[0];
            String receiver = giftArr[1];
            
            historySet.get(sender).add(receiver);
            giftScoreSet.put(sender, giftScoreSet.get(sender) + 1);
            giftScoreSet.put(receiver, giftScoreSet.get(receiver) - 1);
        }
        
        // 2. friends 배열을 순회하며 nextGiftSet 갱신
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String frd1 = friends[i];
                String frd2 = friends[j];
                int frd1Score = giftScoreSet.get(frd1);
                int frd2Score = giftScoreSet.get(frd2);
                
                // 두 사람이 선물을 주고받은 기록이 있다면,
                if (historySet.get(frd1).contains(frd2) || historySet.get(frd2).contains(frd1)) {
                    // 이번 달까지 "두 사람 사이"에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
                    int frd1Point = 0; // 친구1이 친구2에게 준 선물 개수
                    int frd2Point = 0; // 친구2가 친구1에게 준 선물 개수
                    
                    // historySet을 순회하며 서로에게 준 선물 개수 갱신
                    for (String receiver: historySet.get(frd1)) {
                        if (receiver.equals(frd2)) frd1Point++;
                    }
                    
                    for (String receiver: historySet.get(frd2)) {
                        if (receiver.equals(frd1)) frd2Point++;
                    }
                    
                    if (frd1Point > frd2Point) nextGiftSet.put(frd1, nextGiftSet.getOrDefault(frd1, 0) + 1);
                    else if (frd1Point < frd2Point) nextGiftSet.put(frd2, nextGiftSet.getOrDefault(frd2, 0) + 1);
                    else {
                        // 주고받은 수가 같다면,
                        // 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
                        if (frd1Score > frd2Score) nextGiftSet.put(frd1, nextGiftSet.getOrDefault(frd1, 0) + 1);
                        else if (frd1Score < frd2Score) nextGiftSet.put(frd2, nextGiftSet.getOrDefault(frd2, 0) + 1);
                    }
                } else {
                    // 두 사람이 선물을 주고받은 기록이 하나도 없다면
                    // 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
                    if (frd1Score > frd2Score) nextGiftSet.put(frd1, nextGiftSet.getOrDefault(frd1, 0) + 1);
                    else if (frd1Score < frd2Score) nextGiftSet.put(frd2, nextGiftSet.getOrDefault(frd2, 0) + 1);
                }
            }
        }
                
        // 3. nextGiftSet을 순회하며 다음달에 가장 많은 선물을 받은 친구가 받을 선물 수 return
        int maxValue = 0;
        for (String friend: friends) {
            int cnt = nextGiftSet.getOrDefault(friend, 0);
            if (maxValue < cnt) maxValue = cnt;
        }
        
        // 다음달에 가장 많은 선물을 받은 친구가 받을 선물 수 return
        return maxValue;
    }
}

/***
String[] friends 친구 이름
String[] gifts 선물 기록

friends
["muzi", "ryan", "frodo", "neo"]	
gifts	
["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]	

• 선물 지수: + 이번 달까지 자신이 친구들에게 준 선물의 수, - 받은 선물의 수

// • 두 사람이 선물을 주고받은 기록이 있다면,
//     • 이번 달까지 "두 사람 사이"에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
// • 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면,
//     • 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
//     • 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.

* Set: 단일 자료형으로 구성된 중복되지 않는 자료형
* Map: 키-값 쌍으로 구성된 키가 중복되지 않는 자료형 (값은 중복 가능)

[ 필요 변수 ]
• HashMap<String, List<String>> 선물을 주고받은 전체 기록 (선물을 준 사람, (선물을 받은 사람1, 2, ...))
    • historySet
• HashMap<String, Integer> 선물 지수 (이름, 선물지수)
    • giftScoreSet
• HashMap<String, Integer> 다음 달에 받을 선물 수 (이름, 선물 개수)
    • nextGiftSet

1. gifts 배열을 순회하며 historySet, giftScoreSet 초기화
2. friends 배열을 순회하며 nextGiftSet 갱신
3. nextGiftSet을 순회하며 다음달에 가장 많은 선물을 받은 친구가 받을 선물 수 return

***/
