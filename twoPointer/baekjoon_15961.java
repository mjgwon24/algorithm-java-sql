import java.io.*;
import java.util.*;
import java.lang.*;

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
        N = Integer.parseInt(st.nextToken()); // 벨트 접시 수 (2 ≤ N ≤ 3,000,000)
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수 (2 ≤ d ≤ 3,000)
        k = Integer.parseInt(st.nextToken()); // 선택하는 초밥 범위 (2 ≤ k ≤ 3,000)
        c = Integer.parseInt(st.nextToken()); // 추가 초밥 번호 (1 ≤ c ≤ d)
        belt = new int[N];
        
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine()); // 벨트 위의 초밥
        }
        
        // 슬라이딩 윈도우로 초밥 가짓수 계산
        sliding();
        System.out.println(result);
    }
    
    // 슬라이딩 윈도우로 초밥 가짓수 계산
    static void sliding() {
        int left = 0;
        int right = k - 1; // 3
        int selectCnt = 1; // 추가 초밥은 항상 선택하고있다고 가정
        int[] equalSushi = new int[3001];
        equalSushi[c] = N + 1; // 추가 초밥 예외처리
        
        // 1. 초기화
        for (int i = 0; i < right; i++) { // 0, 1, 2
            equalSushi[belt[i]]++;
            if (equalSushi[belt[i]] == 1) selectCnt++; // 처음으로 추가된 초밥의 경우 선택 개수 증가
        }
        
        // 2. right를 하나씩 늘려가면서 추가되는 초밥 탐색, 중복 초밥 갱신
        // 최대 N회 반복 가능 (단, k가 N일시 1회 반복)
        while (left < N) {
            equalSushi[belt[right % N]]++;
            if (equalSushi[belt[right % N]] == 1) selectCnt++;
            
            // 4. 최종 초밥 가짓수 갱신
            result = Math.max(result, selectCnt);
            
            // left 이동시 중복 초밥 배열 갱신
            if (equalSushi[belt[left]] == 1) selectCnt--;
            equalSushi[belt[left]]--;
            right++;
            left++;
        }
    }
}

// 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥 가짓수 최댓값 도출

// 슬라이딩 윈도우로 초밥 가짓수 계산
// 1. 초기화
// 2. right를 하나씩 늘려가면서 추가되는 초밥 탐색, 중복 초밥 갱신
// 3. 추가 초밥 고려
// 4. 최종 초밥 가짓수 갱신

// 나올 수 있는 최댓값: N + 1 => 3,000,001
// int 범위: 2 * 10^9 (안전)

// 시간복잡도: N => 3 * 10^6
// 1초: 연산 10^8 (안전)
