class Solution {
    boolean solution(String s) {
        boolean answer = true;

        long pCnt = s.chars().filter(c -> c == 'p' || c == 'P').count();
        long yCnt = s.chars().filter(c -> c == 'y' || c == 'Y').count();

        return pCnt == yCnt;
    }
}
