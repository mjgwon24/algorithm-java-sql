import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N;
    static int[][] lines;
    static int maxLines;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 전깃줄 개수 (≤ 100)
        lines = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken()); // ≤ 500
            lines[i][1] = Integer.parseInt(st.nextToken()); // ≤ 500
        }
        
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
        LIS_Optimized();
        
        System.out.println(N - maxLines);
    }
    
    static void LIS_Optimized() {
        int[] lis = new int[N]; // LIS 배열 (최대 크기 N)
        int length = 0; // 현재 LIS 길이
        
        for (int i = 0; i < N; i++) {
            int num = lines[i][1]; // 현재 전깃줄의 B 좌표
            
            // 이분 탐색으로 LIS 위치 찾기
            int idx = lowerBound(lis, length, num);
            
            // LIS 업데이트
            lis[idx] = num;
            if (idx == length) length++; // 새로운 원소 추가 시 길이 증가
        }
        
        maxLines = length;
    }
    
    // lowerBound: lis 배열에서 num이 들어갈 위치 찾기
    static int lowerBound(int[] arr, int len, int key) {
        int left = 0, right = len;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < key) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}


// 교차하지 않게 없애야하는 전깃줄 최소 개수

// 최대 설치 가능한 전깃줄 개수 (정렬되어있어야 함)
// {1, 8}, {2, 2}, {3, 9}, {4, 1}, {6, 4}, {7, 6}, {9, 7}, {10, 10}
// y축이 증가하는 수로 이어져있으면 설치 가능 전깃줄이 됨. 
// 증가하는 최대한 긴 수들의 길이 => 최대 설치가능 전깃줄 개수

// 없애야하는 전깃줄 개수 = 전체 전깃줄 개수 - 최대 설치가능 전깃줄 개수
// 8 - 5 = 3

// 1. 전깃줄 리스트 정렬
// 2. 증가하는 최대한 긴 수 길이 도출
// 3. 없애야하는 전깃줄 개수 도출
