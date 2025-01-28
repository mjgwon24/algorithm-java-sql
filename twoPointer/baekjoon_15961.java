import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, d, k, c;
    static int[] belt;
    static int rst;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수 (2 ≤ N ≤ 3,000,000)
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수 (2 ≤ d ≤ 3,000)
        k = Integer.parseInt(st.nextToken()); // 선택해야하는 접시 수 (2 ≤ k ≤ 3,000 (k ≤ N))
        c = Integer.parseInt(st.nextToken()); // 추가 초밥 번호 (1 ≤ c ≤ d)
        belt = new int[N];
        
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
        
        sliding(N == k ? true : false);
        System.out.println(rst);
    }
    
    static void sliding(boolean isOne) {
        int left = 0;
        int right = k - 1; // 3
        int cnt = 0;
        int[] equalCnt = new int[d + 1];
        int nowSelect = 1;
        boolean isSelectPlus = false;
        equalCnt[c] = Integer.MAX_VALUE;
        
        if (isOne) cnt = N - 1;
        
        // 1. k-1개를 미리 초기화
        for (int i = 0; i < right; i++) { // 0, 1, 2 초기화 필요
            equalCnt[belt[i]]++;
            if (equalCnt[belt[i]] == 1) nowSelect++;
        }
        rst = nowSelect;
        
        // 2. right를 1씩 늘려가며 탐색
        while (cnt < N) {
            equalCnt[belt[right % N]]++;
            if (equalCnt[belt[right % N]] == 1) nowSelect++;
            
            rst = Math.max(rst, nowSelect);
            
            // 돌리기
            // System.out.printf("돌리기 전 equalCnt[%d] = %d\n", belt[left % N], equalCnt[belt[left % N]]);
            equalCnt[belt[left % N]]--;
            // System.out.printf("돌린 후 equalCnt[%d] = %d\n", belt[left % N], equalCnt[belt[left % N]]);
            if (equalCnt[belt[left % N]] == 0) {
                nowSelect--;
                // System.out.printf("제거된 초밥 종류: %d", belt[left % N]);
            }
            // System.out.println();
            // System.out.println();
            left++;
            right++;
            cnt++;
        }
    }
}

// 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값 출력
// 추가 초밥이 추가되는 경우 고려
// 벨트를 슬라이딩 윈도우로 탐색

// 1. k-1개를 미리 초기화
// 2. right를 1씩 늘려가며 탐색
//    접시 종류 중복 카운트
// 3. 추가 초밥이 선택되지 않은 경우에는 +1
// 4. 최종 선택가능 가짓수 출력
// 탐색을 시도하는 총 횟수: N번
