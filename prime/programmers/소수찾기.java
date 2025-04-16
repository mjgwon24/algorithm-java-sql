import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n) {
        // n: 1000000이하의 자연수
        // 1부터 n 사이 소수 개수 반환
        return isPrime(n);
    }
    
    // 에라토스테네스의 체
    static int isPrime(int max) {
        boolean[] isP = new boolean[max + 1];
        // 소수 개수 초기화
        int cnt = max - 1; // 1 빼고
        
        // 초기화 (처음에는 모두 소수라고 가정)
        for (int i = 2; i <= max; i++) {
            isP[i] = true;
        }
        
        // 이중 for문으로 소수가 아닌 수들 거르기 (제곱에서부터 max까지 해당 수의 배수 모두 제거)
        // Q. p * p가 max보다 클 때는 더 이상 반복하지 않아도 됨 -> 왜???
        for (int p = 2; p * p <= max; p++) {
            // 현재 수가 소수일 경우,
            if (isP[p]) {
                // Q. 어째서 시작이 p*p 일까?
                // long 자료형을 사용하는 이유: int 자료형으로 할 시, int 자료형에서의 최댓값을 넘어가면 음수가 되어 j <= max 조건에서 항상 true가 나 무한루프에 빠짐
                for (int j = p * p; j <= max; j += p) {
                    if (j > 1000000) break;
                    if (isP[(int)j]) cnt--;
                    isP[(int)j] = false;
                }
            }
        }
        
        return cnt;
    }
}
