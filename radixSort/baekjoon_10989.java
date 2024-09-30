import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 수의 개수 n 입력받기 (1 <= n <= 10,000,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 2. 숫자 배열 입력받기
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // 3. 기수 정렬 (오름차순 정렬)
        radixSort(array);

        // 4. 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < array.length; i++) {
            bw.write(array[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 기수 정렬
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        for (int exp = 1; (max / exp) > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    // 기수 정렬 함수
    public static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // 나머지 배열 생성
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // 누적 합
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 정렬 (역순)
        for (int i = n-1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // 원본 배열에 반영
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // 최댓값 반환 함수
    public static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        return max;
    }
}
