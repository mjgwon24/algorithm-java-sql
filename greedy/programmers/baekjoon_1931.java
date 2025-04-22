import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, answer;
    static int[][] info;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 회의 수
        info = new int[N][2];

        StringTokenizer st;
        
        // 회의 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            // 회의 시작시간, 끝나는 시간
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        // 1. 정렬 -> 기준: 끝나는 시간 오름차순, 같다면 시작 시간 오름차순
        Arrays.sort(info, (int[] a, int[] b) -> {
            if (a[1] != b[1]) return a[1] - b[1];

            return a[0] - b[0];
        });

        // for (int[] aa: info) System.out.println(aa[0] + " " + aa[1]);

        int nowTime = 0;

        for (int[] r: info) {
            if (r[0] >= nowTime) {
                answer++;
                nowTime = r[1];
            }
        }
        
        // 최대 사용할 수 있는 회의 최대 개수
        System.out.println(answer);
    }
}

/***
회의가 겹치지 않게 하면서 회의실 사용할 수 있는 회의 최대 개수 도출
7
2 2
1 5
1 1
0 3
1 3
0 5
2 5
result = 3

1. 정렬 -> 기준: 끝나는 시간 오름차순, 같다면 시작 시간 오름차순
2. 정답 도출


nowTime = 0, answer = 0

1 1
nowTime = 1, answer = 1

2 2
nowTime = 2, answer = 2

0 3
1 3

0 5
1 5
2 5
nowTime = 5, answer = 3


---
nowTime = 0, answer = 0

1 4
nowTime = 4, answer = 1

3 5
0 6

5 7
nowTime = 7, answer = 2

3 8
5 9
6 10

8 11
nowTime = 11, answer = 3

8 12
2 13
12 14
nowTime = 12, answer = 4

***/
