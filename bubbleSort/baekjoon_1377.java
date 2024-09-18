import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// n <= 500,000 (입력값은 큰 숫자로, 이중 for문 사용시 시간 초과됨)
// sort() 함수 사용 - 시간복잡도 O(nlongn)
// 데이터의 정렬 전 index와 정렬 후 index를 비교해 왼쪽으로 가장 많이 이동한 값 찾기

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. n 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 2. 배열 A 입력받기
        mData[] A = new mData[n];
        for (int i = 0; i < n; i++) {
            A[i] = new mData(Integer.parseInt(br.readLine()), i);
        }

        // 3. 배열 정렬(O(nlogn) 시간 복잡도)
        Arrays.sort(A);

        // 4. 정렬 전 index와 정렬 후 index 계산해 최댓값 + 1 출력
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (max < A[i].index - i) { // 정렬 전 index - 정렬 후 index 계산의 최댓값 저장
                max = A[i].index - i;
            }
        }
        System.out.println(max + 1);
    }
}

/**
 * 별도 클래스 선언
 */
class mData implements Comparable<mData> {
    int value;
    int index;

    public mData(int value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}
