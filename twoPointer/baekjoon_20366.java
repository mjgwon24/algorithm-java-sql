import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] heights;
    static long result = Long.MAX_VALUE;
    static int[] selectedFirst = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 눈덩이 개수 (4 <= * <= 600)
        heights = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken()); // 각 눈덩이 지름 (<= 10^9) ...int 초과 위험
        }
        
        // 1. 정렬
        Arrays.sort(heights);

        // 2. 첫번째 눈사람 조합 생성 (중복이 일어나지 않도록 맨앞, 맨뒤부터 선택해서 안으로 들어가기)
        // N = 4
        // i: 0 ~ (N - 4), j: 3 ~ (N - 1)
        for (int i = 0; i <= N - 4; i++) {
            selectedFirst[0] = i;
            for (int j = N - 1; j >= i + 3; j--) {
                selectedFirst[1] = j;

                long heightFirst = heights[i] + heights[j];

                // 3. 두번째 눈사람 조합 생성 - 투포인터 탐색
                twoPointSearch(i, j, heightFirst)
                if (result == 0) break;
            }
            if (result == 0) break;
        }

        System.out.println(result);
    }

    // 3. 두번째 눈사람 조합 생성 - 투포인터 탐색
    static void twoPointSearch(int start, int end, long heightFirst) {
        int left = start + 1;
        int right = end - 1;
        long minDiff = Long.MAX_VALUE;

        while (left < right) {
            while (left == selectedFirst[0] || left == selectedFirst[1]) {
                left++;
            }

            while (right == selectedFirst[0] || right == selectedFirst[1]) {
                right--;
            }

            long heightSecond = heights[left] + heights[right];
            long newDiff = heightFirst - heightSecond;

            if (newDiff == 0) {
                result = 0;
                return;
            }

            minDiff = Math.min(minDiff, Math.abs(newDiff));

            //    diff > 0, heightFirst > heightSecond => 두번째 눈사람 키우기. left++
            if (newDiff > 0) left++;
        
            //    diff < 0, heightFirst < heightSecond => 두번째 눈사람 줄이기. right--
            if (newDiff < 0) right--;
        }

        result = Math.min(result, minDiff);
    }
}

// heights = {2 3 5 5 9}
// 1. 정렬
// 2. 첫번째 눈사람 조합 생성 (중복이 일어나지 않도록 맨앞, 맨뒤부터 선택해서 안으로 들어가기)
// 3. 두번째 눈사람 조합 생성 - 투포인터 탐색
//    left = 0; right = 1
//    diff = heightFirst - heightSecond
//    diff > 0, heightFirst > heightSecond => 두번째 눈사람 줄이기. right--
//    diff < 0, heightFirst < heightSecond => 두번째 눈사람 키우기. left++
//    diff == 0, return



