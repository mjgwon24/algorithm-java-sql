import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static boolean success;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            success = false;
            dfs(str);

            if (success) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        
        }

        //  빈 문자로 바꿀 수 있는지 없는지 "여부" 확인
        bw.flush();
    }

    static void dfs(String s) {
        if (success) return;

        int stst = 1; // 연속된 문자열 개수
        int startIdx = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                // 이전 문자와 현재 문자가 같다면, 그룹 문자열 길이 늘리기
                stst++;
            } else {
                // 이전 문자와 현재 문자가 다르다면, 그룹 문자열을 제거하고 인덱스 초기화
                if (stst >= 2) {
                    dfs(s.substring(0, startIdx) + s.substring(startIdx + stst));
                }

                stst = 1;
                startIdx = i;
            }
        }

        // 들어온 s가 2 이상이고 전부 그룹 문자열이라면 true
        if (s.length() >= 2 && s.length() == stst) success = true;
    }
}

/***
• DFS - "여부" 한번만 확인
• BFS - 모든 경우를 탐색해 최단 거리/횟수 도출
***/
