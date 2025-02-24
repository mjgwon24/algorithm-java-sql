import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 상근이가 가진 CD 수
            int M = Integer.parseInt(st.nextToken()); // 선영이가 가진 CD 수

            if (N == 0 && M == 0) return;

            Set<Integer> aNums = new HashSet<>();

            for (int i = 0; i < N; i++) {
                // 상근이가 가진 CD 번호 오름차순
                // 1 2 3
                aNums.add(Integer.parseInt(br.readLine()));
            }

            int results = 0;
            for (int i = 0; i < M; i++) {
                // 선영이가 가진 CD 번호 오름차순
                // 1 2 4
                int n = Integer.parseInt(br.readLine());
                // bNums[i] = ;
                if (aNums.contains(n)) results++;
            }

            // 두 사람이 동시에 가지고 있는 CD의 개수 출력
            System.out.println(results);
        }
    }
}

// 10^8회 연산
// HashSet으로 key로 넣기 (O(1))
