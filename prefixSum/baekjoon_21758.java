import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    static int N;
    static int[] arr;
    static boolean[] selected;
    static int[] beePositions = new int[2];
    static int[] prefixSum;
    static int maxHoney;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 장소 수 (3 <= N <= 100000)
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 꿀의 양 (1 <= arr[i] <= 10000)
        }
        
        // 누적합 생성
        prefixSum = new int[N];
        prefixSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        // arr: 9 9 4 1 4 9 9
        // prefixSum: 9 18 22 23 27 36 45
        
        // 0. N = 3일때, 별도 처리
        if (N == 3) {
            maxHoney = Math.max(arr[0] * 2, Math.max(arr[1] * 2, arr[2] * 2));
            System.out.println(maxHoney);
            return;
        }
        
        // 1. 벌통이 왼쪽 끝에 있는 경우 (벌 하나는 오른쪽 끝에 고정)
        for (int i = 1; i < N - 1; i++) {
            int honey = (prefixSum[N - 1] - arr[0]) + (prefixSum[N - 1] - prefixSum[i]) - arr[i];
            maxHoney = Math.max(maxHoney, honey);
        }
        
        // 2. 벌통이 오른쪽 끝에 있는 경우 (벌 하나는 왼쪽 끝에 고정)
        for (int i = 1; i < N - 1; i++) {
            int honey = (prefixSum[N - 1] - arr[N - 1]) + prefixSum[i - 1] - arr[i];
            maxHoney = Math.max(maxHoney, honey);
        }
        
        // 3. 벌통이 중간에 있는 경우 (벌들을 양 끝에 고정)
        for (int i = 1; i < N - 1; i++) {
            int honey = prefixSum[i - 1] + prefixSum[N - 2] - prefixSum[i] + arr[i];
            maxHoney = Math.max(maxHoney, honey);
        }
       
        System.out.println(maxHoney);
    }
    
    
}

// 가능한 최대 꿀의 양 도출
// 2개 위치 선택 - 벌 위치(동일할 수 없음), 1개 위치 선택 - 벌통 위치
// 벌은 벌통으로 이동. 시작 위치의 꿀은 채취 불가

