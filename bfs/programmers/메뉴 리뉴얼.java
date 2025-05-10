import java.util.*;

class Solution {
    static HashMap<String, Integer> allCandidateMap = new HashMap<>(); // 모든 코스요리 후보 (코스요리, 개수)
    static HashSet<Character> allMenu = new HashSet<>(); // 모든 요리
    static List<Character> allMenuList; // 모든 요리
    
    public String[] solution(String[] orders, int[] course) {
        // 지금까지 나온 요리 초기화
        for (String s: orders) {
            for (char c: s.toCharArray()) {
                allMenu.add(c);
            }
        }
        
        allMenuList = new ArrayList<>(allMenu);
        
        List<String> answerList = new ArrayList<>();
        // 개수별 후보 탐색 (조합하기)
        for (int courseCnt: course) {
            dfs(0, 0, courseCnt, new StringBuilder(), orders);
            
            // {BC=2, CD=3, DE=3, FG=2, AC=4, CE=3, AD=2, CF=2, AE=2, BF=2, CG=2, BG=2}
            int max = 0;
            for (Map.Entry<String, Integer> a: allCandidateMap.entrySet()) {
                if (a.getValue() > max) max = a.getValue();
            }
            
            for (Map.Entry<String, Integer> a: allCandidateMap.entrySet()) {
                if (a.getValue() == max) {
                    answerList.add(a.getKey());
                }
            }
            
            allCandidateMap.clear();
        }
        
        String[] answer = new String[answerList.size()];
        
        int idx = 0;
        for (String s: answerList) {
            answer[idx++] = s;
        }
        
        Arrays.sort(answer);
        
        // 새로 추가하게 될 코스요리의 메뉴 구성
        // 사전 순으로 오름차순 정렬
        // 배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬
        return answer;
    }
    
    // 개수 기반 요리 후보 조합
    static void dfs(int start, int depth, int targetDepth, StringBuilder cur, String[] orders) {
        if (depth == targetDepth) {
            for (String s: orders) {
                boolean flag = true;
                String curStr = cur.toString();
                
                for (char c: curStr.toCharArray()) {
                    boolean flag2 = true;
                    
                    if (!s.contains(Character.toString(c))) {
                        flag2 = false;
                    }
                    
                    // 없음
                    if (!flag2) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    allCandidateMap.put(cur.toString(), allCandidateMap.getOrDefault(cur.toString(), 0) + 1);
                }
            }
            
            if (allCandidateMap.getOrDefault(cur.toString(), 0) == 1) allCandidateMap.remove(cur.toString());
            
            return;
        }
        
        for (int i = start; i < allMenuList.size(); i++) { 
            char c = allMenuList.get(i);
            
            if (!cur.toString().contains(Character.toString(c))) {
                StringBuilder newCur = new StringBuilder(cur);
                newCur.append(c);
                dfs(i + 1, depth + 1, targetDepth, newCur, orders);
            }
        }
    }
}

/*
이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성
코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함

orders 각 손님들이 주문한 단품메뉴들
course 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수

---
## ex1
["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]	
[2,3,4]	
["AC", "ACDE", "BCFG", "CDE"]

---
## ex2
["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]	
[2,3,5]	
["ACD", "AD", "ADE", "CD", "XYZ"]


---
## ex3
["XYZ", "XWY", "WXA"]	
[2,3,4]	
["WX", "XY"]

*/
