import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int n, m, x, y;
    static int depth = 0;
    static int[][] mazeGraph;
    static boolean[][] visited;
    static int[][] calInt = new int[4][2];
    static int cnt = 1;
    static int cnt2 = 0;
    
    public static void main(String[] args) throws IOException {
        // 1. vertical length n, horizontal length m input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 2. mazeGraph initialize
        mazeGraph = new int[n][m];

        // 3. maze graph input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            
            for (int j = 0; j < m; j++) {
                mazeGraph[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        // 4. find the minimum distince - bfs
        visited = new boolean[n][m];
        calInt[0] = new int[] {0, -1};
        calInt[1] = new int[] {0, 1};
        calInt[2] = new int[] {-1, 0};
        calInt[3] = new int[] {1, 0};
            
        bfs(0, 0);
        System.out.print(depth);
    }

    // bfs
    static void bfs(int i, int j) {
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.offer(new int[] {i, j});
        visited[i][j] = true;       

        while(!bfsQueue.isEmpty()) {
            depth++;
            cnt += cnt2;
            cnt2 = 0;
            
            while(cnt != 0) {
            int[] outNode = bfsQueue.poll();
            cnt--;
            
            for (int k = 0; k < 4; k++) {
                x = outNode[0] + calInt[k][0];
                y = outNode[1] + calInt[k][1];

                if (x == (n - 1) && y == (m - 1)) break;
                
                if (x >= 0 && y >= 0 && x < n && y < m) {
                     if(mazeGraph[x][y] == 1 && !visited[x][y]) {
                        bfsQueue.offer(new int[] {x, y});
                        visited[x][y] = true;
                        cnt2++;
                    }   
                }
            }

            if (x == (n - 1) && y == (m - 1)) {
                    break;
            }
            }
            
if (x == (n - 1) && y == (m - 1)) {
                    depth++;
                    break;
            }            
        }
    }
}
