import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열 크기 (1 <= * <= 10^6)
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 1 <= * <= 10^6
        }
        
        System.out.println(LIS(N, arr));
    }

    // 가장 긴 증가하는 부분 수열 길이 반환
    static int LIS(int N, int[] arr) {
        int[] lisList = new int[N];
        lisList[0] = arr[0];
        int lastIdx = 1;

        for (int i = 1; i < N; i++) {
            if (lisList[lastIdx - 1] < arr[i]) {
                // lisList의 마지막 값보다 현재 대상 숫자가 더 크다면 추가
                lisList[lastIdx] = arr[i];
                lastIdx++;
            } else {
                // lisList의 마지막 값보다 현재 대상 숫자가 더 작거나 같다면 자리 교체
                int idx = searchIdx(lisList, arr[i], lastIdx);
                lisList[idx] = arr[i];
            }
        }

        return lastIdx;
    }

    // 목표 숫자의 적절한 위치 반환
    // 이분 탐색 - O(NlogN)
    static int searchIdx(int[] arr, int target, int lastIdx) {
        int left = 0;
        int right = lastIdx;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) right = mid - 1; // arr을 줄여야함. right down
            else left = mid + 1; // arr을 키워야함. left up
        }

        // 값이 존재하지 않을 경우 해당 값이 들어갈 위치 반환
        return left;
    }

}
