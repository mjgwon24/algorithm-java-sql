import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String explosionStr = br.readLine();

        String result = explodeString(str, explosionStr);

        // 5. 결과가 비어있다면 FRULA 출력
        System.out.println(result.isEmpty() ? "FRULA" : result);
    }

    static String explodeString(String str, String explosionStr) {
        StringBuilder stack = new StringBuilder(); // 스택 역할
        int expLen = explosionStr.length();

        for (char ch : str.toCharArray()) {
            stack.append(ch);

            // 1. 스택의 끝부분이 폭발 문자열과 일치하는지 확인
            if (stack.length() >= expLen) {
                boolean isExplosion = true;

                // 2. 폭발 문자열과 비교
                for (int i = 0; i < expLen; i++) {
                    if (stack.charAt(stack.length() - expLen + i) != explosionStr.charAt(i)) {
                        isExplosion = false;
                        break;
                    }
                }

                // 3. 폭발 문자열과 일치하면 제거
                if (isExplosion) {
                    stack.delete(stack.length() - expLen, stack.length());
                }
            }
        }

				// 4. 스택에 남은 문자열 반환
        return stack.toString();
    }
}
