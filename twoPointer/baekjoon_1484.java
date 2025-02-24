import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int G;
    static List<Integer> results = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // 10^5 이하 자연수 (0 x)
        // G = 현재 몸무게^2 - 기억 몸무게^2
        G = sc.nextInt();

        // 탐색
        findNowWeights();

        // 한 줄에 하나씩 가능한 성원이의 현재 몸무게를 오름차순으로 출력
        // 가능한 몸무게가 없을 때는 -1을 출력
        if (results.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int n : results) {
                System.out.println(n);
            }
        }
    }

    // G = 현재 몸무게^2 - 기억 몸무게^2
    static void findNowWeights() {
        int nowWeight = 2; // G는 자연수이기때문에 2부터 스타트. 구해야하는 수.
        int rememberWeight = 1;

        while (rememberWeight < nowWeight) {
            int diff = nowWeight * nowWeight - rememberWeight * rememberWeight;

            if (diff == G) {
                results.add(nowWeight);
            }
            
            if (diff > G) {
                // 차이를 줄여야함.
                rememberWeight++;
            } else {
                // 차이를 늘려야함.
                nowWeight++;
            }
        }
    }
}

// G = 15
// nowWeight = 2, rememberWeight = 1
// diff = 4 - 1 = 3
// diff < G -> nowWeight++
