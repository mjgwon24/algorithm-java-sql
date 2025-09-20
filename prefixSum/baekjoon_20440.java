import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N; // 모기 마릿수
    static int[][] T; // 모기 입장 시각, 최장 시각
    static int answerCnt, startT, endT;
    static Map<Integer, Integer> info = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            T[i][0] = s;
            T[i][1] = e;
            
            info.put(s, info.getOrDefault(s, 0) + 1);
            info.put(e, info.getOrDefault(e, 0) - 1);
        }

        // key순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (Map.Entry<Integer, Integer> e : info.entrySet()) {
            pq.add(new int[]{e.getKey(), e.getValue()});
        }

        // 답 갱신
        int cnt = 0;
        boolean flag = false;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 현재 cnt가 answerCnt에서 "최초로" 떨어진다면, 끝 시간 갱신
            if (flag && cnt == answerCnt) {
                if (cur[1] < 0) {
                    endT = cur[0];
                    flag = false;
                }
            }

            // 모기 마릿수
            cnt += cur[1];
            
            // answerCnt가 갱신된다면, 시작 시간 갱신
            if (answerCnt < cnt) {
                startT = cur[0];
                answerCnt = cnt;
                flag = true;
                continue;
            }
        }
        
        // 모기가 가장 많이 있는 시간대의 모기 마릿수 출력
        System.out.println(answerCnt);

        // 모기가 가장 많이 있는 시간대의 연속 구간 전체
        // 가장 빠른 시작 시간 기준 출력
        System.out.println(startT + " " + endT);
    }
}
