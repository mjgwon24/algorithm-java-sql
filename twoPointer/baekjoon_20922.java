import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N, K; // 수열 길이 N, 같은 정수 제한 K
    static int[] arr;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(findMaxLength());
    }
    
    static int findMaxLength() {
        int max = 0;
        
        // 1. left = 0, right는 0부터 하나하나 늘려가면서 중복 개수 탐색
        Map<Integer, Integer> dupMap = new HashMap<>();
        int left = 0;
        
        for (int right = 0; right < N; right++) {
            // 2. 각 정수별 중복 개수 저장
            dupMap.put(arr[right], dupMap.getOrDefault(arr[right], 0) + 1);
            
            // 3. if 중복 개수 > K, 중복이 사라질때까지 left++;
            while (dupMap.get(arr[right]) > K) {
                dupMap.put(arr[left], dupMap.get(arr[left]) - 1);
                left++;
            }
            
            // 4. 연속 부분 수열의 길이 갱신 (Math.max)
            // System.out.printf("left: %d, right: %d, dupMap.get(%d): %d\n", left, right, arr[right], dupMap.get(arr[right]));
            max = Math.max(max, right - left + 1);
        }
        
        return max;
    }
}

// 수열에서 같은 정수 K개 이하로 포함한 최장 연속 부분 수열의 길이 도출
// 수열에서 같은 정수가 K개 이하인 경우의 길이

// 1. left = 0, right는 0부터 하나하나 늘려가면서 중복 개수 탐색
// 2. 각 정수별 중복 개수 저장
// 3. if 중복 개수 > K, 중복이 사라질때까지 left++;
// 4. 연속 부분 수열의 길이 갱신 (Math.max)
