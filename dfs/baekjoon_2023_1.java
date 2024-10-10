import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // 정답 배열 전역변수로 선언
    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    
    public static void main(String[] args) {
        // 1. 자릿수 n 입력받기 (1 <= n <= 8)
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 2. n이 1일 경우 소수 초기화
        List<Integer> initialArray = new ArrayList<>();
        initialArray.add(2);
        initialArray.add(3);
        initialArray.add(5);
        initialArray.add(7);

        graph.put(1, initialArray);

        // 3. n의 값에 따라 소수 계산
        if (n > 1) {
            for (int i = 1; i < n; i++) {
                findPrimeNum(i);
            }   
        }

        // 4. 답 출력
        List<Integer> result = graph.get(n);
        for (int i : result) {
            System.out.println(i);
        }
        
    }

    // 주어진 수 + 뒷자리 한자리 추가해서 소수 탐색 함수
    public static void findPrimeNum(int node) {
        // node가 넘어오면 node+1의 자리 소수를 탐색
        List<Integer> newResult = new ArrayList<>();
        
        List<Integer> neighbors = graph.get(node);
        for (int neighbor : neighbors) {
            for (int i = 1; i<= 9; i++) {
                int n = neighbor*10 + i;

                // n이 소수인지 탐색
                if (isPrimeNum(n)) {
                    newResult.add(n);
                }
            }
        }

        graph.put(node+1, newResult);
    }

    // 소수 탐색 함수
    public static boolean isPrimeNum(int num) {
        // 본인과 1을 제외한 어떠한 수로 나눠도 나누어떨어지지 않아야한다
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
