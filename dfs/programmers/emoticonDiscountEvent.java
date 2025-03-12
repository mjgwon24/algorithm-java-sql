import java.util.*;

class Solution {
    static int emoticonUsersMaxCnt;  // 이모티콘 플러스 서비스 가입 최대 수
    static int emoticonUsersMaxSales; // 가입 수가 최대일 때, 이모티콘 매출액 최대값
    static final int[] DISCOUNT_RATES = {10, 20, 30, 40}; // 사용 가능한 할인율

    public int[] solution(int[][] users, int[] emoticons) {
        emoticonUsersMaxCnt = 0;
        emoticonUsersMaxSales = 0;

        // DFS를 이용해 모든 할인율 조합 탐색
        findBestDiscount(0, new int[emoticons.length], users, emoticons);
        
        return new int[]{emoticonUsersMaxCnt, emoticonUsersMaxSales};
    }

    // DFS를 이용한 할인율 조합 탐색
    private void findBestDiscount(int idx, int[] discountRates, int[][] users, int[] emoticons) {
        if (idx == emoticons.length) {
            // 할인율이 정해진 경우, 가입자 수 및 매출 계산
            calculate(users, emoticons, discountRates);
            return;
        }

        for (int rate : DISCOUNT_RATES) {
            discountRates[idx] = rate;
            findBestDiscount(idx + 1, discountRates, users, emoticons);
        }
    }

    // 현재 할인율 조합에 대해 가입자 수와 매출을 계산
    private void calculate(int[][] users, int[] emoticons, int[] discountRates) {
        int plusSubscribers = 0;
        int totalSales = 0;

        for (int[] user : users) {
            int minDiscount = user[0]; // 최소 구매 할인율
            int minSpending = user[1]; // 이모티콘 구매 후 이모티콘 플러스 가입 기준 금액

            int spending = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (discountRates[i] >= minDiscount) { // 유저의 기준 할인율 충족 시 구매
                    spending += emoticons[i] * (100 - discountRates[i]) / 100;
                }
            }

            if (spending >= minSpending) {
                plusSubscribers++; // 이모티콘 플러스 가입
            } else {
                totalSales += spending; // 매출 합산
            }
        }

        // 최적의 경우 갱신
        if (plusSubscribers > emoticonUsersMaxCnt) {
            emoticonUsersMaxCnt = plusSubscribers;
            emoticonUsersMaxSales = totalSales;
        } else if (plusSubscribers == emoticonUsersMaxCnt) {
            emoticonUsersMaxSales = Math.max(emoticonUsersMaxSales, totalSales);
        }
    }
}

/***
목표 (1번 목표가 우선이며, 2번 목표가 그 다음)
1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
2. 이모티콘 판매액을 최대한 늘리는 것.

이모티콘 할인 행사
- n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매
- 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정

카카오톡 사용자: 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입
- 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
- 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 
  이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입

[ 입력 ]
int[][] users: 카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 [비율, 가격]
-> users[] 비율: 사용자가 구매하기위한 최소 할인 비율
int[] emoticons: 이모티콘 m개의 정가를 담은 1차원 정수 배열

[ 반환 ]
answer[0]: 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수
answer[1]: 이모티콘 매출액

1. 이모티콘 플러스 서비스 가입자 최대 도출
1.1 [dfs] 이모티콘 플러스 가입 가능 여부 파악 (최소 할인 비율 설정, 할인은 모든 상품 개별 적용)

각 상품별 최소 할인 비율 범위 설정 - int[] discountRatioList

초기화 
users별 최대 필요 할인율 (maxRatio)
Arrays.fill(discountRatioList, maxRatio)

이동
emotions 인덱스별로 돌아가며 할인율을 각각 한 단계씩 낮추기 (40 -> 30 -> 20 -> 10)
• 이모티콘 플러스 가입자가 max보다 줄어들 시, 더이상 낮출 필요가 없으므로 다음 인덱스로 이동

40 40 => 0, 19200

40 30 => 1, 4200
40 20 => 0, 8400 (중지)

30 40 => 1, 5400
30 30 =>
...

20 40 => ...



(1) emoticons[0], emoticons[1]에 users[0]의 최소 구매 비율인 40% 할인
users[0] => 7000*0.6 + 9000*0.6 = 9600
users[1] => 7000*0.6 + 9000*0.6 = 9600
이모티콘 플러스 가입자 0명

이모티콘 플러스 가입 유도를 위해 할인율을 낮춘다
(2) emoticons[0]에 users[0]의 최소 구매 비율인 40% 할인, emoticons[1]에 30% 할인
users[0] => emoticons[0] 7000*0.6 = 4200 구매, emoticons[1] 할인율 미달로 인해 구매 x
users[1] => emoticons[0] 7000*0.6 = 4200 구매, emoticons[1] 9000*0.7 = 6300 => 이모티콘 플러스 가입
∆ 이모티콘 플러스 서비스 가입 1, 매출액 4200

(3) emoticons[0]에 40% 할인, emoticons[1]에 20% 할인
users[0] => emoticons[0] 7000*0.6 = 4200 구매, emoticons[1] 할인율 미달로 인해 구매 x
users[1] => emoticons[0] 7000*0.6 = 4200 구매, emoticons[1] 할인율 미달로 인해 구매 x
∆ 이모티콘 플러스 서비스 가입 0, 매출액 8400

(4) emoticons[0]에 30% 할인, emoticons[1]에 40% 할인
users[0] => emoticons[0] 할인율 미달로 구매 x, emoticons[1] 9000*0.6 = 5400
users[1] => emoticons[0] 7000*0.7 = 4900 구매, emoticons[1] 9000*0.6 = 5400 => 이모티콘 플러스 가입
∆ 이모티콘 플러스 서비스 가입 1, 매출액 5400
***/
