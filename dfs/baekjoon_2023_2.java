import java.util.Scanner;

public class Main {
    public static int n;

    public static void main(String[] args) {
        // 1. 자릿수 n 입력받기
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // 2. 깊이 우선 탐색 dfs 함수 실행
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }

    /**
     * 깊이 우선 탐색 dfs 함수
     */
    public static void dfs(int num, int jarisu) {
        if (jarisu == n) {
            if (isPrime(num)) {
                System.out.println(num);
            }
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            if (isPrime(num * 10 + i)) {
                dfs(num * 10 + i, jarisu + 1);
            }
        }
    }

    /**
     * 소수 찾기 함수
     */
    public static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
