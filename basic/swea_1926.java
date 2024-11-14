import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int n = 0;
	
	public static void main(String[] args) throws IOException {
		// 1. input n
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		// 2. print result
		game();
	}
	
	// start game
	static void game() {
		for (int i = 1; i <= n; i++) {
			int length = calLength(i);
			int[] array = new int[length];
			Boolean printYn = false;
			int cnt = 0;
			
			array = parseArray(i, length);

			for (int n : array) {
				if (n % 3 == 0 && n != 0) {
					printYn = true;
					cnt++;
				}
			}

			if (printYn) {
				for (int j = 0; j < cnt; j++) {
					System.out.print("-");					
				}
			} else {
				System.out.print(i);
			}
			
			System.out.print(" ");
		}
	}
	
	// cal length
	static int calLength(int num) {
		int n = num;
		int cnt = 0;
		
		while (n > 0) {
			n = n / 10;
			cnt++;
		}
		
		return cnt;
	}
	
	// int -> int[]
	static int[] parseArray(int num, int length) {
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i]= num / (int) Math.pow(10, length - i - 1) % 10; 
		}
		
		return array;
	}
	
	
	
}
