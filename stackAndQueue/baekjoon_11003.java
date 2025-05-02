import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = solution(N, L, A);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < result.length; i++) {
            bw.write(String.valueOf(result[i]) + " ");
        }
        bw.flush();
        bw.close();
    }

    public static int[] solution(int N, int L, int[] A) {
        int[] result = new int[N];
        Deque<Integer> indexdq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // dq가 비어있지 않을 경우, 윈도우 범위 벗어나면 제거
            if (!indexdq.isEmpty() && i - L >= indexdq.peekFirst()) {
                indexdq.removeFirst();
            }

            // dq가 비어있지 않을 경우, 들어올 값보다 크거나 같은 값 인덱스 있으면 전부 제거
            while (!indexdq.isEmpty() && A[indexdq.peekLast()] >= A[i]) {
                indexdq.removeLast();
            }

            indexdq.addLast(i);

            result[i] = A[indexdq.peekFirst()];
        }
        return result;
    }
}
