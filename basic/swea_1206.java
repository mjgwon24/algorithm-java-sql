import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    static int[] buildingNum;
    static int[] buildingLength;
    
    public static void main(String[] args) throws IOException {
        // 1. number of building n input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        buildingNum = new int[10];
        // buildingLength = new ArrayList[10];
        
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            buildingNum[i] = n;

            // buildingLength[i] = new ArrayList<>();
            buildingLength = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int m = Integer.parseInt(st.nextToken());
                // buildingLength[i].add(m);
                buildingLength[j] = m;
            }

            System.out.print("#" + (i+1) + " ");
            System.out.println(buildingFnc(buildingLength, buildingNum[i]));
        }
    }

    // calculate beautiful building
    static int buildingFnc(int[] array, int n) {
        // System.out.println("===n: " + n);
        int sum = 0;
        int maxLeft;
        int maxRight;
        int max;

        for (int i = 2; i < (n-2); i++) {
            // System.out.println("=== " + i + " ===");
            
            // find left max
            if (array[i-2] < array[i-1]) {
                maxLeft = array[i-1];
            } else {
                maxLeft = array[i-2];
            }

            // find right max
            if (array[i+2] < array[i+1]) {
                maxRight = array[i+1];
            } else {
                maxRight = array[i+2];
            }

            // find max
            if (maxRight < maxLeft) {
                max = maxLeft;
            } else {
                max = maxRight;
            }

            // 비교
            if (array[i] > max) {
                sum += array[i] - max;
            }

            // System.out.println("maxLeft: " + maxLeft + ", maxRight: " + maxRight + ", max: " + max + ", sum: " + sum);
        }

        return sum;
    }
}
