import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    static int t;
    static int[][] scores;
    
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        // 2. each test case number array input
        scores = new int[t][1000];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < 1000; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. print test case number, a lot of number result
        for (int i = 0; i < t; i++) {
            System.out.print("#" + (i + 1) + " ");
            System.out.println(lotNFnc(scores[i]));
        }
    }

    // a lot of number calculate function
    static int lotNFnc(int[] array) {
        int[] cnt = new int[101];
        int max = 0;
            
        for (int i = 0; i < 1000; i++) {
             cnt[array[i]]++;
        }   

        for (int i = 1; i < 101; i++) {
            if (cnt[i] > cnt[max]) max = i;
            if (cnt[i] == cnt[max]) max = i;
        }

        return max;
    }
}
