import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception {
        new Main().solution();
    }
    
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        // str: 명령어, n: 배열 길이, arrStr: 배열
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            int n = Integer.parseInt(br.readLine());
            
            String arrStr = br.readLine().replace("[", "").replace("]", "").trim();
            StringTokenizer st = new StringTokenizer(arrStr, ",");
            Deque<Integer> dq = new ArrayDeque<>();
            
            for (int j = 0; j < n; j++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }
            
            boolean isRight = false;
            boolean err = false;
            
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'R') {
                    isRight = !isRight;
                } else {
                    if (dq.isEmpty()) {
                        err = true;
                        break;
                    } 
                    
                    if (isRight) dq.pollLast();
                    else dq.pollFirst();
                }
            }
            
            if (err) sb.append("error");
            else {
                sb.append("[");
                
                while (!dq.isEmpty()) {
                    if (isRight) sb.append(dq.pollLast());
                    else sb.append(dq.pollFirst());
                    
                    if (dq.size() == 0) break;
                    else sb.append(",");
                }
                
                sb.append("]");
            }
            
            
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}

// if isRight is true, D -> right first
// if isRight is false, D -> left first
// empty array meet command D -> print "error"
// empty array meet command R -> print "[]"
