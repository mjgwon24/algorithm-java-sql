import java.lang.*;
import java.util.*;

class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] arr = new int[n + 1];

        // t[0] 믿음을 주는 사람, t[1] 믿음을 받는 사람
        for (int[] t: trust) {
            arr[t[0]]--;
            arr[t[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i] == n - 1) return i;
        }

        return -1;
    }
}

/***
1. 판사는 누구도 신뢰하지 않는다
2. 판사는 모두의 신뢰를 받는다

신뢰도 배열
- 믿음을 줄 경우 --
- 믿을을 받을 경우 ++

판사는 아무도 믿지 않고, 모두에게 믿음을 받으므로 판사의 신뢰도는 n - 1
 */
