import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K, max;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수열 길이
        K = Integer.parseInt(st.nextToken()); // 삭제할 수 있는 최대 횟수
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0; int r = 0;
        int blankCnt = 0; int temp = 0;

        while (r < N) {
            // 추가되는 숫자가 짝수면 temp++
            if (arr[r] % 2 == 0) temp++;
            else {
                // 추가되는 숫자가 홀수면 blankCnt++
                blankCnt++;
            }

            max = Math.max(max, temp);

            // blankCnt가 K를 초과할 경우
            while (blankCnt > K) {
                // left를 blankCnt가 K를 초과하지 않을 때까지 ++
                if (arr[l] % 2 == 0) temp--;
                
                // 옮기면서 1이 빠지면 그만큼 temp--
                else blankCnt--;
                l++;
            }
            r++;
        }

        // 최대 K번 원소를 삭제한 수열에서
        // 짝수로 이루어져 있는 연속한 부분 수열 중
        // 가장 긴 길이 출력
        System.out.println(max);
    }
}

/***
9 2
2 1 2 2 4 3 8 6 7
result: 6

짝수는 1, 홀수는 0
1 0 1 1 1 0 1 1 0

홀수를 최대 2개 포함하면서 영역 구하기
int blankCnt = 0

l = 0, r = 0 => 1
result = 1
right++

l = 0, r = 1 => 1 0
result = 1
blankCnt = 1
right++

l = 0, r = 2 => 1 0 1
result = 2
blankCnt = 1
right++

l = 0, r = 3 => 1 0 1 1
result = 3
blankCnt = 1
right++

l = 0, r = 4 => 1 0 1 1 1
result = 3
blankCnt = 1
right++

l = 0, r = 5 => 1 0 1 1 1 0
result = 4
blankCnt = 2 // 최댓값 도달
right++

l = 0, r = 6 => 1 0 1 1 1 0 1
result = 5
blankCnt = 2 // 최댓값 도달
right++

l = 0, r = 7 => 1 0 1 1 1 0 1 1
result = 6
blankCnt = 2 // 최댓값 도달
right++

l = 0, r = 8 => 1 0 1 1 1 0 1 1 0
result = 6
blankCnt = 3 // 최댓값 초과
left를 blankCnt가 2개 될때까지 ++
right++

l = 2, r = 9 => 1 1 1 0 1 1 0
result = 5
blankCnt = 2 // 최댓값 도달
right++




***/
