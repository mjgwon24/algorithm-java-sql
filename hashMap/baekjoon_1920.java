import java.util.*;
import java.lang.*;
import java.io.*;

/**
* arr2의 수들이 arr1에 존재하는지 알아내기
**/

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        // 1. HashMap에 arr을 저장
        HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hm.put(arr[i], true);
        }

        int M = Integer.parseInt(br.readLine());
        int[] arr2 = new int[M];

        // M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
            System.out.println(hm.containsKey(arr2[i]) == true ? 1 : 0);
        }
    }
}
