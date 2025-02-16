import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static int[] stats;
    static int[] results = new int[2];
    static boolean allMinus = true;
    static boolean allPlus = true;
    static int endMinusIdx, firstPlusIdx;
    static long result = Long.MAX_VALUE;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 전체 용액 수 (2 ≤ * ≤ 10^5)
        stats = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 합은 long 가능성 있음
            // 특성값은 모두 서로 다름 -> HashSet, HashMap 가능성
            // 정렬된 순서로 주어짐
            stats[i] = Integer.parseInt(st.nextToken()); // 용액의 특성값 (-10^10 ≤ * ≤ 10^10)
            if (stats[i] > 0) {
                allMinus = false;
                if (firstPlusIdx == 0) firstPlusIdx = i;
            }
            if (stats[i] < 0) {
                allPlus = false;
                endMinusIdx = i;
            }
        }
        
        // 전부 마이너스일 경우, 가장 큰 수 2개 출력
        if (allMinus) {
            System.out.println(stats[N - 2] + " " + stats[N - 1]);
            return;
        }
        
        // 전부 플러스일 경우, 가장 작은 수 2개 출력
        if (allPlus) {
            System.out.println(stats[0] + " " + stats[1]);
            return;
        }
        
        int left = 0;
        int right = N - 1;
        long sum = Long.MAX_VALUE;
        
        // 10^5
        while (left < right) {
            sum = stats[left] + stats[right];
            // System.out.printf("sum: %d, left: %d, right: %d\n", sum, left,right);
            
            if (Math.abs(sum) <= Math.abs(result)) {
                result = sum;
                results[0] = stats[left];
                results[1] = stats[right];
            }
            
            if (sum == 0) {
                System.out.println(stats[left] + " " + stats[right]);
                return;
            }
            
            // 좁혀주는식으로 포인터를 움직여야 함
            // -면 left++, +면 right--
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        
        // 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력
        // 오름차순으로 출력
        System.out.println(results[0] + " " + results[1]);
    }
}

// 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수
// 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수
// 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합
