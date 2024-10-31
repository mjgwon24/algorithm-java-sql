import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
 
public class Solution {
    static int t;
    static int[] eachArrayLength;
    static List<Integer>[] priceArray;
     
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
 
        // 2. each test case priceArray input
        eachArrayLength = new int[t];
        priceArray = new ArrayList[t];
         
        for (int i = 0; i < t; i++) {
            priceArray[i] = new ArrayList<>();
             
            st = new StringTokenizer(br.readLine());
            eachArrayLength[i] = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < eachArrayLength[i]; j++) {
                int n = Integer.parseInt(st.nextToken());
                priceArray[i].add(n);
            }
        }
 
        // 3. calculate result
        for (int i = 0; i < t; i++) {
            System.out.print("#" + (i + 1) + " ");
            System.out.println(calProfit(eachArrayLength[i], priceArray[i]));
        }
    }
 
    // calculate profit
    static long calProfit(int n, List<Integer> array) {
        long sum = 0;
        int max = array.get(n - 1);
 
        for (int i = (n - 2); i >= 0; i--) {
            if (array.get(i) < max) {
                sum += max - array.get(i);
            } else if (array.get(i) > max) {
                max = array.get(i);
            }
        }
 
        return sum;
    }
}
