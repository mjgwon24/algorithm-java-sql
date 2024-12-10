import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            boolean exist = false;
            int start = 0;
            int end = arr.length - 1;
            
            while (start <= end) {
                int midI = (start + end) / 2;
                int midV = arr[midI];

                if (target < midV) {
                    end = midI - 1;
                } else if (target > midV) {
                    start = midI + 1;
                } else {
                    exist = true;
                    break;
                }
            }

            if (exist) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}

// arr = {1, 2, 3, 4, 5}, start = 0, end = 4
// [ double navigation 1 ]
// midI = (0 + 4) / 2 = 2
// midV = arr[2] = 3
// 3 > 1(target)
// end = midI - 1

