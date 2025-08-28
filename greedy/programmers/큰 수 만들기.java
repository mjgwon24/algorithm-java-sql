class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int N = number.length();
        int idx = 0;
        
        for (int i = 0; i < N - k; i++) {
            char max = '0';
            
            // idx부터 k + i까지 중 가장 큰 수 탐색
            for (int j = idx; j <= k + i; j++) { 
                if (number.charAt(j) > max) { 
                    max = number.charAt(j);
                    idx = j + 1;
                }
            }
            
            sb.append(max);
        }
        
        return sb.toString();
    }
}
