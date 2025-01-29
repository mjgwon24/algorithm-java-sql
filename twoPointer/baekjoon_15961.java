import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, d, k, c;
    static int[] belt;
    static int result;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수 (2 ≤ N ≤ 3,000,000)
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수 (2 ≤ d ≤ 3,000)
        k = Integer.parseInt(st.nextToken()); // 선택해아하는 접시 수 (2 ≤ k ≤ 3,000 (k ≤ N))
        c = Integer.parseInt(st.nextToken()); // 추가 초밥 번호 (1 ≤ c ≤ d)
        belt = new int[N];
        
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
        
        // 1. 길이가 k인 슬라이딩 윈도우 실행
        sliding(N == k ? true : false);
        System.out.println(result);
    }
    
    // 1. 길이가 k인 슬라이딩 윈도우 실행
    // 도는 횟수는 총 N번, 만약 N == k 라면 도는 횟수는 1번
    static void sliding(boolean isOne) {
        int left = 0;
        int right = k - 1; // 3
        int[] equalCnt = new int[d + 1];
        equalCnt[c] = Integer.MAX_VALUE; // 추가 초밥은 언제나 선택되어있는 상태
        // int cnt = 0; // 연산 횟수
        int select = 1; // 현재 범위에서 선택한 초밥의 가짓수 (추가 초밥은 언제나 선택되어있는 상태)
        
        // 초기화 - 중복 초밥 카운트
        for (int i = 0; i < right; i++) { // 0, 1, 2
            equalCnt[belt[i]]++;
            if (equalCnt[belt[i]] == 1) select++;
        }
        
        if (isOne) left = N - 1;
        
        while (left < N) {
            // right 초밥 계산
            equalCnt[belt[right % N]]++;
            if (equalCnt[belt[right % N]] == 1) select++;
            // System.out.printf("%d session, plus equalCnt[%d] = %d, select = %d\n", left, belt[right % N], equalCnt[belt[right % N]], select);
            
            // 최대 초밥 수 갱신
            result = Math.max(select, result);
            
            // 이동
            equalCnt[belt[left]]--;
            if (equalCnt[belt[left]] == 0) select--;
            // System.out.printf("%d session, minus equalCnt[%d] = %d, select = %d\n\n", left, belt[left], equalCnt[belt[left]], select);
            left++;
            right++;
        }
    }
}

// 먹을 수 있는 초밥 가짓수 최댓값 도출

// 1. 길이가 k인 슬라이딩 윈도우 실행
// 2. 가짓수 최댓값 갱신 및 최종 결과 출력
