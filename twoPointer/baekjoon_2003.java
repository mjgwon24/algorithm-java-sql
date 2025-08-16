import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static long M;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        long sum = 0;
        int answer = 0;

        // end 포인터가 배열의 끝까지 이동하며 창문을 확장
        for (int end = 0; end < N; end++) {
            sum += arr[end];

            // sum이 M보다 크면, M보다 작거나 같아질 때까지 start 포인터를 이동시키며 창문을 축소
            while (sum > M && start <= end) {
                sum -= arr[start];
                start++;
            }

            // 창문 축소 후, sum이 M과 같은지 확인
            if (sum == M) {
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}
