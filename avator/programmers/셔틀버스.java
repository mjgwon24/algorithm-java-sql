import java.util.*;

class Solution {
    static Deque<String> waitDq = new ArrayDeque<>(); // 대기열(중복 가능, 순서대로 정렬)
    static List<int[]> waitList = new ArrayList<>(); // 대기열(중복 가능, 순서대로 정렬)
    // static Deque<String> 탑승;
    
    public String solution(int n, int t, int m, String[] timetable) {
        // 대기열 리스트 초기화 (정렬)
        for (String s : timetable) {
            String[] str = s.split(":");
            int H = Integer.parseInt(str[0]);
            int M = Integer.parseInt(str[1]);
            
            waitList.add(new int[]{H, M});
        }
        
        waitList.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });
        
        // 최초 버스 출발 시간 09:00
        int curH = 9; int curM = 0;
        // 콘 안정적으로 도착하는 시간 초기화
        int conH = waitList.get(0)[0];
        int conM = waitList.get(0)[1] - 1;
        if (conM == -1) {
            conM = 59;
            conH--;
        }
        
        int idx = 0; // 대기열 리스트에서, 탑승한 손님 식별 인덱스
        while (n > 0 && idx < waitList.size()) {
            int tempCnt = 0; // 현재 탈수있는 손님 카운트
            
            // 버스 도착
            // 현재 버스가 꽉 차는지 판단
            // 손님 수: m
            while (tempCnt < m && idx < waitList.size()) {
                // 현재 손님
                int[] cur = waitList.get(idx);
                
                // 손님 탑승 가능 여부 판단
                if ((cur[0] == curH && cur[1] <= curM) || cur[0] < curH) {
                    // 탑승 가능
                    idx++;
                    tempCnt++;
                } else {
                    break; // 탑승이 불가능하다면 중지
                }
            }
            
            // 만약 수용인원 다 찼다면, 
            if (m == tempCnt) {
                // 현재 버스를 탈 수 있는 고객의 마지막 시각 -1으로 con시간 변경
                // 버스 탑승 가능 마지막 고객
                int[] p = waitList.get(idx - 1);
                
                conH = p[0];
                conM = p[1] - 1;
                
                if (conM == -1) {
                    conM = 59;
                    conH--;
                } 
            } else {
                // 꽉 차지 않는다면, 현재 버스 도착시간으로 con시간 변경
                conH = curH;
                conM = curM;
            }
            
            // 버스 시간 이동 curH, curM
            // 배차 간격: t
            curM += t;
            
            if (curM >= 60) {
                curH++;
                curM -= 60;
            }
            n--;
        }
        
        // 제일 늦은 도착 시각 출력
        // HH:MM 형식
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2s", Integer.toString(conH)).replace(' ', '0'))
            .append(":")
            .append(String.format("%2s", Integer.toString(conM)).replace(' ', '0'));
        
        return sb.toString();
    }
}

/*
셔틀 운행 횟수 n
셔틀 운행 간격 t
한 셔틀에 탈 수 있는 최대 크루 수 m
크루가 대기열에 도착하는 시각을 모은 배열 timetable

- 셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착
- 대기 순서대로 태우고 바로 출발
- 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. -> 뭔말임;;;

---

n 1, t 1, m 5
timetable
["08:00", "08:01", "08:02", "08:03"]	
return 09:00

Deque<String> 대기열(중복 가능): (먼저) 0800, 0801, 0802, 0803 (나중)
09:00 버스 도착
Deque<String> 탑승: (먼저) 0800, ...
09:00 내림
남은 승객 없음
정답 09:00

---

n 2, t 10, m 2
["09:10", "09:09", "08:00"]	
return "09:09"

1번 버스 (n = 1)
Deque<String> 대기열(중복 가능, 순서대로): (먼저) 0800, 0909, 0910
09:00 버스 도착
탑승: 0800

2번 버스 (n = 2)
Deque<String> 대기열(중복 가능, 순서대로): (먼저) 0909, 0910
09:10 버스 도착, 바로 감(동일 시간 태움)
탑승: 0909, 0910
콘이 0910에 도착하면 자리를 뺏겨서 못탐. 따라서 0909가 답.

---




*/
