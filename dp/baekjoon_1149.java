import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] rgb;
    static int[][] cost;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        rgb = new int[N][3];
        cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = sc.nextInt();
            }
        }

        cost[0][0] = rgb[0][0];
        cost[0][1] = rgb[0][1];
        cost[0][2] = rgb[0][2];

        for (int i = 1; i < N; i++) {
            cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + rgb[i][0];
            cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + rgb[i][1];
            cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + rgb[i][2];
        }

        int min = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
        System.out.println(min);        
    }
}

// cost[0][0] = rgb[0][0]
// cost[0][1] = rgb[0][1]
// cost[0][2] = rgb[0][2]

// cost[1][0] = min(cost[0][1], cost[0][2]) + rgb[1][0]
