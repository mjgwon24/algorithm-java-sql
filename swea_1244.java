import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
 
public class Solution {
    static int t = 0;
    static int switchNum = 0;
    static String lineString;
    static int lineLength = 0;
    static int[] numbers;
    static Map<Integer, List<Integer>> graphLists;
    static List<Integer> resultsIntegers;
     
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
         
        // 2. each case calculate
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            // 1. number info input
            lineString = st.nextToken();
            lineLength = lineString.length();
             
            numbers = new int[lineLength];
            for (int j = 0; j < lineLength; j++) {
                numbers[j] = Integer.parseInt(lineString.substring(j, j+1));
            }
             
            // 2. number of switch input
            switchNum = Integer.parseInt(st.nextToken());
         
            // 3. calculate output
            resultsIntegers = new ArrayList<>();
            graphLists = new HashMap<>();
            int transNum = transInt(numbers);
            dfs(transNum, 0);
             
            // 4. print output
            System.out.print("#" + (i+1) + " ");
            System.out.print(calMaxValue());
            System.out.println();
        }   
    }
     
    // calculate max number
    static void dfs(int start, int switchNumber) {
        if (switchNumber == switchNum) {
            return;
        }
         
        graphLists.putIfAbsent(start, new ArrayList<>());
         
        // switch case all add graph
        for (int i = 0; i < lineLength; i++) {
            for (int j = i+1; j < lineLength; j++) {
                int[] swapNum = new int[lineLength];
                int g = 0;
                for (int k = lineLength-1; k >=0; k--) {
                    swapNum[g] = start / (int) Math.pow(10, k) % 10;
                    g++;
                }
                 
                swap(swapNum, i, j);
                int swapInt = transInt(swapNum);
                 
                if (!graphLists.get(start).contains(swapInt)) {
                    if (switchNumber == switchNum-1) {
                        resultsIntegers.add(swapInt);
                    } else {
                        graphLists.get(start).add(swapInt);
                        dfs(swapInt, switchNumber+1);
                    }
                }
            }
        }
         
         
    }
     
    // calculate max value in resultsIntegers
    static int calMaxValue() {
        int max = 0;
         
        for (int n : resultsIntegers) {
            if (n > max) {
                max = n;
            }
        }
         
        return max;
    }
     
    // swap
    static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b]= temp; 
    }
     
    // translate array -> int
    static int transInt(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result * 10 + array[i];
        }
         
        return result;
    }
}
