import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<String[]> inputOptions = new ArrayList<>();
    static Set<Character> options = new HashSet<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 옵션 개수 (1 ≤ N ≤ 30)

        // 옵션을 나타내는 문자열
        for (int k = 0; k < N; k++) {
            // 5개 이하의 단어 (공백 한 칸으로 구분)
            String[] strArr = br.readLine().split(" ");
            boolean isBrake = false;

            // 1. 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 확인
            for (int i = 0; i < strArr.length; i++) {
                char c = Character.toLowerCase(strArr[i].charAt(0));

                if (!options.contains(c)) {
                    options.add(c);
                    printResult(strArr, i, 0);
                    isBrake = true;
                    break;
                }
            }

            if (isBrake) continue;

            // 2. 모든 단어의 첫 글자가 이미 지정이 되어있다면 
            for (int i = 0; i < strArr.length; i++) {
                for (int j = 1; j < strArr[i].length(); j++) {
                    char c = Character.toLowerCase(strArr[i].charAt(j));

                    if (!options.contains(c)) {
                        options.add(c);
                        printResult(strArr, i, j);
                        isBrake = true;
                        break;
                    }
                }

                if (isBrake) break;
            }

            if (isBrake) continue;

            // 3. 어떠한 것도 단축키로 지정할 수 없다면
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) sb.append(" ");
                sb.append(strArr[i]);
            }

            System.out.println(sb.toString());
        }
    }

    // 답 출력
    static void printResult(String[] strArr, int optionIdx, int charIdx) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0) sb.append(" ");
            if (i == optionIdx) {
                sb.append(strArr[i], 0, charIdx)
                    .append("[")
                    .append(strArr[i], charIdx, charIdx + 1)
                    .append("]")
                    .append(strArr[i], charIdx + 1, strArr[i].length());
            } else sb.append(strArr[i]);
        }

        System.out.println(sb.toString());
    }
}

/***
[ 단축키를 의미하는 대표 알파벳 지정 순서 ]
1. 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 확인
    -> 만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정
2. 모든 단어의 첫 글자가 이미 지정이 되어있다면 
    -> 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정
3. 어떠한 것도 단축키로 지정할 수 없다면
    -> 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분 X
4. 바로 출력

[ solution ]
5
inputOptions = {
New,
Open,
...,
{Save, As},
{Save, All},
}

단축키 목록 options (대소문자 구분 x)
New => [N], options = N
Open => [O], options = N, O
Save => [S]
Save As => [A]
Save All => 
***/
