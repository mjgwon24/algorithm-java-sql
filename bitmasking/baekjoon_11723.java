import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine()); // 연산 수 (3_000_000)
        int bitSet = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String[] cmd = br.readLine().split(" ");
            String str = cmd[0];
            int x = 0;
            if (cmd.length == 2) x = Integer.parseInt(cmd[1]);

            switch (str) {
                case "add":
                    // 없으면 추가
                    bitSet |= (1 << (x - 1));
                    break;
                case "remove":
                    // 있으면 제거
                    bitSet &= ~(1 << (x - 1));
                    break;
                case "check":
                    // 있으면 1, 없으면 0 출력
                    // 0이면 없음, 1이상이면 있음
                    sb.append((bitSet & (1 << (x - 1))) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    // 있으면 제거, 없으면 추가
                    bitSet ^= (1 << (x - 1));
                    break;
                case "all":
                    // {1, 2, ..., 20}으로 바꿈
                    bitSet = (1 << 20) - 1;
                    break;
                case "empty":
                    // 공집합으로 바꿈
                    bitSet = 0;
                    break;
            }
        }

        System.out.print(sb);
    }
}
