import java.util.*;

class Solution {
    static Map<String, List<String>> graph = new HashMap<>();
    static Map<String, Integer> dp = new HashMap<>();
    static int answer = Integer.MAX_VALUE;
    
    static class Info {
        String name;
        int cnt;
        
        public Info(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }
    
    /**
    * 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 도출
    * @param begin 
    * @param target
    * @param words 단어 집합 (3개 이상 50개 이하)
    **/
    public int solution(String begin, String target, String[] words) {
        // 예외. words에 target이 없을 경우 즉시 0 반환
        if (!Arrays.asList(words).contains(target)) return 0;
        
        // 1. Map graph, dp 초기화
        List<String> tempList = new ArrayList<>();
        for (String word : words) {
            if (isPut(begin, word)) tempList.add(word);
            dp.put(word, Integer.MAX_VALUE);
            
            List<String> tempList2 = new ArrayList<>();
            for (String other : words) {
                if (word.equals(other)) continue;
                if (isPut(word, other)) tempList2.add(other);
            }
            
            graph.put(word, tempList2);
        }
        graph.put(begin, tempList);
        
        bfs(begin, target);
        
        // 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 도출
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    static void bfs(String begin, String target) {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(begin, 0));
        dp.put(begin, 0);
        
        while (!q.isEmpty()) {
            Info cur = q.poll();
            String name = cur.name;
            int cnt = cur.cnt;
            // System.out.printf("현재 %s, cnt: %d\n", name, cnt);
            
            if (name.equals(target)) {
                answer = Math.min(answer, cnt);
                continue;
            }
            
            for (String next : graph.get(name)) {
                if (dp.get(next) <= cnt + 1) continue;
                
                // System.out.printf("다음 %s, cnt: %d\n", next, cnt + 1);
                
                dp.put(next, cnt + 1);
                q.add(new Info(next, cnt + 1));
            }
        }
    }
    
    // A -> B 변환 가능한지 판단
    // 한 번에 한 개의 알파벳만 바꿀 수 있음
    // words에 있는 단어로만 변환 가능
    static boolean isPut(String A, String B) {
        if (A.equals(B)) return false;
        
        int cnt = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                cnt++;
                if (cnt == 2) return false;
            }
        }
        
        return true;
    }
}
