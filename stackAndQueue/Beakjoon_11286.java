import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 우선순위 큐 선언 (절댓값 작은 순, 같을시 음수 먼저 배치)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if (absA != absB ) {
                return Integer.compare(absA, absB);
            }
            return Integer.compare(a,b);
        });

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i<n; i++) {
            int nowInt = sc.nextInt();

            if (nowInt != 0) {
                pq.offer(nowInt);
            } else {
                if (!pq.isEmpty()) {
                    System.out.println(pq.poll());
                } else {
                    System.out.println(0);
                }
            }
        }
    }
}
