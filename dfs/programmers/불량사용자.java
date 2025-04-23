import java.util.*;

class Solution {
    Set<Set<String>> result = new HashSet<>(); // 최종 결과 set
    List<List<String>> candidateList = new ArrayList<>(); // 매칭 가능 후보 리스트

    public int solution(String[] user_id, String[] banned_id) {
        // 1. banned_id별로 매칭 가능한 user_id 리스트 구하기
        for (String ban : banned_id) {
            List<String> candidates = new ArrayList<>(); // 현재 ban 패턴에 매칭되는 user_id 저장
            
            for (String user : user_id) {
                if (isMatch(user, ban)) candidates.add(user);
            }
            
            candidateList.add(candidates);
        }

        // 2. 백트래킹(DFS)로 경우의 수 탐색
        dfs(0, new HashSet<>());

        return result.size();
    }

    // idx: 현재 banned_id 인덱스, selected: 현재까지 선택한 user_id 집합
    void dfs(int idx, Set<String> selected) {
        if (idx == candidateList.size()) {
            // 현재 선택된 조합을 결과 Set에 추가 (Set의 Set이므로 중복 없이 저장)
            result.add(new HashSet<>(selected)); // 반드시 복사해서 넣어야 함
            return;
        }
        
        // 현재 banned_id 인덱스에 대해, 가능한 user_id 후보들 모두 시도
        for (String user : candidateList.get(idx)) {
            if (!selected.contains(user)) {
                selected.add(user);
                dfs(idx + 1, selected);
                selected.remove(user);
            }
        }
    }

    // user_id와 banned_id 패턴이 매칭되는지 확인하는 함수
    boolean isMatch(String user, String ban) {
        // 길이 다르면 불일치
        if (user.length() != ban.length()) return false;
        
        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) != '*' && ban.charAt(i) != user.charAt(i)) return false;
        }
        
        return true;
    }
}


/*
[DFS]
1. banned_id[i]에 대응 가능한 user_id 리스트를 구함
2. DFS로 banned_id의 각 인덱스마다 가능한 user_id를 하나씩 할당
3. 이미 사용한 user_id는 다음 banned_id에 할당하지 않음
4. 모든 banned_id에 할당이 끝나면, 해당 조합(순서 무시, 중복 제거)을 Set에 저장


*/
