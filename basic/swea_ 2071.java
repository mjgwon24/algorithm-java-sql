import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int t;
    static int[][] a;
     
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
 
        // 2. each test case array input
        a = new int[t][10];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        // 3. output print
        for (int i = 0; i < t; i++) {
            System.out.print("#" + (i + 1) + " ");    
            System.out.println(calAverage(a[i]));
        }
    }
 
    // calculate average number
    static int calAverage(int[] array) {
        int sum = 0;
 
        for (int i = 0; i < 10; i++) {
            sum += array[i];
        }
 
        if (sum % 10 >= 5) {
            return sum / 10 + 1;
        } else {
            return sum / 10;
        }
    }
}
