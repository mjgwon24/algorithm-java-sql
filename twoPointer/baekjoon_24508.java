import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 바구니 개수
        int K = Integer.parseInt(st.nextToken()); // K마리 모이면 터짐
        int T = Integer.parseInt(st.nextToken()); // 최대 이동 가능 횟수

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());

        // 나도리가 한마리 이상인 바구니 개수
        int notZeroCount = 0;
        // 나도리가 터지지 않은 바구니 개수
        int notBombCount = 0;

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

            if (A[i] != 0 && A[i] != K) {
                notZeroCount++; // 0 x
                notBombCount++; // 0 x, K x
            } else if (A[i] == K) notZeroCount++;
        }

        // 모든 바구니가 비어있다면 즉시 YES
        if (notZeroCount == 0) {
            System.out.println("YES");
            return;
        }

        // 터지지않은 나도리 바구니가 하나만 있을 경우 즉시 NO
        if (notBombCount == 1) {
            System.out.println("NO");
            return;
        }

        Arrays.sort(A);

        int left = N - notZeroCount;
        int right = N - 1;

        while (T > 0 && left < right) {
            // 현재 바구니가 터지기까지 필요한 나도리 수
            int remaining = K - A[right];

            if (A[left] < remaining) {
                // 오른쪽 나도리가 터지는데 필요한 왼쪽 나도리 부족
                // 왼쪽 바구니의 모든 나도리 전부 옮기기
                A[right] += A[left];
                T -= A[left];
                left++;
            } else {
                // 오른쪽 나도리가 터지는데 필요한 왼쪽 나도리가 충분함. 오른쪽 무조건 터짐.
                // 왼쪽 나도리의 일부만 옮기기
                A[right] += remaining;
                T -= remaining;
                A[left] -= remaining;
                right--; // 터졌으니까 옮기기
                if (A[left] == 0) left++;
            }
        }

        // 모든 나도리 터뜨릴 수 있다면 YES, 아니라면 NO
        System.out.println(left > right && T >= 0 ? "YES" : "NO");
        
    }
}

// K = 5, T = 2
// 1 2 2
// T = 2
// remaining = 5 - 2 = 3
// A[left] = 1 < remaining = 3
// 왼쪽 바구니의 모든 나도리 옮기기
// A[right] += A[left], T -= A[left], left++;

// A[left] >= remaining
// 왼쪽 바구니 일부만 옮겨서 터뜨리기, 바구니 터질수있음
// A[right] += remaining, A[left] -= remaining, T-= remaining, 
// 바구니 터짐 right--
// left의 바구니가 비어버렸다면, left++
