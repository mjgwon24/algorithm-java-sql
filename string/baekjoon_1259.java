import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if (Integer.parseInt(str) == 0) return;

            String newStr = preProcess(str);
            int n = newStr.length();
            int left = n / 2 - 1;
            int right = n / 2 + 1;

            // 현재 문자열이 대칭인지만 확인하면 됨.
            // 범위를 벗어나지 않으며, 중심을 기준으로 양 옆이 대칭인지 확인
            while (left >= 0 && right < n
                    && newStr.charAt(left) == newStr.charAt(right)) {
                // 같음. 대칭을 확인하였으므로 확장
                left--;
                right++;
            }

            if (left == -1 && right == n) System.out.println("yes");
            else System.out.println("no");
        }
    }

    static String preProcess(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c: s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append('#');

        return sb.toString();
    }
}
