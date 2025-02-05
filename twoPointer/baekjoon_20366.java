import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N;
    static int[] heights;
    statinc long result = Long.MAX_VALUE;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(st.nextToken()); // 눈덩이 개수 (4 ≤ N ≤ 600)
        heights = new int[N]; // 각 눈덩이 지름 (1 ≤ H ≤ 10^9)
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        
        // [시간복잡도] 정렬 -> O(NlogN) (무시가능)
        Arrays.sort(heights);
        
        // two pointer
        // [시간복잡도] 두 개의 눈덩이를 모두 선택하는 모든 조합 -> O(N^2)
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // 첫 번째 눈사람
                int sum1 = heights[i] + heights[j];
                
                // 두 번째 눈사람
                int left = 0;
                int right = N - 1;
                
                // [시간복잡도] 각 조합에 대해 포인터를 움직이며 탐색 -> O(N)
                while (left < right) {
                    // 현재 비교 중인 눈덩이와 겹치는 경우 건너뛰기
                    if (left == i || left == j) {
                        left++;
                        continue;
                    }
                    
                    if (right == i || right == j) {
                        right++;
                        continue;
                    }
                    
                    int sum2 = heights[left] + heights[right];
                    result = Math.min(result, Math.abs(sum1 - sum2));
                    
                    // 최적화
                    if (result == 0) {
                        System.out.println(result);
                        return;
                    }
                    
                    // 더 작은 차이 탐색을 위해 포인터 이동 (sum2의 크기만 조절 가능)
                    if (sum1 > sum2) left++; // sum2 키우기위해 left 증가
                    else right--; // sum2 줄이기위해 right 감소
                }
            }
        }
        
        
        System.out.println(result);
    }
}

// 눈사람 키 차이 중 최솟값 도출
// 서로 다른 4개의 눈덩이로 두 눈사람 만들기

// 최종 시간 복잡도: O(N^3) = (6 * 10^2)^3 = 6^3 * 10^6 (안전)
// 1초: 10^8회 연산
