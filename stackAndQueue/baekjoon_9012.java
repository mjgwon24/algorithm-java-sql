import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            String str = br.readLine();
            int stackValue = 0;
            boolean flag = true;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '(') stackValue++;
                else if (c == ')') stackValue--;

                if(stackValue < 0) {
                    System.out.println("NO");
                    flag = false;
                    break;
                }
            }

            if (flag) System.out.println(stackValue == 0 ? "YES" : "NO");
        }
    }
}

/***
 * 스택 - (가 들어오면 추가, )가 들어오면 제거
 * 최종 스택의 크기가 0일 시, YES
 */
