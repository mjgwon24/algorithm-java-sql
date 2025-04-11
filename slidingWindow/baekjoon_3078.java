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

        int[] nameLength = new int[N]; // i번째 학생 이름 길이
        int[] nameLengthCnt = new int[21]; // 현재 윈도우 내에서 이름 길이가 len인 학생 수

        for (int i = 0; i < N; i++) {
            nameLength[i] = br.readLine().length();
        }

        int left = 0;
        for (int right = 0; right < N; right++) {
            // 윈도우를 벗어난 학생 제거 (윈도우 길이 = K)
            if (right - left > K) {
                nameLengthCnt[nameLength[left]]--;
                left++;
            }

            int len = nameLength[right]; // right 인덱스 학생의 이름 길이
            result += nameLengthCnt[len]; // 같은 길이를 가진 친구 수 누적 (right 기준, 이전 친구들을 보며)
            nameLengthCnt[len]++;
        }

        System.out.println(result);
    }
}

/***
• 알고리즘 선택: 등수 차이가 K 이하 -> 연속된 범위(구간)을 다루는 문제 -> 슬라이딩 윈도우

• 모든 쌍을 (i, j)로 표현할 수 있고, i < j 라는 고정된 순서가 있다면, 앞에서부터 스캔하면서 쌍을 한 방향으로만 세도 된다.
    쌍은 항상 앞쪽 인덱스 i와 뒤쪽 인덱스 j로 구성된다. 순서를 뒤집지 않는다.
    즉, (1, 4)는 세지만 (4, 1)은 세지 않는다는 뜻.
    이 조건이 있으면, 앞에서부터 한 번만 스캔하면서 내 뒤에 올 친구들만 고려하면 된다.
• i < j 조건: 쌍은 항상 앞쪽 인덱스 i와 뒤쪽 인덱스 j로 구성된다. 순서를 뒤집지 않는다.
             쌍의 순서가 정해져 있음. 앞→뒤로 한 방향만 보면 됨

***/
