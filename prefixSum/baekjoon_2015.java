import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N, K;
    static int[] A;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 목표 부분합 값
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 등장 횟수 저장 Map (누적합 값, 등장 횟수)
        Map<Long, Long> prefixCountMap = new HashMap<>();

        long sum = 0; // 현재까지 누적합
        long answer = 0;

        // 초기화 - 누적합 0 1개부터 시작
        prefixCountMap.put(0L, 1L);

        for (int i = 0; i < N; i++) {
            sum += A[i]; // 현재 위치까지의 누적합

            // sum - K 값이 과거에 몇 번 나왔는지 확인
            answer += prefixCountMap.getOrDefault(sum - K, 0L);

            prefixCountMap.put(sum, prefixCountMap.getOrDefault(sum, 0L) + 1);
        }

        // 합이 K인 부분합 개수
        System.out.println(answer);
    }
}

/***
• 부분합: 1 ≤ i ≤ j ≤ N인 정수 i와 j에 대해 A[i]부터 A[j]까지의 합

[사고 흐름]
- 슬라이딩 윈도우 적용 불가 (양수만 있는게 아니고, 음수값도 있어 범위가 줄어들지 않음)
- 부분합을 구하는 대표적인 방법인 누적합(prefix sum) 사용
A[i] + A[i+1] + ... + A[j] == K
⇒ sum[j] - sum[i] == K
⇒ sum[i] == sum[j] - K
⇒ K를 만족하는 구간의 개수: 현재 누적합에서 K를 뺀 값이, 과거에 나온 누적합 중 몇 번 있었는지 찾기

[알고리즘 및 변수]
• 누적합 + 해시맵(과거의 sum[i] 값을 해시맵에 저장)

Map<Long, Long> prefixCountMap 누적합이 매우 커질 수 있어서 long 필요
long sum 누적합이 int 범위 초가할 수 있음


***/
