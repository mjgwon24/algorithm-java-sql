import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N;
    static int[][] lineList;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 전깃줄 개수 (N <= 100)
        lineList = new int[N][2]; // 전깃줄 왼쪽 오른쪽 위치 (input <= 500)
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lineList[i][0] = Integer.parseInt(st.nextToken());
            lineList[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 1. 전깃줄 리스트 정렬
        Arrays.sort(lineList, Comparator.comparingInt(arr -> arr[0]));
        
        // 2. 증가하는 최대한 긴 수 길이 도출
        System.out.println(N - lis());
    }
    
    // 2. 증가하는 최대한 긴 수 길이 도출
    static int lis() {
        List<Integer> lisList = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int value = lineList[i][1];
            
            if (lisList.isEmpty() || lisList.get(lisList.size() - 1) < value) {
                // LIS 마지막 값보다 크면 추가
                lisList.add(value);
            } else {
                // 이진 탐색으로 적절한 위치 찾아 값 교체
                int idx = Collections.binarySearch(lisList, value);
                if (idx < 0) idx = -idx - 1; // 삽입 위치 변환
                lisList.set(idx, value);
            }
        }
        
        return lisList.size();
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
