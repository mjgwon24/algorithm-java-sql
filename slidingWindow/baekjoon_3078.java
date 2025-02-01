import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    static int N, K;
    static long result;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 이름 개수 (3 ~ 300000)
        K = Integer.parseInt(st.nextToken()); // 등수 범위 (1 ~ 300000)

        int[] nameLengthCount = new int[21]; // 이름 길이 (2~20)
        int[] nameLength = new int[N];

        for (int i = 0; i < N; i++) {
            nameLength[i] = br.readLine().length();
        }

        result = 0;

        // {7, 5, 6, 5, 7, 6}
        // k = 3
        // (1, 3), (2, 5)
        // 최악의 경우 3 * 10^5 연산
        int left = 0;
        for (int right = 0; right < N; right++) {
             // 윈도우 범위를 벗어난 학생 제거
            if (right - left > K) {
                nameLengthCount[nameLength[left]]--;
                left++;
            }
            
            int len = nameLength[right];
            result += nameLengthCount[len]; // 같은 길이를 가진 학생 수 더하기
            nameLengthCount[len]++;
        }

        System.out.println(result);
    }
}


// 좋은 친구 - 등수의 차이가 K보다 작거나 같으면서 이름의 길이가 같은 친구

// k = 2
// (0, 1), (0, 2) left = 0, right = 1, 2
// (1, 2), (1, 3) left = 1, right = 2, 3
// (2, 3) left = 2, right = 3

// {7, 5, 6, 5, 7, 6}
// k = 3
// (1, 3), (2, 5)
// 답: 2쌍

// nameLength = {3, 3, 3, 3}
// 시간복잡도: 10^8 - 1초
// 출력 범위: N
