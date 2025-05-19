import java.util.*;

class Solution {
    static Deque<Integer> assistContainer = new ArrayDeque<>(); // 보조 컨테이너 - 후입선출 (addLast, pollLast)
    static Deque<Integer> waitingContainer = new ArrayDeque<>(); // 대기열 - 선입선출 (addLast, pollFirst)
    static HashSet<Integer> truckSet = new HashSet<>(); // 성공 트럭
    
    public int solution(int[] order) {
        int N = order.length;
        for (int i = 1; i <= N; i++) {
            waitingContainer.addLast(i);
        }
        
        int targetIdx = 0;
        while (targetIdx < N) {
            int target = order[targetIdx];
            
            // 보조 컨테이너도, 대기열도 비었다면 종료
            if (assistContainer.isEmpty() && waitingContainer.isEmpty()) break;
            
            // 보조 컨테이너에서 바로 뽑을 수 있다면, 성공 트럭으로 뽑기
            if (!assistContainer.isEmpty() && assistContainer.peekLast() == target) {
                truckSet.add(assistContainer.pollLast());
                targetIdx++;
                continue;
            }
            
            // 대기열에서 바로 뽑을 수 있다면, 성공 트럭으로 뽑기
            if (!waitingContainer.isEmpty() && waitingContainer.peekFirst() == target) {
                truckSet.add(waitingContainer.pollFirst());
                targetIdx++;
                continue;
            }
            
            if (!assistContainer.isEmpty() && 
                !waitingContainer.isEmpty() && 
                waitingContainer.peekFirst() > target && 
                assistContainer.peekLast() != target) break;
            
            if (assistContainer.isEmpty() && 
                !waitingContainer.isEmpty() && 
                waitingContainer.peekFirst() > target) break;
            
            if (!assistContainer.isEmpty() && 
                assistContainer.peekLast() != target && 
                waitingContainer.isEmpty()) break;
            
            // 대기열의 숫자가 target보다 작으면, 보조 컨테이너로 옮기기
            while (!waitingContainer.isEmpty() && waitingContainer.peekFirst() < target) {
                assistContainer.addLast(waitingContainer.pollFirst());
            }
        }

        // 몇 개의 상자를 실을 수 있는지 return
        return truckSet.size();
    }
}
