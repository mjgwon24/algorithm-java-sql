import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 블록의 가로의 길이와 세로의 길이
        int TD = Integer.parseInt(st.nextToken());
        int RL = Integer.parseInt(st.nextToken());

        // 상점 개수
        int N = Integer.parseInt(br.readLine());
        int[][] s = new int[N][2];
        int result = 0;

        // 첫째 수는 상점이 위치한 방향
        // 1은 블록의 북쪽, 2는 블록의 남쪽, 3은 블록의 서쪽, 4는 블록의 동쪽에 상점이 있음
        // 둘째 수는 상점이 블록의 북쪽 또는 남쪽에 위치한 경우 블록의 왼쪽 경계로부터의 거리
        // 상점이 블록의 동쪽 또는 서쪽에 위치한 경우 블록의 위쪽 경계로부터의 거리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s[i][0] = Integer.parseInt(st.nextToken()); // 방향
            s[i][1] = Integer.parseInt(st.nextToken()); // 거리
        }

        // 동근이의 위치가 상점의 위치
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 내 위치 방향
        int y = Integer.parseInt(st.nextToken()); // 내 위치 거리

        int dist1 = move(x, y, TD, RL);

        for (int i = 0; i < N; i++) {
            int dist2 = move(s[i][0], s[i][1], TD, RL);
            int distance1 = Math.abs(dist1 - dist2);
            int distance2 = 2 * (TD + RL) - distance1;

            result += Math.min(distance1, distance2);
        }

        System.out.println(result);
    }

    public static int move(int dir, int dist, int TD, int RL) {
        if (dir == 1) {
            return dist;
        } else if (dir == 2) {
            return TD + RL + (TD - dist);
        } else if (dir == 3) {
            return 2 * TD + RL + (RL - dist);
        } else {
            return dist + TD;
        }
    }
}
