import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] array, tmpArray;
    public static long result;
    
    public static void main(String[] args) throws IOException{
        // 1. 수의 개수 n 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 2. 숫자 배열 입력받기
        array = new int[n];
        tmpArray = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        // 3. 병합 정렬 수행 후 swap 횟수 결과 출력
        result = 0;
        mergeSort(0, n-1);
        System.out.println(result);
    }

    // 병합 정렬 함수
    public static void mergeSort(int left, int right) {
        if (right - left < 1) return;

        int middle = (left + right) / 2;

        mergeSort(left, middle);
        mergeSort(middle + 1, right);

        // 임시 배열에 원본 배열의 구간만큼 복사
        for (int i = left; i <= right; i++) {
            tmpArray[i] = array[i];
        }

        int k = left;
        int index1 = left;
        int index2 = middle + 1;

        // 양쪽 배열 병합
        while (index1 <= middle && index2 <= right) {
            if (tmpArray[index1] > tmpArray[index2]) {
                array[k] = tmpArray[index2];
                result += index2 - k;
                index2++;
                k++;
            } else {
                array[k] = tmpArray[index1];
                index1++;
                k++;
            }
        }

        // 병합 후 나머지 배열 병합
        while (index1 <= middle) {
            array[k] = tmpArray[index1];
            index1++;
            k++;
        }
        while (index2 <= right) {
            array[k] = tmpArray[index2];
            index2++;
            k++;
        }
    }
}
