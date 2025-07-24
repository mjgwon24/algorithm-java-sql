import java.util.*;

class Solution {
    static class Food {
        int time;
        int idx; // 1-index
        
        public Food (int time, int idx) {
            this.time = time;
            this.idx = idx;
        }
    }
    
    public int solution(int[] food_times, long k) {
        int answer = 0;
        int N = food_times.length;
        
        // 우선순위 큐 초기화
        PriorityQueue<Food> pq1 = new PriorityQueue<>((a, b) -> a.time - b.time);
        for (int i = 0; i < N; i++) {
            pq.add(new Food(food_times[i], i + 1));
        }
        
        // 그리디
        long sum = 0; // 지금까지 먹은 시간
        long prev = 0; // 이전에 먹은 음식 시간
        long length = N; // 남은 음식 개수
        
        while (!pq.isEmpty()) {
            long now = pq.peek().time; // 현재 음식을 먹기 위해 필요한 시간
            long = spend = (now - prev) * length; // 한 번에 줄일 수 있는 시간(현재 시간 - 이전에 이미 줄인 시간) * 남은 음식 수
            
            if (sum + spend > k) break; // 지금까지 먹은 시간과 이번에 한 번에 줄일 시간이 총 시간을 넘으면 멈춤
            
            sum += spend; // 시간 추가
            prev = now; // 현재 음식의 시간을 이전 시간으로 갱신
            pq.poll(); // 다 먹은 음식 제거
            length--; // 하나의 음식이 줄었다면, 총 개수 줄임
        }
        
        if (pq.isEmpty()) return -1;

        // 남은 음식 정렬(번호 순)
        List<Food> rest = new ArrayList<>(pq);
        rest.sort((a, b) -> a.idx - b.idx);

        // 남은 시간에서 몇 번째 음식인지 계산
        return rest.get((int)((k - sum) % length)).idx;
    }
}

/*
회전판 - 큐, % 연산
k는 1 이상 2 x 10^13 이하의 자연수 - 그리디 (섭취 시간 적은 순으로 제거)
각 음식에는 1부터 N 까지 번호가 붙어있으며, 각 음식을 섭취하는데 일정 시간이 소요 - Food class
남은 음식은 그대로 두고, 다음 음식을 섭취 - 음식의 남은 시간과 음식 번호 데이터가 함께 존재해야 함 (Food class)

[핵심 로직]
1. 모든 음식의 남은 시간 힙에 저장
2. 한 번에 줄일 수 있는 시간 계산
    (가장 적은 남은 시간 - 이전 라운드에서 다 먹은 시간) * 남은 음식 개수
3. 이 시간을 k에서 소모 가능한지 체크
4. 남은 음식 중 (k - sum) % length 번째 음식이 정답
*/
