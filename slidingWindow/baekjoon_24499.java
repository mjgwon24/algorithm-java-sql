import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] arr;
    static int result;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 애플파이의 개수
        K = Integer.parseInt(st.nextToken()); // 먹으려는 애플파이의 개수

        arr = new int[N]; // 애플파이의 맛있는 정도
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 길이 K인 윈도우를 돌려가며 최댓값 탐색
        calMax();

        // 블롭이 먹을 애플파이의 맛의 합의 최댓값을 출력
        System.out.println(result);
    }

    // 길이 K인 윈도우를 돌려가며 최댓값 탐색
    static void calMax() {
        int left = 0;
        int right = left + K - 1;
        int tempSum = 0;

        // 초깃값
        for (int i = 0; i < right; i++) {
            tempSum += arr[i];
        }
        
        while (left < N) {
            // 현재 윈도우에서의 맛 최댓값 도출
            tempSum += arr[right % N];

            // 갱신
            result = Math.max(result, tempSum);

            // 윈도우 이동
            tempSum -= arr[left];
            left++;
            right++;
        }
    }
}

// N개의 애플파이를 만들었으며, 이를 원 모양으로 책상에 배치
// 연속으로 배치되어 있는 K개의 애플파이를 먹으려 한다.

// 길이 K인 윈도우를 돌려가며 최댓값 탐색
