import java.io.*;
import java.lang.*;

public class Main {
    static int N;
    static boolean success;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            dfs(str);

            if (success) bw.write(1 + "\n");
            else bw.write(0 + "\n");

            success = false;
        }

        bw.flush();
    }

    static void dfs(String s) {
        if (success) return;

        int stst = 1; // 연속된 문자 개수
        int startIdx = 0; // 그룹 시작 인덱스

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                // 이전 문자와 현재 문자가 동일하다면 연속된 문자 증가
                stst++;
            } else {
                // 이전 문자와 현재 문자가 다른 경우
                if (stst >= 2) {
                    // 연속된 문자가 2개 이상이라면 제거 가능
                    dfs(s.substring(0, startIdx) + s.substring(startIdx + stst, s.length()));
                }

                stst = 1;
                startIdx = i;
            }
        }

        // 문자열 전체가 같은 문자로 이루어져있고 길이가 2 이상이라면 true
        if (s.length() >= 2 && s.length() == stst) success = true;
    }
}
