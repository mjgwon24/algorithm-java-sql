import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int MOD = 1000000;
    static int passwordLength;
    static String pwStr;
    static int[] pwArr;
    static long[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pwStr = br.readLine();
        passwordLength = pwStr.length();
        
        pwArr = new int[passwordLength];
        dp = new long[passwordLength][2];

        for (int i = 0; i < passwordLength; i++) {
            pwArr[i] = Integer.parseInt(pwStr.substring(i, i + 1)); 
        }

        // 초기화 - 0번째 인덱스가 0이 아닐 경우, 1
        if (pwArr[0] != 0) dp[0][0] = 1;

        // 점화식
        for (int i = 1; i < passwordLength; i++) {
            // dp[i][0] = dp[i - 1][0] + dp[i- 1][1] -> 0이 오면 불가
            if (pwArr[i] != 0) dp[i][0] = (int) (dp[i - 1][0] + dp[i - 1][1]) % MOD;

            // dp[i][1] = dp[i - 1][0] -> 걸러야됨 (합쳐서 26 이하가 되는지 확인)
            // 가능하면 전부, 불가능하면 0
            // pwArr[1] * 10 + pwArr[2] 51 <= 26 탈락
            if (pwArr[i - 1] * 10 + pwArr[i] <= 26) dp[i][1] = (int) (dp[i - 1][0]) % MOD;
        }
      
        // 나올 수 있는 해석의 가짓수
        // 1000000으로 나눈 나머지
        // 암호가 잘못되어 암호를 해석할 수 없는 경우에는 0
        int result = (int) (dp[passwordLength - 1][0] + dp[passwordLength - 1][1]) % MOD;
        System.out.print(result);
    }
}

/***
[ 요구사항 ]
어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램
암호가 잘못되어 암호를 해석할 수 없는 경우에는 0

[ 조건 ]
대화를 서로 암호화
• A를 1이라고 하고, B는 2로, 그리고 Z는 26
• "0"을 주의해라!!!!!!!!!!!!!!!!!!!!!

[ 입력 ]
5000자리 이하의 암호

[ 출력 ]
나올 수 있는 해석의 가짓수

25114
6

이 문제를 dp라는 알고리즘으로 선택하는 이유는?
• 부분 문제(Overlapping Subproblems)
    • 전체 문제를 작은 부분 문제로 나누어 해결할 수 있는가?
    • 작은 문제의 해결 결과를 사용하여 큰 문제를 해결할 수 있는가?
• 최적 부분 구조(Optimal Substructure)
    • 큰 문제의 정답이 작은 부분 문제들의 정답을 이용하여 구성될 수 있는가?
    • ex. dp[i]가 dp[i-1] 또는 dp[i-2] 등의 이전 값들을 이용하여 계산될 수 있는 구조인가?
• 중복 계산이 발생하는가?
    • 같은 입력에 대해 반복적인 계산이 이루어지는가?
    • Memoization(메모이제이션) 또는 Tabulation(배열을 사용한 점화식 계산)이 유용한가?

dp[i][j]: i번째 자리수까지 고려했을 때 마지막 선택된 현재 숫자가 두자리인지 여부 j(0 한자리, 1 두자리), 모든 경우의 수
• 주의!! 알파벳은 26까지 존재함. 27 이상의 숫자는 2자리수로 포함할 수 없음.
• "0"을 주의해라!!!!!!!!!!!!!!!!!!!!!

20114 

dp[0][0] = 1
dp[0][1] = 0
2

dp[1][0] = 0 => 0이 오면 불가
2 => 2, 0 (불가능)
dp[1][1] = 1
2 => 20

dp[2][0] = 1
20 => 20, 1
dp[2][1] = 0

dp[3][0] = 1
20, 1 => 20, 1, 1
dp[3][1] = 1
20, 1 => 20, 11

dp[4][0] = 2
20, 1, 1 => 20, 1, 1, 4
20, 11 => 20, 11, 4
dp[4][1] = 1
20, 1, 1 => 20, 1, 14



초기화 - 0번째 인덱스는 항상1
dp[0][0] = 1
pd[0][1] = 0
2

dp[1][0] = dp[0][0] (1)
dp[1][1] = dp[0][0] (1) => 통과
2 => 2, 5
2 => 25
pwArr[0] * 10 + pwArr[1] <= 26 통과

dp[2][0] = dp[1] (2)
dp[2][1] = 이전에 한 자리였을때만 판별하여 가능. 2자리였을때는 무조건 불가능.
        = dp[1][0] 인데.... 27 이상일 경우 불가 = 0
2, 5 => 2, 5, 1
2, 5 => 2, 51 (불가능) 이전 자리수가 5이므로 51 생성 불가
pwArr[1] * 10 + pwArr[2] 51 <= 26 탈락

25 => 25, 1



dp[3][0] = dp[2] = 2
dp[3][1] = dp[2][0] = 2
2, 5, 1 => 2, 5, 1, 1
2, 5, 1 => 2, 5, 11
25, 1 => 25, 1, 1
25, 1 => 25, 11

dp[4] = 6
2, 5, 1, 1 => 2, 5, 1, 1, 4
2, 5, 1, 1 => 2, 5, 1, 14
2, 5, 11 => 2, 5, 11, 4
2, 5, 11 => 2, 5, 114 (불가능)
25, 1, 1 => 25, 1, 14
25, 1, 1 => 25, 1, 1, 4
25, 11 => 25, 11, 4
25, 11 => 25, 114 (불가능)


dp[3][0] = dp[2] = 2
dp[3][1] = dp[2][0] = 2

[ 점화식 도출 ]
dp[i][0] = dp[i - 1][0] + dp[i- 1][1] -> 무조건 가능
dp[i][1] = dp[i - 1][0] -> 걸러야됨 (합쳐서 26 이하가 되는지 확인)

[ 최종 답 ]
passwordLength
dp[passwordLength - 1][0] + dp[passwordLength - 1][1]


***/
