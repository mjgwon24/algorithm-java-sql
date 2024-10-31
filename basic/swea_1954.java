import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int t;
    static int[] nArray;
    
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        // 2. each test case number input
        nArray = new int[t];
        for (int i = 0; i < t; i++) {
            nArray[i] = Integer.parseInt(br.readLine());
        }

        // 3. each case result output print
        for (int i = 0; i < t; i++) {
            System.out.println("#" + (i + 1));
            snailFnc(nArray[i]);
        }
    }

    // create snail table function
    static void snailFnc(int n) {
        int[][] snailTable = new int[n][n];
        int maxN = n*n;
        int writeNum = 1;
        int x = 0;

        while (writeNum <= maxN) {
            // 1. right Horizontal orientation
            // x fix
            for (int i = x; i < (n - x); i++) {
                snailTable[x][i] = writeNum;
                writeNum++;
            }
            x++;

            if (writeNum > maxN) {
                break;
            }

            // 2. down portrait orientation
            // y fix
            for (int i = x; i < n - x + 1; i++) {
                snailTable[i][n - x] = writeNum;
                writeNum++;
            }

            if (writeNum > maxN) {
                break;
            }

            // 3. left horizontal orientation
            // x fix
            for (int i = (n - 1) - x; i  >= x - 1; i--) {
                snailTable[n - x][i] = writeNum;
                writeNum++;
            }

            if (writeNum > maxN) {
                break;
            }

            // 4. up portrait orientation
            // y fix
            for (int i = (n - 1) - x; i >= x; i-- ) {
                snailTable[i][x - 1] = writeNum;
                writeNum++;
            }

            if (writeNum > maxN) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(snailTable[i][j] + " ");
            }
            System.out.println();
        }
    }
}
