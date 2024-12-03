import java.util.*;

class Solution {
    static int[][] patterns = {
        {1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}
    };
    static int[] correctCounts = new int[3];
    static List<Integer> ranks = new ArrayList<>();
    
    public int[] solution(int[] answers) {
        checkAnswer(answers);
        checkRank();
        
        int i = 0;
        int[] answer = new int[ranks.size()];
        for (int winner : ranks) {
            answer[i] = winner;
            i++;
        }
        
        return answer;
    }
    
    static void checkRank() {
        int max = correctCounts[0];
        
        for (int i = 1; i < 3; i++) {
            if (max < correctCounts[i]) max = correctCounts[i];
        }
        
        for (int i = 0; i < 3; i++) {
            if (max == correctCounts[i]) {
                ranks.add(i + 1);
            }
        }
        
        Collections.sort(ranks);
    }
    
    static void checkAnswer(int[] answers) {
        int resultScore = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (patterns[i][j % patterns[i].length] == answers[j]) correctCounts[i]++;
            }        
        }
    
    }
    
}
