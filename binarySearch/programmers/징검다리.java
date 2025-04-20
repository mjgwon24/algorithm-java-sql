import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        List<Integer> rocksList = new ArrayList<>();
        for (int r: rocks) {
            rocksList.add(r);
        }
        rocksList.add(0);
        rocksList.add(distance);
        rocksList.sort((a, b) -> a - b);
        
        int l = 0, r = distance;
        while (l <= r) {
            int m = (l + r) / 2;
            int removeCnt = 0;
            
            // 최대 거리가 m이 되기 위해 제거해아하는 바위 개수
            int prev = rocksList.get(0);
            for (int i = 1; i < rocksList.size(); i++) {
                // 이전 바위와의 거리 차 
                int diff = rocksList.get(i) - prev;
                
                // 두 바위 간격이 m보다 작다면, 제거
                if (diff < m) removeCnt++;
                // 두 바위 간격이 m보다 크다면, prev 갱신
                else prev = rocksList.get(i);
            }
            
            // 제거해야하는 돌이 요구하는 개수와 같거나 적다면, 거리 최솟값 늘리기
            if (removeCnt <= n) {
                answer = m;
                l = m + 1;
            } else {
                // 제거해야하는 돌이 요구하는 개수보다 많다면, 거리 최솟값 줄이기
                r = m - 1;
            }
        }
        
        // 각 지점 사이 거리의 최솟값 중 가장 큰 값 return
        return answer;
    }
}

/***
int distance 출발지점부터 도착지점까지의 거리
int[] rocks 바위들 위치
int n 제거할 바위 개수

• 최솟값 중 최댓값 -> 파라메트릭 서치
• 결정 문제



***/
