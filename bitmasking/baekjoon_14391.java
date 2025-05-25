import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++){
            String str = br.readLine();
            
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str.substring(j, j + 1));
            }
        }

        int max = 0;
        
        // 모든 칸을 가로 또는 세로 상태로 지정 (가로 0, 세로 1)
        int totalStatus = (1 << (N * M)) - 1;
        for (int i = 0; i <= totalStatus; i++) {
            max = Math.max(max, calSum(i));
        }

        // 점수 최댓값
        System.out.println(max);
    }

    // 잘린 상태에 따른 합 도출
    static int calSum(int status) {
        int sum = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            int tempSum = 0;
            
            for (int j = 0; j < M; j++) {
                // 0이면 가로 상태 (연달아있으면 자릿수 증가됨)
                if ((status & (1 << (i * M + j))) == 0)
                    tempSum = tempSum * 10 +  arr[i][j];
                else {
                    // 연속 상태 끊김
                    sum += tempSum;
                    tempSum = 0;
                }
            }

            sum += tempSum;
        }

        // 세로
        for (int j = 0; j < M; j++) {
            int tempSum = 0;
            
            for (int i = 0; i < N ; i++) {
                // 1이면 세로 상태 (연달아있으면 자릿수 증가됨)
                if ((status & (1 << (i * M + j))) != 0)
                    tempSum = tempSum * 10 +  arr[i][j];
                else {
                    // 연속 상태 끊김
                    sum += tempSum;
                    tempSum = 0;
                }
            }

            sum += tempSum;
        }

        return sum;
    }
}

/*
2 3
123
312
result: 435
-> 123 + 312

2 2
99
11
result: 182
-> 91 + 91

4 3
001
010
111
100
result: 1131
-> 1010 + 110 + 11

1 1
8
result: 8
-> 8

[놓치기 쉬운 포인트]
- 마지막 값 합산
- 세로는 열 기준 반복문
- 비트 연산 (1, 0 판별 - &)
- 연달아있으면 자릿수 증가, 연속이 끊기면 합산은 도중에 끊김

*/
