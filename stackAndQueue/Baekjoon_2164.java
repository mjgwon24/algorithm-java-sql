import java.io.IOException;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Integer> myque = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            myque.add(i);
        }

        while (myque.size() != 1) {
            myque.poll();
            if (myque.size() != 1) {
                myque.add(myque.poll());
            }
        }

        System.out.println(myque.poll());     
    }
}
