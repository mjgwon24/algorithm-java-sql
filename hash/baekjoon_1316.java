import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static int checkNum;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            Set<Character> charCheckerSet = new HashSet<>(); // 남아있는 그룹 문자
            Set<Character> historyCheckerSet = new HashSet<>(); // 기록된 문자

            int idx = 1;
            char c = str.charAt(0);
            charCheckerSet.add(c); // h
            historyCheckerSet.add(c); // h
            checkNum++;

            while (idx < str.length()) {
                char tempC = str.charAt(idx);

                // 연속되는 경우는 문제 없음
                if (c == tempC) {
                    idx++;
                    continue;
                }

                // 연속되지 않을 경우, 새로운 문자인지 확인
                else {
                    // 처음 나오는 문자일 경우
                    if (!historyCheckerSet.contains(tempC)) {
                        charCheckerSet.add(tempC);
                        historyCheckerSet.add(tempC);
                    }

                    // 기록이 있던 문자일 경우
                    // 연속되지 않은 문자 확정
                    else {
                        checkNum--;
                        break;
                    }

                    c = tempC;
                    idx++;
                    continue;
                }
            }
        }

        System.out.print(checkNum);
    }
}
/**
 * 이미 나왔던 문자가 이후 또 나오면 그룹 단어가 아님 -> 이를 체크
 */
