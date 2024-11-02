import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int t;
    static int n, m;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException {
        // 1. number of test case t input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        // 2. each test case n, m input
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                
                for (int k = 0; k < n; k++) {
                    graph[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // 3. print output a lot of kill number
            System.out.print("#" + (i + 1) + " ");
            System.out.println(mostKillNum(graph, n, m));
        }
    }

    // a lot of kill number calculate function
    static int mostKillNum(int[][] array, int n, int killSize) {
        int size = (n-killSize + 1)*(n-killSize + 1);
        int[] killCnt = new int[size];
        int cnt = 0;

        for (int i = 0; i < (n - killSize + 1); i++) {
            for (int j = 0; j < (n - killSize + 1); j++) {
                for (int a = 0; a < killSize; a++) {
                    for (int b = 0; b < killSize; b++) {
                        killCnt[cnt] += array[i + a][j + b];
                    }
                }
                cnt++;
            }
        }

        int max = killCnt[0];

        for (int i = 1; i < size; i++) {
            if (killCnt[i] > max) max = killCnt[i];
        }

        return max;
    }
}
