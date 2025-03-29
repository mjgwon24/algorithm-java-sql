class Solution
{
    public int solution(String s) {
        if (s.length() == 0 || s == null) return 0;
        
        // # 추가, 홀수 길이로 변형
        String modifiedStr = preProcess(s);
        // System.out.println(modifiedStr);
        
        int n = modifiedStr.length();
        // 해당 문자가 중심인 팰린드롬 반지름 저장 배열
        int[] p = new int[n]; 
        // 가장 긴 팰린드롬 중심, 오른쪽 끝
        int center = 0, right = 0;
        int maxLen = 0; // 가장 긴 팰린드롬 부분 문자열 길이
        
        // 변환시킨 문자열 순회하며 팰린드롬 길이 계산
        // #a#b#c#d#c#b#a#
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            // System.out.println("i: " + i + ", right: " + right);
            // System.out.println("반대쪽 인덱스: " + mirror);
            
            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
                // System.out.println("i가 right보다 작음. p[i] 갱신(p[i]: " + p[i]);
            }
            
            // 중심을 기준으로 양쪽으로 확장하며 팰린드롬 확인
            // 반지름이 증가될따마다 탐색 범위도 자동으로 늘어남
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n
                  && modifiedStr.charAt(i - p[i] - 1) == modifiedStr.charAt(i + p[i] + 1)) {
                // modifiedStr.charAt(0) == modifiedStr.charAt(2) 양쪽이 같은가? 같다면 반지름 증가
                p[i]++; 
            }
            // System.out.println("반지름 크기: " + p[i]);
            
            // 현재 인덱스가 중심인 팰린드롬이 right보다 오른쪽으로 확장되어있다면, 업데이트
            if (i + p[i] > right) {
                center = i;       // 중심 업데이트
                right = i + p[i]; // 새로운 오른쪽 경계 업데이트
            }
            // System.out.println("업데이트된 center: " + center + ", right: " + right);
            
            maxLen = Math.max(maxLen, p[i]); // 가장 긴 팰린드롬 길이 갱신
        }
        
        // for (int nn: p) {
        //     System.out.print(nn + " ");
        // }
        // System.out.println();

        return maxLen;
    }
    
    // abcdcba -> #a#b#c#d#c#b#a#
    static String preProcess(String s) {
        StringBuilder sb = new StringBuilder();
        
        // 각 문자 사이마다 '#'을 추가해 경계 표시 + 홀수 길이로 반환
        for (char c: s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append('#');
        
        return sb.toString();
    }
}
