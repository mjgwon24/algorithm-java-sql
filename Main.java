import twoPointer.Baekjoon_1940;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


class Main {
    public static void main(String[] args) throws IOException {
        /**
         * 시작 시간 측정
         */
        long startTime = System.currentTimeMillis();

        /**
         * 실행할 코드
         */
        Baekjoon_1940 baekjoon = new Baekjoon_1940();
        baekjoon.calculateArmorCount();

        /**
         * 종료 시간 측정
         */
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("프로그램 실행 시간: " + duration + " 밀리초");
    }
}

