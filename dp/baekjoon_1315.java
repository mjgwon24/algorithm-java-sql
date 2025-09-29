import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;

    static class Game {
        int s, i, p;

        public Game(int s, int i, int p) {
            this.s = s;
            this.i = i;
            this.p = p;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 퀘스트 개수

        Game[] games = new Game[N];
        for (int t = 0; t < N; t++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            games[t] = new Game(s, i, p);
        }

        // possible[s][i]: 힘 s, 지능 i 상태에 도달 가능 여부
        boolean[][] possible = new boolean[1001][1001];
        // solved[s][i]: 힘 s, 지능 i 상태에서 성공 가능한 게임 수
        int[][] solved = new int[1001][1001];
        // leftPoint[s][i]: 힘 s, 지능 i 상태를 도달하고 남은 포인트
        int[][] leftPoint = new int[1001][1001];

        /** 모든 힘/지능 조합 계산 **/
        for (int s = 1; s <= 1000; s++) {
            for (int i = 1; i <= 1000; i++) {
                int pointSum = 0;     // 현재 상태에서 가능한 게임을 성공한 후 누적 포인트
                int solvedCnt = 0;    // 현재 상태에서 성공 가능한 게임 수

                // 모든 게임에 대해 성공 가능 여부 판단
                for (int t = 0; t < N; t++) {
                    if (games[t].s <= s || games[t].i <= i) {
                        pointSum += games[t].p;
                        solvedCnt++;
                    }
                }

                solved[s][i] = solvedCnt;
                // 위 동작을 수행하고 남은 포인트 = 획득한 포인트 - 능력치 증가에 사용한 포인트
                leftPoint[s][i] = pointSum - (s - 1) - (i - 1);

                // 초기 상태 - 가능
                if (s == 1 && i == 1) {
                    possible[s][i] = true;
                    continue;
                }

                // 이 로직이 불가능한 이유: 
                // 이전 단계부터 차근차근 성공해서 도달 가능 여부를 판단해야 하는데,
                // 아래 로직으로 판단하면 이전 단계에서는 도달 불가능한 경우까지 성공시켜버림
                // if (leftPoint[s][i] >= 0) {
                //     possible[s][i] = true;
                //     continue;
                // }

                if (s > 1 && possible[s - 1][i] && leftPoint[s - 1][i] > 0) {
                    possible[s][i] = true;
                    continue;
                }

                if (i > 1 && possible[s][i - 1] && leftPoint[s][i - 1] > 0) {
                    possible[s][i] = true;
                }
            }
        }

        int answer = 0;
        for (int s = 1; s <= 1000; s++) {
            for (int i = 1; i <= 1000; i++) {
                if (possible[s][i])
                    answer = Math.max(answer, solved[s][i]);
            }
        }
        
        System.out.println(answer);
    }
}
