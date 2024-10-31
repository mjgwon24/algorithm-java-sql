import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int t;
    static int[][] testCase;
     
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
 
        // 2. each test case array input
        testCase = new int[t][10];
         
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                testCase[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        // 3. calculate sum
        for (int i = 0; i < t; i++) {
            System.out.print("#" + (i + 1) + " ");
            System.out.println(oddSumFcn(testCase[i]));
        }
         
    }
 
    // odd number function
    static boolean isOdd(int number) {
        if (number % 2 == 0) {
            return false;
        }
 
        return true;
    }
 
    // sum function
    static int oddSumFcn(int[] array) {
        int sum = 0;
 
        for (int i : array) {
            if (isOdd(i)) sum += i;
        }
 
        return sum;
    }
}
