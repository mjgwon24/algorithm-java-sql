import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
    }

    // 가능한 시간중 최소시간 도출
    public static long solution(int n, int[] times) {
        long left = 1;
        long right = (long) n * Arrays.stream(times).max().getAsInt();
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;

            // mid 시간 동안 각 심사관이 심사할 수 있는 사람 수 총 합
            for (int t: times) {
                people += mid / t;
            }

            if (people >= n) {
                // n명 이상 심사 가능 -> 더 적은 시간으로 탐색
                answer = mid;
                right = mid - 1;
            } else {
                // n명 미만 심사 가능 -> 더 많은 시간으로 탐색
                left = mid + 1;
            }
        }

        return answer;
    }
}

