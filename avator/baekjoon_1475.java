import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N; // 방번호
    static int[] usedNum = new int[10];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        sc.close();

        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(i) - '0';
            if (n == 6 || n == 9) {
                if (usedNum[6] < usedNum[9]) {
                    usedNum[6]++;
                } else {
                    usedNum[9]++;
                }
            } else {
                usedNum[n]++;
            }
        }

        int max = usedNum[0];
        for (int n : usedNum) {
            if (max < n) max = n;
        }

        System.out.println(max);
    }
}

// N(방번호) = 9999
// [1] 6, 9, 
// [2] 6, 9
// usedNum[9] = 4 / 2 = 2

// N = 6669
// usedNum[6] = 3
// usedNum[9] = 1

// N(방번호) = 122
// [1] 1,2
// [2] 2
// usedNum[1] = 1
// usedNum[2] = 2

// N = 12635
// [1] 1,2,3,5,6 (중복숫자 0개)

// 숫자가 겹치지 않으면 1개의 세트로 가능
// 1. 방 번호를 하나하나 stack에 넣기
// 2. 중복 숫자 계산 (6,9인지 확인) 
