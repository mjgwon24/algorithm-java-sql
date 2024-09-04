import java.io.IOException;

import java.util.Scanner;
import java.util.Stack;

/**
 * 스택
 * 1. 입력 값의 개수 n, n개의 수열 A[] 입력받기
 * 2. 변수 선언 - 스택에 넣을 오름차순 값 i = 1, 수열의 인덱스 j = 0
 * 3. 스택, 버퍼 선언
 * 4. while ( j < n )
 * 4.1 if ( stack.peek != A[j] )
 * 4.1.1 if ( i != A[j] ) push, bf.append("+\n"), i++
 * 4.1.2 if ( i == A[j] ) push, bf.append("+\n"), i++, pop, bf.append("-\n"), j++
 * 4.2 if ( stack.peek == A[j] ) pop, bf.append("-\n") j++
 */

class Main {
    public static void main(String[] args) throws IOException {
        // 1. 입력 값의 개수 n, n개의 수열 A[] 입력받기
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // 2. 변수 선언 - 스택에 넣을 오름차순 값 i = 1, 수열의 인덱스 j = 0, 출력 가능여부 result
        int i = 1;
        int j = 0;
        boolean result = true;

        // 3. 스택, 버퍼 선언
        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();

        // 4. while ( j < n )
        while (j < n) {
            if (stack.isEmpty() || stack.peek() != A[j]) {
                if (i > n) {
                    System.out.println("NO");
                    result = false;
                    break;
                }
                if (i != A[j]) {
                    stack.push(i);
                    bf.append("+\n");
                    i++;
                } else {
                    stack.push(i);
                    bf.append("+\n");
                    i++;
                    stack.pop();
                    bf.append("-\n");
                    j++;
                }
            } else {
                stack.pop();
                bf.append("-\n");
                j++;
            }
        }

        if (result) {
            System.out.println(bf.toString());
        }
    }   
}
