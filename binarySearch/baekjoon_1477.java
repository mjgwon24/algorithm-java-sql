import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, L;
    static List<Integer> info = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 휴게소 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려고 하는 휴게소 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        // 현재 휴게소의 위치 (고속도로의 시작으로부터 얼만큼 떨어져 있는지)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            info.add(Integer.parseInt(st.nextToken()));
        }

        info.add(0);
        info.add(L);
        Collections.sort(info);

        // 이분 탐색
        int start = 1;
        int end = L - 1;
        int result = 0; // 정답 후보 (최소 가능한 최대 간격)

        while (start <= end) {
            int mid = (start + end) / 2;

            int divide = 0; // mid 간격으로 만들기 위해 필요한 휴게소 개수

            for (int i = 1; i < info.size(); i++) {
                int diff = info.get(i) - info.get(i - 1) - 1; // 인접 휴게소 사이 거리

                // 휴게소가 없는 구간의 길이 diff가 현재 예상하는 간격 mid보다 크면
                if (diff >= mid) {
                    divide += (diff / mid); // 해당 구간에 설치해야 할 휴게소 개수 누적
                }
            }

            // 설치해야 할 휴게소 개수가 M보다 많으면, 간격 늘리기
            if (divide > M) {
                start = mid + 1; // 간격 늘림 (최소 간격 증가)
            } else {
                // 설치 개수 만족(혹은 남음) -> 더 작은 간격도 시도
                end = mid - 1;
                result = mid; // 현재 간격 후보 저장
            }
        }

        System.out.println(result);
    }
}

/***
 휴게소 위치: 고속도로의 시작으로부터 얼만큼 떨어져 있는지
 • 이미 휴게소 있는 곳 또 세울 수 없음
 • 고속도로의 끝에도 세울 수 없음

 휴게소를 M개 지어서, 휴게소가 없는 구간의 길이의 최댓값 -> 최소로 만들기

 • 현재 가장 긴 구간을 절반으로 나누기 -> 매번 가장 긴 구간을 쪼개는 것은 항상 최적의 해를 보장하지 않음
 • 이분 탐색으로 해결 -> 최대 구간 길이를 X 이하로 만들 수 있나? 라는 결정문제로 바꿔서 생각

[ 파라메트릭스 서치 사고 흐름 ]
 1. 최적화 문제인가?
 "최대/최소"라는 단어가 있는가?
 2. 탐욕법이 최적임을 증명할 수 있는가?
 아니면, 반례가 있을까?
 3. YES/NO로 바꿔서 생각할 수 있는가?
 “이 값 이하로 만들 수 있나?”
 4. 이분 탐색이 가능한 범위/조건이 있는가?
 정수 범위, 단조성(값이 증가하면 조건이 바뀌는 성질)
 5. 결정문제를 빠르게 판단할 수 있는 함수(코드)를 상상할 수 있는가?
 ***/
