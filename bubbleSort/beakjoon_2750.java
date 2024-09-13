import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        // 개수 N 받아오기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // Buffer로 입력 배열 받아오기
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        // 버블 정렬 - 2개씩 비교하며 오름차순 배열 만들기
        int temp = 0;
        for (int i = 0; i < (n-1); i++) {
            for (int j = 0; j < (n-1-i); j++) {
                if (A[j] > A[j+1]) {
                    temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }

        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(A[i]));
            bw.newLine();
        }
        bw.flush();
    }
}
