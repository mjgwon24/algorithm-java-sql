import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 배열 개수 n 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 2. 숫자 배열 입력받기
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        
        // 3. 병합정렬로 배열 정렬후 출력
        mergeSort(array, 0, n-1);

        for (int i = 0; i < n; i++) {
            System.out.println(array[i]);
        }
    }

    // 병합정렬 함수
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }
    }
    
    // 병합 함수
    public static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // 두 부분 배열 임시로 생성
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // 데이터를 임시 배열에 복사
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];    
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[middle + j + 1];
        }

        // 임시 배열들을 병합하여 원본 배열에 저장
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // 남아 있는 요소들을 원본 배열에 복사
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
        
    }
}
