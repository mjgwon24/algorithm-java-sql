import java.util.*;

class Solution {
    public int[] solution(int target) {
        HashSet<Integer> scores = new HashSet<>(); // 한 번 던졌을 때, 획득할 수 있는 모든 점수들
        HashSet<Integer> singleScores = new HashSet<>(); // 한 번 던졌을 때, 싱글 또는 불 만으로 획득할 수 있는 점수들
        int[] minDarts = new int[target + 1]; // i점을 획득하는데 던진 최소 다트 수
        int[] maxSingles = new int[target + 1]; // i점을 획득하는데 던진 최대 싱글 또는 불 수
        Arrays.fill(minDarts, Integer.MAX_VALUE);
        minDarts[0] = 0;
            
        // scores, singleScores 초기화
        for (int i = 1; i <= 20; i++) {
            scores.add(i); // 싱글
            scores.add(i * 2); // 더블
            scores.add(i * 3); // 트리플
            singleScores.add(i);
        }
        scores.add(50); // 불
        singleScores.add(50);
        
        for (int i = 1; i <= target; i++) {
            
            // 한번 던져서 나올 수 있는 모든 점수를 탐색
            for (int score: scores) {
                // i - score가 0 이상이어야지만 성공 가능
                if (i - score < 0) continue;
                
                // 싱글 또는 불로 도달할 수 있는 점수면, 싱글 또는 불로 점수 먹기
                if (singleScores.contains(score) && minDarts[i - score] + 1 <= minDarts[i]) {
                    minDarts[i] = minDarts[i - score] + 1;
                    maxSingles[i] = maxSingles[i - score] + 1;
                } else if (!singleScores.contains(score) && minDarts[i - score] + 1 < minDarts[i]) {
                    // 싱글 또는 불로 도달할 수 없는 점수면, 다트 최소만 가져가기
                    minDarts[i] = minDarts[i - score] + 1;
                    maxSingles[i] = maxSingles[i - score];
                }
            }
        }
        
        return new int[]{minDarts[target], maxSingles[target]};
    }
}

/*
자료형
HashSet<Integer> scores 한 번 던졌을 때, 획득할 수 있는 모든 점수들
HashSet<Integer> answerScores 한 번 던졌을 때, 싱글 또는 불 만으로 획득할 수 있는 점수들
int[] minDarts i점을 획득하는데 던진 최소 다트 수
int[] maxSingles i점을 획득하는데 던진 최대 싱글 또는 불 수
*/
