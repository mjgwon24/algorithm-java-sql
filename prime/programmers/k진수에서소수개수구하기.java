import java.util.*;

class Solution {
    // n(10진수)를 k진수로 바꾼 뒤, 0을 기준으로 끊어서 나온 수들 중 소수가 몇 개인지 반환하는 함수
    public int solution(int n, int k) {
        int answer = 0;

        // 1. n을 k진수로 변환해 문자열로 받음
        String kStr = Integer.toString(n, k);

        // 2. 0을 기준으로 split
        String[] tokens = kStr.split("0+"); // 연속된 0도 분리

        // 3. 각 토큰이 소수인지 판별
        for (String token : tokens) {
            if (token.isEmpty()) continue; // 빈 문자열 건너뜀
            long num = Long.parseLong(token);
            if (num > 1 && isPrime(num)) answer++;
        }

        return answer;
    }

    // 소수 판별 함수
    static boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
