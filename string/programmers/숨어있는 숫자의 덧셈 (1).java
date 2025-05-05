class Solution {
    public int solution(String str) {
        int answer = 0;
        
        for (char c: str.toCharArray()) {
            if (Character.isDigit(c)) {
                answer += c - '0';
            }
        }
        
        return answer;
    }
}
