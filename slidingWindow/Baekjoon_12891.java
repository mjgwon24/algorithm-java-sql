import java.io.IOException;
import java.util.Scanner;

/**
 * 12891. DNA 비밀번호 구하기
 *
 * 슈도코드
 * 1. 문자열 길이 S, 부분문자열 길이 P 입력받기
 * 2. 문자열 inputStr 입력받기
 * 3. 포함되어야 할 문자(A,C,G,T) 개수 배열C[4] 입력받기
 * 4. 변수 초기화 - cnt = 0, dnaStr
 * 5. for ( S-P+1회 반복 )
 * 5.2 for ( 4 ), dnaStr에 A가 있다면 C[0]--; C가 있다면 C[1]--; G가 있다면 C[2]--; T가 있다면 C[3]--;
 * 5.3 if ( C[0] == 0 && C[1] == 0 && C[2] == 0 && C[3] == 0 ), cnt++;
 * 6. 계산된 cnt 출력
 */
class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int P = sc.nextInt();
        sc.nextLine();

        String inputStr = sc.nextLine();
        int[] C = new int[4];

        for (int i = 0; i < 4; i++) {
            C[i] = sc.nextInt();
        }

        System.out.println(dnaPw(S, P, inputStr, C));
    }
    
    public static int dnaPw(int S, int P, String A, int[] C) {
        // 변수 초기화
        int cntA = 0;
        int cntC = 0;
        int cntG = 0;
        int cntT = 0;
        char start_c;
        char end_c;

        int cnt = 0;
        String dnaStr;

        for (int i = 0; i <= (S - P); i++) {
            if (i == 0) {
                // i부터 i+P까지 문자열 자르기
                dnaStr = A.substring(i, i + P);

                for (char c : dnaStr.toCharArray()) {
                    if (c == 'A') cntA++;
                    if (c == 'C') cntC++;
                    if (c == 'G') cntG++;
                    if (c == 'T') cntT++;
                }
            } else {
                // 부분 문자열 이전, 마지막 알파벳
                start_c = A.charAt(i - 1);
                end_c = A.charAt(i + P - 1);

                if (start_c == 'A') cntA--;
                if (start_c == 'C') cntC--;
                if (start_c == 'G') cntG--;
                if (start_c == 'T') cntT--;

                if (end_c == 'A') cntA++;
                if (end_c == 'C') cntC++;
                if (end_c == 'G') cntG++;
                if (end_c == 'T') cntT++;
            }
            
            
                    if (cntA >= C[0] && cntC >= C[1] && cntG >= C[2] && cntT >= C[3]) {
                        cnt++;
                    }
        }
        return cnt;
    }
}
