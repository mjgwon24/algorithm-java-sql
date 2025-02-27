import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이 (10ˆ6)
        int P = Integer.parseInt(st.nextToken()); // 비밀번호로 사용할 부분문자열 길이 (10ˆ6)

        char[] charArr = new char[S];
        String str = br.readLine();
        for (int i = 0; i < S; i++) {
            charArr[i] = str.charAt(i);
        }

        // 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수
        int[] ACGTArr = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ACGTArr[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우
        int left = 0;
        int right = left + P - 1;
        int[] currentCnt = new int[4];

        // 초기화
        for (int i = 0; i < right; i++) {
            if (charArr[i] == 'A') currentCnt[0]++;
            else if (charArr[i] == 'C') currentCnt[1]++;
            else if (charArr[i] == 'G') currentCnt[2]++;
            else if (charArr[i] == 'T') currentCnt[3]++;
        }

        int result = 0;
        while (right < S) {
            // 1. 뽑아야하는 자릿수만큼의 슬라이딩 윈도우 크기에 대해 문자 개수 카운트
            if (charArr[right] == 'A') currentCnt[0]++;
            else if (charArr[right] == 'C') currentCnt[1]++;
            else if (charArr[right] == 'G') currentCnt[2]++;
            else if (charArr[right] == 'T') currentCnt[3]++;

            // 2. 현재 윈도우가 조건을 만족하는지 확인, 만족하면 결과값 증가
            if (currentCnt[0] >= ACGTArr[0] && currentCnt[1] >= ACGTArr[1] && currentCnt[2] >= ACGTArr[2] && currentCnt[3] >= ACGTArr[3]) {
                result++;
            }

            // 3. 윈도우를 오른쪽으로 한 칸씩 이동시키며 업데이트
            //  3.1 빠지는 문자 개수 감소
            if (charArr[left] == 'A') currentCnt[0]--;
            else if (charArr[left] == 'C') currentCnt[1]--;
            else if (charArr[left] == 'G') currentCnt[2]--;
            else if (charArr[left] == 'T') currentCnt[3]--;
            left++;
            right++;
        }

        // 민호가 만들 수 있는 비밀번호의 종류의 수를 출력
        System.out.println(result);
    }
}

// DNA 문자열: 모든 문자열에 등장하는 문자가 {‘A’, ‘C’, ‘G’, ‘T’} 인 문자열

// GATA, 2자리 뽑기
// 1 0 0 1
// 비밀번호: AT, TA

// CCTGGATTG, 8자리 뽑기
// 2 0 1 1
// AA... x

// 1. 뽑아야하는 자릿수만큼의 슬라이딩 윈도우 크기에 대해 문자 개수 카운트
// 2. 현재 윈도우가 조건을 만족하는지 확인, 만족하면 결과값 증가
// 3. 윈도우를 오른쪽으로 한 칸씩 이동시키며 업데이트
//  3.1 빠지는 문자 개수 감소
//  3.2 새로 추가되는 문자 개수 증가
//  3.3 변경된 개수로 조건 다시 체크
// 4. 최종적으로 조건을 모두 만족하는 경우의 수 출력
