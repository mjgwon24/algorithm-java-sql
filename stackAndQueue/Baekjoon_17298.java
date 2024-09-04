import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.Stack;

/**
 * 17298 오큰수
 * 1. 수열의 크기 N 입력받기
 * 2. 버퍼로 배열 A[N] 입력받기
 * 3. 스택 선언
 * 4. for ( N만큼 반복 )
 * 4.1 while ( 스택이 비어 있지 않고, 현재 수열 값이 top에 해당하는 수열보다 클 때까지 )
 * 4.1.1 pop
 * 4.1.2 정답 배열에 오큰수를 현재 수열로 저장
 * 4.2 현재 수열을 스택에 push
 * 5. while ( 스택이 빌 때까지 )
 * 5.1 스택에 있는 인덱스의 정답 배열에 -1 저장
 * 6. 정답 배열 bw로 출력
 */

class Main {
    public static void main(String[] args) throws IOException {
         // 1. 수열의 크기 N 입력받기
        // 2. 버퍼로 배열 A[N] 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] result = new int[N];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i]){
                result[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
    }
}
