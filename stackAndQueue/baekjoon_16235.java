import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int N, M, K;
    static int[][] map, A;
    static Deque<Tree> trees = new ArrayDeque<>();
    static List<Tree> deadTrees = new ArrayList<>();
    static int[][] move = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    // static
    // 메모리 절약 - 클래스 로딩 시 한 번만 메모리에 할당되어 메모리 절약 가능
    // 공유 자원 - 모든 인스턴스가 동일한 static 멤버를 공유할 수 있음
    // 클레스 레벨 접근 - 클래스 이름을 통해 직접 접근 가능
    static class Tree {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 땅 크기
        M = Integer.parseInt(st.nextToken()); // 나무 개수
        K = Integer.parseInt(st.nextToken()); // 지나는 시간
        map = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        // 연도만큼 4계절 실행
        for (int year = 0; year < K; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        // K년이 지난 후 살아남은 나무의 수
        System.out.println(trees.size());
    }

    static void spring() {
        // 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
        Deque<Tree> newTrees = new ArrayDeque<>();
        deadTrees.clear();

        // 나이가 어린 나무부터 양분 먹음
        while (!trees.isEmpty()) {
            Tree tree = trees.pollFirst();

            // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉사
            if (map[tree.x][tree.y] < tree.age) {
                deadTrees.add(tree);
            } else {
                // 양분 섭취
                 map[tree.x][tree.y] -= tree.age;
                 tree.age++;
                 newTrees.add(tree);
            }
        }

         trees = newTrees;
    }

    static void summer() {
        // 봄에 죽은 나무가 양분으로 변함
        // 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
         for (Tree tree: deadTrees) {
              map[tree.x][tree.y] += tree.age / 2;
         }
    }

    static void fall() {
         Deque<Tree> newTrees = new ArrayDeque<>(trees);

        // 번식하는 나무는 나이가 5의 배수
        // 인접한 8개의 칸에 나이가 1인 나무 생성
        for (Tree tree: trees) {
            if (tree.age % 5 == 0) {
                int x = tree.x;
                int y = tree.y;

                for (int[] mv: move) {
                    int nx = x + mv[0];
                    int ny = y + mv[1];

                    if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;

                    // 나무 생성
                     newTrees.addFirst(new Tree(nx, ny, 1));
                }
            }
        }

        trees = newTrees;
    }

    static void winter() {
        // 땅에 양분을 추가
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }
}
