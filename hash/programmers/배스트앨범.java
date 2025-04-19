import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 재생 수 합 set
        Map<String, Integer> genresMap = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genresMap.put(genres[i], genresMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<Map.Entry<String, Integer>> descList = new ArrayList<>(genresMap.entrySet());
        descList.sort((e1, e2) -> e2.getValue() - e1.getValue());
        
        Map<String, Integer> scoreIdxMap = new HashMap<>();
        
        int idx = 0;
        for (Map.Entry<String, Integer> map: descList) {
            scoreIdxMap.put(map.getKey(), idx++);
        }
        
        // scoreIdxMap = ("pop", 0), ("classic", 1)
        
        
        // 2. 각 장르별로 고유 번호 묶기
        // scoreList[0] = (1, 600), (4, 2500)
        // scoreList[1] = (0, 500), (2, 150), (3, 800)
        List<int[]>[] scoreList = new ArrayList[scoreIdxMap.size()];
        for (int i = 0; i < scoreIdxMap.size(); i++) {
            scoreList[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < genres.length; i++) {
            int scoreListIdx = scoreIdxMap.get(genres[i]);
            scoreList[scoreListIdx].add(new int[]{i, plays[i]});
        }
        
        // 3. 재생 횟수 내림차순 정렬, 장르별 개수는 최대 2개
        List<Integer> resultList = new ArrayList<>();
        
        for (List<int[]> l: scoreList) {
            l.sort((int[] a, int[] b) -> b[1] - a[1]);
            
            for (int i = 0; i < l.size() && i < 2; i++) {
                resultList.add(l.get(i)[0]);
            }
        }
        
        // 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
        // 장르별 최대 2개까지 수록
        int[] answer = new int[resultList.size()];
        int i = 0;
        for (int n: resultList) {
            answer[i++] = n;
        }
        
        return answer;
    }
}

/***
노래 수록 기준
1. 속한 노래가 많이 재생된 장르 먼저 수록
2. 장르 내에서 많이 재생된 노래 먼저 수록
3. 장르 내에서 재생 횟수가 같은 노래 중, 고유 번호가 낮은 노래 먼저 수록

classic 장르: 1450회 재생
pop 장르: 3100회 재생

1. pop 장르 -> classic 장르
genresMap = ("classic", 1450), ("pop", 3100)
scoreIdxMap = ("pop", 0), ("classic", 1)

2. 각 장르별 재생 횟수 내림차순 정렬
scoreList[0] = (1, 600), (4, 2500)
scoreList[1] = (0, 500), (2, 150), (3, 800)



***/
