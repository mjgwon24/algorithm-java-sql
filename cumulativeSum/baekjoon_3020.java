import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동굴 길이(석순, 종유석 개수)
        int H = Integer.parseInt(st.nextToken()); // 동굴 높이
        
        // 1. bottom, top 배열에 번갈아가며 값 저장
        int[] top = new int[N / 2];
        int[] bottom = new int[N / 2 + (N % 2)];
                
        for (int i = 0; i < N / 2; i++) {
            bottom[i] = Integer.parseInt(br.readLine());
            top[i] = Integer.parseInt(br.readLine());
        }
        
        if (N % 2 == 1) {
            bottom[N / 2] = Integer.parseInt(br.readLine());
        }
        
        // 2. 이진 탐색으로 충돌 계산
        Arrays.sort(bottom);
        Arrays.sort(top);
        
        int[] crash = new int[H + 1];
        
        for (int i = 1; i <= H; i++) {
            // 3. 충돌 횟수 crash 배열에 저장
            crash[i] = binarySearch(bottom, i);
            crash[i] += binarySearch(top, H - i + 1);
        }
        
        // 4. 최소값 및 개수 계산
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            if (min > crash[i]) {
                min = crash[i];
                cnt = 1;
            } else if (min == crash[i]) {
                cnt++;
            }
        }
        
        System.out.println(min + " " + cnt);
        
    }
    
    // 2. 이진 탐색으로 충돌 계산 (장애물 개수 반환)
    static int binarySearch(int[] arr, int height) {
        int left = 0; // 첫 충돌 인덱스 번호
        int right = arr.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (height <= arr[mid]) right = mid;
            else left = mid + 1;
        }
        
        return arr.length - left;
    }
    
}

// 1. bottom, top 배열에 번갈아가며 값 저장
// 2. 이진 탐색으로 충돌 계산 (장애물 개수 반환)
// 3. 충돌 횟수 crash 배열에 저장
// 4. 최소값 및 개수 계산

// left = 0, right = 6, height = 1, arr = {-3, -2, -1, 1, 3, 5}, 충돌 3개
// 1) mid = 3, 1 <= arr[3] 1, right = 3
// 2) mid = 1, 1 > arr[1] -2, left = 2
// 3) mid = 2, 1 > arr[2] -1, left = mid + 1 = 3 (마지막 통과 인덱스)
// return 6 - 3 = 3

// left: 첫 충돌 인덱스 번호
// 1) mid = 3, height = 1, arr[mid] = 1 => 충돌. 
// 이미 충돌했으므로 더 큰 값 탐색할 필요 없음. right = mid = 3
// 2) mid = 1, height = 1, arr[mid] = -2 => 통과.
// 통과했으므로 충돌하는 큰 값을 탐색해야 함. left = mid + 1 = 2
// 3) mid = 2, height = 1, arr[mid] = -1 -> 통과.
// 통과했으므로 충돌하는 큰 값을 탐색해야 함. left = mid + 1 = 3
// 4) left < right 조건 통과하지 못함. 첫 충돌 인덱스는 left인 3이 된다. (0, 1, 2 통과)
// 충돌 개수 = 총 개수 - 통과 개수 = 6 - 3 = 3
