import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수열 A 크기  (1 ≤ N ≤ 1,000,000)
        arr = new int[N];

        // 수열 A를 이루고 있는 Ai (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int[] history = new int[N];
        ArrayList<Integer> lis = new ArrayList<>();

        // 1. LIS -> 최단길이 도출
        for (int i = 0; i < N; i++) {
            int idx = searchIdx(lis, arr[i]);

            // 현재 인덱스의 숫자가 가장 끝 위치라면, 끝에 추가
            // 아니라면, 해당 위치의 값 갱신
            if (idx == lis.size()) lis.add(arr[i]);
            else lis.set(idx, arr[i]);

            // 2. 각 숫자가 LIS의 어느 위치에 있는지 저장
            dp[i] = lis.size();
            history[i] = idx;

            // System.out.printf("현재 인덱스: %d, 현재 숫자: %d, dp[%d] = %d, history[%d] = %d\n", i, arr[i], i, dp[i], i, history[i]);
        }

        // 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력
        System.out.println(lis.size());


        // 3. LIS 역추적하여 복원
        Stack<Integer> result = new Stack<>();
        int targetIdx = lis.size() - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (history[i] == targetIdx) {
                result.push(arr[i]);
                targetIdx--;
            }

            if (targetIdx < 0) break;
        }
        
        // 정답이 될 수 있는 가장 긴 증가하는 부분 수열을 출력
        while (!result.isEmpty()) System.out.print(result.pop() + " ");
    }

    // 현재 숫자가 들어가야 하는 lis 배열의 위치 도출
    static int searchIdx(ArrayList<Integer> lis, int n) {
        int left = 0;
        int right = lis.size();

        while (left < right) {
            int mid = (left + right) / 2;

            // 중간 요소가 n보다 작으면 오른쪽 탐색, 크면 왼쪽 탐색
            if (lis.get(mid) < n) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}

/***
1. LIS -> 최단길이 도출
2. 각 숫자가 LIS의 어느 위치에 있는지 저장
3. LIS 역추적하여 복원

dp[i]: i번째까지 탐색했을 때, LIS 길이
history[i]: arr[i] 값이 LIS에서 위치한 인덱스

A = {10, 20, 10, 30, 20, 50}
가장 긴 증가하는 부분 수열은 A = {10, 20, 30, 50} 이고, 길이는 4

n = 10
lis = 10
dp[0] = 1
history[0] = 0

n = 20
lis = 10, 20
dp[1] = 2
history[1] = 1

n = 10
lis = 10, 20
dp[2] = 2
history[2] = 0

n = 30
lis = 10, 20, 30
dp[3] = 3
history[3] = 2

...

최종
lis = 10, 20, 30, 50
dp = 1, 2, 2, 3, 3, 4
history = 0(10), 1(20), 0(10), 2(30), 1(20), 3(50)

뒤에서부터 뽑기 (Stack 선입선출)

targetIdx = 3
3 => history[5]
result = 50

targetIdx = 2
2 => history[3]
result = -> 30 50





***/
