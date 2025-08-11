class Solution {
    
    // 규칙에 맞지 않는 아이디 대상으로 규칙에 맞는 아이디를 추천해주는 프로그램 개발
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
        
        // 1. 모든 대문자를 대응되는 소문자로 치환
        // 2. (알파벳 소문자, 숫자, -, _, .)을 제외한 모든 문자 제거
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            
            // 3. 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환
            if (sb.length() >= 1 && sb.charAt(sb.length() - 1) == '.' && c =='.') continue;
            
            // 4. 마침표가 처음이나 끝에 위치한다면 제거
            if (sb.length() == 0 && c == '.') continue;
            
            if (c == '-' || c == '_' || c == '.' || isDigit(c) || isAlphabet(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        
        // 4. 마침표가 처음이나 끝에 위치한다면 제거
        if (sb.length() >= 1 && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        
        // 5. 빈 문자열이 되었다면, 'a' 대입
        if (sb.length() == 0) sb.append('a');
        
        // 6. 길이가 16자 이상이면, 첫 15개의 문자를 제외한 나머지 문자들 모두 제거
        if (sb.length() >= 16) sb = new StringBuilder(sb.substring(0, 15));
        
        // 만약 제거 후 마침표가 끝에 위치한다면 끝에 위치한 마침표 문자 제거
        if (sb.length() >= 1 && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        
        // 7. 길이가 2자 이하라면, 마지막 문자를 길이가 3이 될 떄까지 반복해서 끝에 붙임
        while (sb.length() <= 2) {
            sb.append(sb.charAt(sb.length() - 1));
        }
        
        return sb.toString();
    }
    
    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    static boolean isAlphabet(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
