import java.util.*;

class Solution {
    static class Node {
        int val;
        Node next;
        Node prev;
        
        Node(int v) { 
            val = v; 
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        Node head = new Node(0);
        Node cur = head;
        Node current = null;
        
        for (int i = 1; i < n; i++) {
            Node temp = new Node(i);
            cur.next = temp;
            temp.prev = cur;
            cur = cur.next;

            if (i == k) current = temp;
        }

        Stack<Node> removed = new Stack<>();
        boolean[] deleted = new boolean[n];
        
        int idx = 1;
        for (String c : cmd) {
            if (c.startsWith("D")) {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) current = current.next;
            } else if (c.startsWith("U")) {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) current = current.prev;
            } else if (c.equals("C")) {
                // System.out.println("삭제: " + current.val);
                removed.push(current);
                deleted[current.val] = true;

                Node next = current.next;
                Node prev = current.prev;

                boolean flag = false;
                if (current.next != null) {
                    next.prev = current.prev;
                    flag = true;
                }
                if (current.prev != null) prev.next = current.next;

                if (flag) current = current.next;
                else current = current.prev;
            } else if (c.equals("Z")) {
                Node z = removed.pop();
                // System.out.println("복구: " + z.val);

                if (z.next != null) {
                    Node next = z.next;
                    next.prev = z;
                }
                
                if (z.prev != null) {
                    Node prev = z.prev;
                    prev.next = z;    
                }
                
                deleted[z.val] = false;
            }

            // System.out.printf("[%d] current: %d\n", idx++, current.val);
            // for (int i = 0; i < n; i++) if (!deleted[i]) System.out.print(i + " ");
            // System.out.println();
            // System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) sb.append("O");
            else sb.append("X");
        }
        
        return sb.toString();
    }
}
