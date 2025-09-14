import java.util.*;

class Solution {
    static Map<Integer, Integer> challengeCnt = new HashMap<>();
    static Map<Integer, Integer> failCnt = new HashMap<>();
    // static double[] failureRate;
    
    static class Info {
        int idx; double rate;
        
        public Info(int idx, double rate) {
            this.idx = idx;
            this.rate = rate;
        }
    }

    // N 전체 스테이지 개수, stages 현재 멈춰있는 스테이지의 번호가 담긴 배열
    public int[] solution(int N, int[] stages) {
        // 1. 도전 횟수와 실패 횟수 도출
        for (int stage : stages) {
            // 현재 숫자 이하는 모두 도전자 (200,000)
            for (int i = stage; i >= 1; i--) {
                challengeCnt.put(i, challengeCnt.getOrDefault(i, 0) + 1);
            }
            
            // 현재 숫자는 실패자
            failCnt.put(stage, failCnt.getOrDefault(stage, 0) + 1);
        }
        
        // 2. 실패율 계산
        // 실패율이 높은 스테이지부터 정렬
        // 실패율이 동일하다면 인덱스 순으로 오름차순 정렬
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> {
            // 실패율이 다르면 실패율 내림차순
            if (Double.compare(a.rate, b.rate) != 0) {
                return Double.compare(b.rate, a.rate); // 내림차순
            }
            
            // 실패율이 같으면 인덱스 오름차순
            return Integer.compare(a.idx, b.idx);
        });
        
        for (int i = 1; i <= N; i++) {
            double temp = 0;
            if (failCnt.getOrDefault(i, 0) != 0) {
                temp = (double) failCnt.get(i) / (double) challengeCnt.get(i);
            }
            
            pq.add(new Info(i, temp));
        }
        
        // System.out.println(Arrays.toString(failureRate));
        
        int[] answer = new int[N];
        int idx = 0;
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            answer[idx++] = cur.idx;
        }

        // 스테이지 번호가 담겨있는 배열 반환        
        return answer;
    }
}
