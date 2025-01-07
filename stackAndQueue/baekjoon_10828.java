import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<Integer> pushNum = new ArrayList<>();
    static String[] commands;
    static List<Integer> stack = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        commands = new String[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken().toString();
            
            if (str.equals("push")) {
                stack.add(Integer.parseInt(st.nextToken()));
            } else if (str.equals("top")) {
                int top = stack.isEmpty() ? -1 : stack.get(stack.size() - 1);
                System.out.println(top);
            } else if (str.equals("size")) {
                System.out.println(stack.size());
            } else if (str.equals("empty")) {
                System.out.println(stack.isEmpty() ? 1 : 0);
            } else {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.remove(stack.size() - 1));
                }
            }
        }
    }
}
