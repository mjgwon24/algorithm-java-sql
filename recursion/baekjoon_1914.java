import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Main {
    static int N;
    static StringBuilder sb = new StringBuilder(); // 옮긴 내역 저장
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        BigInteger bigTwo = new BigInteger("2");
        BigInteger bigOne = new BigInteger("1");
        BigInteger moveCount = bigTwo.pow(N).subtract(bigOne);
        
        if (N <= 20) {
            hanoi(N, 1, 3, 2);
            System.out.println(moveCount);
            System.out.println(sb.toString());
        } else {
            System.out.println(moveCount);
        }
    }

    // n: 움직여야 하는 원판의 개수, start: 시작점, target: 목표지점, middle: 중간지점
    static void hanoi(int n, int start, int target, int middle) {
        // 0. 이동해야하는 원판이 1개일 경우, 바로 이동시키기
        if (n == 1) {
            sb.append(start).append(" ").append(target).append("\n");
            return;
        }

        // 1. N - 1개의 원판을 보조장대로 이동 
        // (목표장대는 N번째 원판이 가야하므로 보조장대로 이동시킴)
        hanoi(n - 1, start, middle, target);

        // 2. N번째 원판을 목표장대로 이동
        sb.append(start).append(" ").append(target).append("\n");
        
        // 3. N - 1개의 원판을 목표장대로 이동 
        hanoi(n - 1, middle, target, start);
    }
}

// 가장 큰 원판이 목표 지점 가장 아래로 가야한다. -> N - 1개의 원판들은 보조 장대에 있어야한다
// 0. 이동해야하는 원판이 1개일 경우, 바로 이동시키기
// 1. N - 1개의 원판을 보조장대로 이동 (목표장대는 N번째 원판이 가야하므로 보조장대로 이동시킴)
// 2. N번째 원판을 목표장대로 이동
// 3. N - 1개의 원판을 목표장대로 이동 
