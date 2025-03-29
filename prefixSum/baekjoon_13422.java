import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 마을에 있는 집의 개수 N(1 ≤ N ≤ 100,000)
            int N = Integer.parseInt(st.nextToken());
            // 도둑이 돈을 훔칠 연속된 집의 개수 M(1 ≤ M ≤ N)
            int M = Integer.parseInt(st.nextToken());
            // 자동 방범 장치가 작동하는 최소 돈의 양 K(1 ≤ K ≤ 1,000,000,000, K는 정수)
            int K = Integer.parseInt(st.nextToken());
            

            st = new StringTokenizer(br.readLine());
            
            // N개의 집에서 각각 보관중인 돈의 양이 시계방향 순서
            int[] moneys = new int[N];
            long[] prefixSum = new long[N];
            for (int i = 0; i < N; i++) {
                // 1이상 10,000이하
                moneys[i] = Integer.parseInt(st.nextToken());
                if (i != 0) prefixSum[i] = prefixSum[i - 1] + moneys[i];
                else prefixSum[0] = moneys[0];
            }

            int result = 0;

            // N, M이 같을 경우 한번만
            if (N == M) {
                long stealAmount = prefixSum[N - 1];
                if (stealAmount < K) {
                    result++;
                }
            } else {
                for (int i = M - 1; i < N + M - 1; i++) {
                    long stealAmount= 0;
                    
                    if (i < N) {
                        // 범위 내, stealAmount = prefixSum[i] - prefixSum[i - M]
                        if (i - M >= 0) stealAmount = prefixSum[i] - prefixSum[i - M];
                        else stealAmount = prefixSum[i];
                    } else {
                        // 범위 밖, prefixSum[i%N] + (prefixSum[N - 1] - prefixSum[i - M])
                        stealAmount = prefixSum[i % N] + (prefixSum[N - 1] - prefixSum[i - M]);
                    }
    
                    // if (i == 6) System.out.println(stealAmount);
    
                    if (stealAmount < K) {
                        result++;
                        // System.out.println("훔칠수있는 인덱스: " + i);
                    }
                }
            }

            // 연속된 M개의 집에서 돈을 훔치는 방법의 가짓수
            System.out.println(result);
        }
    }
}

/***
• 첫 번째 집과 마지막 집 또한 이웃하기 위해 입력의 첫 번째와 마지막이 서로 연결되어 있다고 가정
• M개의 연속된 집에서 돈을 훔침
• K원 이상의 돈을 훔친다면 자동 방범장치가 작동

N = 8, M = 3, K = 15
3 4 7 5 6 4 2 9
prefixSum = {3, 7, 14, 19, 25, 29, 31, 40} int[N]

돈을 훔치는 경우의 수
i = 2 (i = M - 1 부터 시작)
stealAmount = prefixSum[2] = 14 -> ok. result++
i = 3
stealAmount = prefixSum[3] - prefixSum[0] = 16 -> no.
...
i = 6
stealAmount = prefixSum[6] - prefixSum[3] = 12 = 31 - 19 -> no.
=> 범위 내, stealAmount = prefixSum[i] - prefixSum[i - M]

i = 8 (6, 7, 0)
stealAmount = 14
prefixSum[0] + (prefixSum[7] - prefixSum[5]) = 3 + (40 - 29) -> ok. result++

prefixSum[i%N] + (prefixSum[N - 1] - prefixSum[i - M]) = 3 + (40 - 29) -> ok. result++

N = 8, M = 3, K = 15

i = 9 (7, 0, 1)
prefixSum[1] + (prefixSum[7] - prefixSum[6])

i < N + M - 1 까지 범위임

i = 10 () => x





***/
