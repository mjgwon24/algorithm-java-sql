import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 빌더
        
        for (int i = 0; i < T; i++) {
            String commandStr = br.readLine(); // 수행할 함수 문자열
            int n = Integer.parseInt(br.readLine()); // 배열 개수
            String arrStr = br.readLine(); // 배열 문자열
            
            // 1. 배열문자열을 배열로 변환
            arrStr = arrStr.replace("[", "");
            arrStr = arrStr.replace("]", "");
            StringTokenizer st = new StringTokenizer(arrStr, ",");
            
            Deque<Integer> dq = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                dq.offer(Integer.parseInt(st.nextToken().trim()));
            }
            
            // 2. 각 연산에 따른 동작 수행
            boolean isReverse = false;
            boolean err = false;
            
            for (int j = 0; j < commandStr.length(); j++) {
                if (commandStr.charAt(j) == 'R') {
                    isReverse = !isReverse;
                } else {
                    if (dq.isEmpty()) {
                        err = true;
                        break;
                    }
                    
                    if (isReverse) {
                        // 뒤에서 제거
                        dq.removeLast();
                    } else {
                        // 앞에서 제거
                        dq.removeFirst();
                    }
                }
            }
            
            if (err) {
                sb.append("error\n");
            } else {
                sb.append("[");
                
                if (isReverse) {
                    while (!dq.isEmpty()) {
                        sb.append(dq.pollLast());
                        
                        if (dq.isEmpty()) break;
                        sb.append(",");
                    }
                } else {
                    while (!dq.isEmpty()) {
                        sb.append(dq.pollFirst());
                        
                        if (dq.isEmpty()) break;
                        sb.append(",");
                    }
                }
                
                sb.append("]\n");
            }
            
        }
        
        // 결과 문자열 출력
        System.out.print(sb);
    }
}

// 1. 배열문자열을 배열로 변환
// 2. 각 연산에 따른 동작 수행
// 배열 길이가 0일 경우에 D를 수행하면 error 출력

// Deque 앞뒤로 삽입 및 제거 가능 자료형

// R(뒤집기)
// isReverse = false / true;

// D(맨 앞 숫자 제거)
// isReverse == false, dq.removeFirst
// isReverse == true, dq.removeLast
