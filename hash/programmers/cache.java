import java.lang.*;
import java.util.*;

class Solution {
    static LinkedList<String> cacheSet = new LinkedList<>();
    static int totalTime = 0;
    
    public int solution(int cacheSize, String[] cities) {
        // 1. 예외 처리 - 캐시크기가 0이면, 모든 요청이 miss
        if (cacheSize == 0) return cities.length * 5;
        
        // 2. 캐시 동작시 소요 시간 계산
        // LRU (가장 오래된 요소 제거, 최근 사용된것이 가장 뒤에 배치되도록)
        for (String city : cities) {
            city = city.toLowerCase();
            
            // cache hit -> totalTime++
            if (cacheSet.contains(city)) {
                // 제거 -> 추가
                cacheSet.remove(city);
                cacheSet.add(city);
                totalTime++;
            } else {
                // cache miss(캐시 데이터 없음) -> totalTime += 5
                // 꽉 찼다면 오래된 데이터 제거 -> 추가
                if (cacheSet.size() == cacheSize) {
                    // 첫번째 데이터가 가장 오래된 데이터
                    cacheSet.removeFirst(); // first
                }
                
                cacheSet.add(city);
                totalTime += 5;
            }
            
            // for (String s : cacheSet) {
            //     System.out.printf(s + " ");
            // }
            // System.out.println();
        }
        
        // 총 실행시간
        return totalTime;
    }
}

/***
[ 요구사항 ]
입력된 도시이름 배열을 순서대로 처리할 때, "총 실행시간"을 출력
• cache hit일 경우 실행시간은 1
• cache miss일 경우 실행시간은 5

[ 조건 ]
DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램 작성
지도에서 도시 이름을 검색 -> 해당 도시와 관련된 맛집 게시물들을 데이터베이스에서 읽어 보여주는 서비스
캐시 교체 알고리즘은 LRU(Least Recently Used)

[ 입력 ]
int cacheSize  캐시 크기
3

String[] cities  도시이름 배열
["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]	

[ 출력 ]
21

1. 예외 처리 - 캐시크기가 0이면, 모든 요청이 miss
2. 캐시 동작시 소요 시간 계산
LRU (가장 오래된 요소 제거, 최근 사용된것이 가장 뒤에 배치되도록)

cacheSet = []
• cache miss(캐시 데이터 없음) -> totalTime += 5
    cacheSet = [Jeju]
    cacheSet = [Jeju, Pangyo]
    cacheSet = [Jeju, Pangyo, Seoul]
    ∆ totalTime = 15

• cache hit -> totalTime++  
    Jeju
    cacheSet = [Jeju, Pangyo, Seoul] -> [Pangyo, Seoul, Jeju]
    ∆ totalTime = 16
    
    
2, ["Jeju", "Pangyo", "NewYork", "newyork"]	
cacheSet = ["Jeju", "Pangyo"] totalTime = 10
cacheSet = ["Pangyo" NewYork] totalTime = 15
cacheSet = ["Pangyo" newyork] totalTime = 16

=> 대소문자 구분 없음
    
***/
