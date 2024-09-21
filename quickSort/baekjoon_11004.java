import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 입력 배열 수 n, 수를 구하는 위치 k 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        // 2. 배열 입력받기
        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 3. 퀵 정렬
        quickSort(a, 0, n - 1, k - 1);

        // 4. k번째 수 출력
        System.out.print(a[k-1]);
    }

    /**
     * 퀵 정렬 함수
     */
    public static void quickSort(int[] arr, int left, int right, int k) {
        // 배열 크기가 2 이상일 때만 분할
        if (left < right) {
            int pivotIndex = partition(arr, left, right);

            if (pivotIndex == k) return;
            else if (pivotIndex > k) quickSort(arr, left, pivotIndex - 1, k);
            else quickSort(arr, pivotIndex + 1, right, k);
        }
    }

    /**
     * 배열 분할 & 피벗 위치 반환
     */
    public static int partition(int[] arr, int left, int right) {
        if (left + 1 == right) {
            if (arr[left] > arr[right]) swap(arr, left, right);
            return right;
        }

        int middle = (left + right) / 2;
        swap(arr, left, middle);
        int pivot = arr[left];
        int i = left + 1, j = right;

        while (i <= j) {
            while (j >= left + 1 && pivot < arr[j]) j--;
            while (i <= right && pivot > arr[i]) i++;
            if (i <= j) {
                swap(arr, i++, j--);
            }
        }

        // 피벗 데이터를 나눠진 두 그룹의 경계 index에 저장
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }

    /**
     * 배열 요소 교환 함수
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
