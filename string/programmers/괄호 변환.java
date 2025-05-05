class Solution {
    public String solution(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열 반환
        if (p.isEmpty()) return "";

        // 2. u, v로 분리
        int idx = splitIndex(p);
        String u = p.substring(0, idx);
        String v = p.substring(idx);

        // 3. u가 올바른 괄호 문자열이면, v에 대해 재귀 수행
        if (isCorrect(u)) {
            return u + solution(v);
        } else {
            // 4. u가 올바르지 않으면, 변환 절차 수행
            StringBuilder sb = new StringBuilder();
            
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");
            sb.append(reverse(u.substring(1, u.length() - 1)));
            
            return sb.toString();
        }
    }

    // 균형잡힌 괄호 문자열 분리 인덱스 구하기
    private int splitIndex(String p) {
        int left = 0, right = 0;
        
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) return i + 1;
        }
        
        return p.length();
    }

    // 올바른 괄호 문자열인지 판별
    private boolean isCorrect(String s) {
        int cnt = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            else cnt--;
            if (cnt < 0) return false;
        }
        
        return cnt == 0;
    }

    // 괄호 방향 뒤집기
    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '(') sb.append(')');
            else sb.append('(');
        }
        
        return sb.toString();
    }
}
