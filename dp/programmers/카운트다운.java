import java.lang.*;
import java.util.*;

class Solution {
    static public int[] solution(int target) {
        // 한 번에 던져서 만들 수 있는 모든 점수
        // 한 번 던질 때마다 점수를 빼기 위한 리스트
        List<Integer> scores = new ArrayList<>();

        int[] dp = new int[target + 1]; // 최소 다트 수
        int[] single = new int[target + 1]; // 싱글/불 수

        // dp 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0점을 만들 수 있는 다트는 0개
        single[0] = 0; // 0점을 만들 수 있는 싱글/불 수는 0개

        // 싱글/더블/트리플 점수 초기화
        for (int i = 1; i <= 20; i++) {
            scores.add(i); // 싱글
            scores.add(i * 2); // 더블
            scores.add(i * 3); // 트리플
        }
        scores.add(50); // 불

        // for (int n: scores) System.out.print(n + " ");

        // 싱글(1~20)과 불(50) 점수만 따로 모아둔 집합
        // 이번에 던진 점수 s가 싱글/불에 해당하는 점수인지 확인용
        // 한 번 던질 때 싱글이나 불이면 -> 싱글/불 카운트 + 1
        // 더블/트리플이면 -> 카운트 x
        Set<Integer> singles = new HashSet<>();

        for (int i = 1; i <= 20; i++) singles.add(i);
        singles.add(50);

        // 목표 점수(target)를 구하기위한 반복문
        // i점에 도달하는 모든 경로 탐색
        for (int i = 1; i <= target; i++) {
            // 한 번에 던져서 만들 수 있는 모든 점수 반복
            // s점을 던져서 만들 수 있는 모든 경우 탐색
            for (int s: scores) {

                // i - s >= 0: i점에서 s점을 빼도 음수가 아니어야 함
                // dp[i - s]: i - s점까지 도달하는 최소 다트 수
                // + 1: 이번에 한번 더 던짐
                // dp[i]: 지금까지 찾은 i점까지의 최소 다트 수
                if (i - s >= 0 && dp[i - s] + 1 <= dp[i]) {
                    // single[i - s]: i - s점까지 올 때까지 싱글/불 맞춘 횟수
                    // singles.contains(s): 이번에 던진 점수 s가 "싱글/불"에 해당하면 +1, 아니면 +0
                    // cnt: i점까지 오면서 싱글/불을 맞춘 총 횟수
                    int cnt = single[i - s] + (singles.contains(s) ? 1 : 0);

                    // 최소 다트 수를 우선, 그 다음 싱글/불 횟수를 두 번째 기준으로 항상 최적의 값을 dp/single에 저장
                    // dp[i-s] + 1 < dp[i]: 이번 경로가 i점까지 가는 가장 적은 다트 수라면 -> 무조건 갱신
                    // dp[i-s] + 1 == dp[i] && cnt > single[i]: 다트 수는 같지만, 싱글/불을 더 많이 썼다면
                    if (dp[i - s] + 1 < dp[i]
                            || dp[i - s] + 1 == dp[i]
                            && cnt > single[i]) {
                        // i점일 경우, 최소 다트 수 => dp[i - s] + 1
                        // i점까지 도달하는 최소 다트 수 갱신
                        dp[i] = dp[i - s] + 1;

                        // i점까지 도달하는 싱글/불 카운트도 갱신
                        // i점일 경우, cnt 만큼의 싱글 또는 불 횟수를 맞춤
                        single[i] = cnt;
                    }
                }
            }
        }

        // 최선의 경우 던질 다트 수, "싱글" 또는 "불"을 맞춘 횟수 합
        // dp[target]: target점일 경우, 최소 다트 수
        // single[target]: 싱글 또는 불을 맞춘 횟수11
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
