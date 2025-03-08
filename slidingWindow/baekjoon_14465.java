import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K, B;
    static int[] arr;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // (1 ≤ B,K ≤ N)
        // 1번부터 N번까지의 번호가 붙은 횡단보도 N (1 ≤ N ≤ 100,000)개
        N = Integer.parseInt(st.nextToken());
        // 연속한 K개의 신호등이 존재하도록 신호등을 수리
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken()); // 망가진 신호등 개수
        arr = new int[N]; // 도로의 고장 여부 (0 정상, 1 망가짐)

        // B줄에는 고장난 신호등의 번호
        for (int i = 0; i < B; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[n - 1] = 1;
        }
        result = B;

        searchKNormal();

        // 정상적으로 작동하는 연속 K개의 신호등이 존재하려면
        // 최소 몇 개의 신호등을 수리해야 하는지 출력
        System.out.println(result);
    }

    static void searchKNormal() {
        int start = 0;
        int end = K - 1;
        int brokenCnt = 0;

        // 초깃값 설정
        for (int i = 0; i < end; i++) {
            if (arr[i] == 1) brokenCnt++;
        }

        while (end < N) {
            // end가 망가졌는지?
            if (arr[end] == 1) brokenCnt++;

            // 고쳐야하는 신호등 개수 갱신
            result = Math.min(result, brokenCnt);

            // move
            if (arr[start] == 1) brokenCnt--;
            start++;
            end++;
        }
    }
}

/***
 10 6 5
 2
 10
 1
 5
 9
 망가진 신호등: 1, 2, 5, 9, 10
 연속해서 정상이어야하는 신호등 개수: 6
 최소 몇개의 신호등을 수리해야 하는가?

 윈도우 크기 = K(6)
 start = 0, end = 5
 윈도우를 옮겨가면서 망가진 신호등의 최소 개수 도출
 이동 횟수 = 10(N) - 6(K) + 1 = 5회 이동

 ***/