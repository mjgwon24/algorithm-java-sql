import java.lang.*;
import java.util.*;

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;

            if (nums[m] == target) return m;
            else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }

        return -1;
    }
}

/***
정수 배열 nums가 오름차순으로 정렬되어 있고, 정수 target이 주어졌을 때, 
nums에서 target을 검색하는 함수를 작성하세요. target이 존재하면 인덱스를 반환하고, 그렇지 않으면 -1을 반환하세요.
알고리즘의 런타임 복잡도가 O(log n)이어야 합니다.
 */
