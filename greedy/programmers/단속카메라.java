import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 차량 끝점 을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1])); 
        int answer = 0;
        
        // 마지막으로 설치한 카메라의 위치
        int end = -300000;
        
        // 각 차량의 경로 순회
        for (int[] route: routes) {
            // 현재 차량의 진입지점이 마지막 카메라 위치(end)보다 크면, (이전 카메라로는 이 차량을 단속할 수 없으면)
            if (route[0] > end) {
                answer++;
                end = route[1];
            }
        }
        
        return answer;
    }
}

/***
[그리디 알고리즘]
• 정렬된 순서대로 차량을 보면서, 이전에 설치한 카메라로 단속이 안 되는 경우(즉, 현재 차량의 진입지점이 마지막 카메라 위치보다 뒤에 있을 때)만 카메라를 새로 설치
• 새로 설치할 때는 항상 현재 차량의 진출 지점에 설치
    • 왜냐하면, 이렇게 해야 앞으로의 차량들과 겹치는 구간이 최대화되어 카메라 개수가 최소가 됨
***/
