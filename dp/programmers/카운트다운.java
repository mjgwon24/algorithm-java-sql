import java.lang.*;
import java.util.*;

class Solution {
    static public int[] solution(int target) {
        // i점일 경우 최소 사용 다트 수
        int[] dp = new int[target + 1];
        // i점일 경우 사용 싱글 또는 불 다트 수
        int[] single = new int[target + 1];

        // dp 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 한 번 다트를 던졌을 때 획득할 수 있는 모든 점수
        Set<Integer> scores = new HashSet<>();

        for (int i = 1; i <= 20; i++) {
            scores.add(i); // 싱글
            scores.add(i * 2); // 더블
            scores.add(i * 3); // 트리플
        }
        scores.add(50); // 불

        // 싱글 또는 불을 사용했을 경우 획들할 수 있는 모든 점수
        Set<Integer> singles = new HashSet<>();

        for (int i = 1; i <= 20; i++) {
            singles.add(i);
        }
        singles.add(50);

        // i점부터 target점 까지 탐색
        for (int i = 1; i <= target; i++) {
            // 한 번 던졌을 때 획득 가능한 점수를 모두 탐색하며
            // 사용 가능한 최소 다트 수, 싱글 또는 불 수 갱신
            for (int s: scores) {
                // 0 미만일 경우는 불가능. 현재 s점의 다트를 맞췄다고 가정할 경우, 0 이상이 되는지 확인
                if (i - s >= 0) {
                    // 싱글, 불 개수 계산
                    int cnt = single[i - s] + (singles.contains(s) ? 1 : 0);

                    // 현재 s점을 맞춘 상황에서, 전체 던진 다트 수가 더 적을 경우, 무조건 갱신
                    // 현재 s점을 맞춘 상황에서, 전체 던진 다트 수가 같을 경우, 싱글/볼 개수가 더 많을 경우 dp와 single을 갱신
                    if (dp[i - s] + 1 < dp[i] ||
                            dp[i - s] + 1 == dp[i] && cnt > single[i]
                    ) {
                        dp[i] = dp[i - s] + 1;
                        single[i] = cnt;
                    }
                }
            }
        }

        // 목표 점수 target에 도달하기 위한최소 다트 수, 싱글 또는 불을 맞춘 횟수 합 반환
        return new int[]{dp[target], single[target]};
    }
}
/*
다트를 던지면서 점수 깎아서 정확히 0점 만들기
남은 점수보다 큰 점수로 득점하면 버스트가 되며 실격

- 최소한의 다트로 0점
- 방법이 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이

싱글(x1), 더블(x2), 트리플(x3), 불(50)
점수 1 ~ 20

1. 문제를 상태(state)로 변환
    - 상태: 남은 점수(0 ~ target)
    - 행동: 한 번에 던질 수 있는 점수(싱글/더블/트리플/불)
    - 목표: 최소 횟수로 0점에 도달
2. 한 번에 만들 수 있는 점수 집합 구하기
    - 1~20 싱글/더블/트리플, 불(50)
    - 싱글/불 맞춤 여부 (동점 시 우위)
3. 최소 횟수와 싱글/불 횟수를 모두 저장하는 DP
    - i: i점을 만든느 최소 다트 수, 그 때의 싱글/불 횟수
    - 작은 점수부터 차례로 갱신 (Bottom-Up DP)
4. 점화식
    - i = min(i - 점수 + 1)
        => 싱글/불이면 싱글 카운트 +1, 아니면 그대로
5. 모든 점수 반복


*/
