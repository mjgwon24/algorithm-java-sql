package twoPointer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 슈도코드
 * 1. 재료의 개수 N, 두 재료 고유번호의 합 M 입력받기
 * 2. 재료 N개 각각의 고유번호 배열 A 입력받기
 * 3. 배열 A 오름차순 정렬
 * 4. 변수 초기화 - 두 고유번호의 합 변수 sum = 0, 시작 인덱스 i = 0, 마지막 인덱스 j = N-1, 만들 수 있는 갑옷 개수 cnt = 0
 * 5. while i < j, 배열 A를 순회하며 갑옷 개수 계산
 * 5.0 sum = A[i] + A[j];
 * 5.1 sum > M 일 경우, j--;
 * 5.2 sum < M 일 경우, i++;
 * 5.3 sum == M 일 경우, i++; j--; cnt++;
 */

public class Baekjoon_1940 {
    public void calculateArmorCount() {
        // 1. 재료의 개수 N, 두 재료 고유번호의 합 M 입력받기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 2. 재료 N개 각각의 고유번호 배열 A 입력받기
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);

        // 3. 변수 초기화 - 두 고유번호의 합 변수 sum = 0, 시작 인덱스 i = 0, 마지막 인덱스 j = N-1, 만들 수 있는 갑옷 개수 cnt = 0
        int sum = 0;
        int i = 0;
        int j = N - 1;
        int cnt = 0;

        // 4. while i < j, 배열 A를 순회하며 갑옷 개수 계산
        while (i < j) {
            sum = A[i] + A[j];
            if (sum > M) {
                j--;
            } else if (sum < M) {
                i++;
            } else {
                i++;
                j--;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
