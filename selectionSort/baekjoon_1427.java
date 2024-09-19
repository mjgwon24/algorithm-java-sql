import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 내림차순 정렬

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 정렬할 수 입력받기 (N <= 1,000,000,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        // 2. 입력받은 수를 바탕으로 배열 생성
        String[] numbers = line.split("");
        int n = line.length();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(numbers[i]);    
        }

        // 3. 내림차순 정렬 - 선택 정렬
        // 나머지 배열에서 최댓값을 가져와서 현재 자릿수와 swap
        int temp = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = i;
            
            for (int j = i; j < n; j++) {
                if (A[max] < A[j]) {
                    max = j;
                }
            }
            
            if (max != i) {
                temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(A[i]);
        }
        
    }
}
