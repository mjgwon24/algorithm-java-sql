import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> bridgeTrucks = new LinkedList<>(); // {트럭 무게, 다리에서 나가는 시점}
        int time = 0;
        int totalWeight = 0;
        int idx = 0; // truck_weights 인덱스

        while (idx < truck_weights.length || !bridgeTrucks.isEmpty()) {
            time++;

            // 1. 다리에서 나갈 트럭 처리
            if (!bridgeTrucks.isEmpty() && bridgeTrucks.peek()[1] == time) {
                totalWeight -= bridgeTrucks.poll()[0];
            }

            // 2. 대기 트럭 다리에 올릴 수 있으면 올리기
            if (idx < truck_weights.length 
                && totalWeight + truck_weights[idx] <= weight 
                && bridgeTrucks.size() < bridge_length) {
                totalWeight += truck_weights[idx];
                bridgeTrucks.add(new int[]{truck_weights[idx], time + bridge_length});
                idx++;
            }
        }
        
        return time;
    }
}
