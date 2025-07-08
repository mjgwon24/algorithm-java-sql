import java.util.*;

class Solution {
    // 각 방 번호가 가리키는 '다음 빈 방'을 저장
    private Map<Long, Long> roomMap = new HashMap<>();

    // 현재 방 번호에서 시작해, 빈 방을 찾는 함수 (경로 압축)
    private long find(long room) {
        if (!roomMap.containsKey(room)) {
            // 현재 방이 비어있다면 이 방을 반환
            return room;
        }
        // 이미 배정된 방이면, 다음 빈 방을 재귀적으로 찾고 경로 압축
        long next = find(roomMap.get(room));
        roomMap.put(room, next); // 경로 압축
        return next;
    }

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long want = room_number[i];
            long assign = find(want); // 원하는 방 또는 그보다 큰 가장 작은 빈 방
            answer[i] = assign;
            // assign 방은 이제 배정되었으므로, 다음 빈 방을 map에 기록
            roomMap.put(assign, assign + 1);
        }

        return answer;
    }
}
