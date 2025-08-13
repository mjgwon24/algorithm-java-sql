import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();
    
    public String[] solution(String[][] tickets) {
        // 그래프 구성
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            // 출발지가 없으면 pq 생성하여 추가
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
        }
        
        dfs("ICN");
        
        String[] answer = new String[result.size()];
        result.toArray(answer);
        return answer;
    }
    
    private void dfs(String from) {
        // from 공항에서 출발하는 항공권 목록
        PriorityQueue<String> destinations = graph.get(from);
        // System.out.printf("from: %s\n", from);
        
        while (destinations != null && !destinations.isEmpty()) {
            String to = destinations.poll();
            // System.out.printf("from: %s, to: %s\n", from, to);
            dfs(to);
        }
        
        // System.out.printf("result add: %s\n", from);
        // 더이상 from 공항에서 갈 곳이 없을 때, 경로에 추가
        result.addFirst(from);
    }
}
