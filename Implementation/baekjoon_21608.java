import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static HashSet<Integer>[] likeInfo;
    static int[][] map;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likeInfo = new HashSet[N * N + 1];

        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            likeInfo[idx] = new HashSet<>();

            // 좋아하는 학생 4명
            for (int j = 0; j < 4; j++) {
                likeInfo[idx].add(Integer.parseInt(st.nextToken()));
            }

            /** 자리 찾기 **/
            // int[] x, y, likeCnt, emptyCnt
            PriorityQueue<int[]> spaces = new PriorityQueue<>((a, b) -> {
                if (a[2] != b[2]) return b[2] - a[2];
                if (a[3] != b[3]) return b[3] - a[3];
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            });
            
            // 1. 비어있는 칸 중 좋아하는 학생이 인접한 칸에 가장 많은 칸
            // 모든 비어있는 칸을 순회
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] != 0) {
                        // 비어있는 칸이 아니므로 패스
                        continue;
                    }
                    
                    // 인접한 칸에 좋아하는 학생 수, 비어있는 칸 확인
                    int likeCnt = 0;
                    int emptyCnt = 0;
                    for (int[] dr : directions) {
                        int nx = x + dr[0];
                        int ny = y + dr[1];
                        
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if (map[nx][ny] == 0) emptyCnt++;
                        if (likeInfo[idx].contains(map[nx][ny])) likeCnt++;
                    }

                    spaces.add(new int[]{x, y, likeCnt, emptyCnt});
                }
            }

            // 가장 첫번째로 나오는 자리에 적재
            int[] spaceInfo = spaces.poll();
            map[spaceInfo[0]][spaceInfo[1]] = idx;
        }


        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        /** 만족도 도출 **/
        int answer = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                // 인접한 칸에 좋아하는 학생 수
                int likeCnt = 0;

                for (int[] dr : directions) {
                    int nx = x + dr[0];
                    int ny = y + dr[1];
                    
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (likeInfo[map[x][y]].contains(map[nx][ny])) likeCnt++;
                }

                if (likeCnt > 0)
                    answer += Math.pow(10, likeCnt - 1);
            }
        }
        
        // 학생의 만족도 총 합 출력
        System.out.println(answer);
    }
}
