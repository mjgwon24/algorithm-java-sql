import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int t = 0;
	static int n = 0; // length
	static int m = 0; // width
	static int[][] array;
	static int[] codeArray;
	
	public static void main(String[] args) throws IOException {
		// 1. number of test case t input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 2. input n, m
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			array = new int[n][m];
			
			// 3. input each array
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				String line = st.nextToken();
				
				for (int k = 0; k < m; k++) {
					array[j][k] = Integer.parseInt(line.substring(k, k+1));
				}
			}
			
			// 4. calculate result
			codeIsReal();
			
			// print output
			System.out.print("#" + (i+1) + " ");
			System.out.print(calValue());
			System.out.println();
		}
	}
	
	// calculate value
	static int calValue() {
		int n = (codeArray[0] + codeArray[2] + codeArray[4] + codeArray[6]) * 3 + (codeArray[1] + codeArray[3] + codeArray[5] + codeArray[7]);
		
		if (n % 10 == 0) {
			int sum = 0;
			for (int num : codeArray) {
				sum += num;
			}
			
			return sum;
		} else {
			return 0;
		}
	}
	
	
	// code true of false
			static void codeIsReal() {
				boolean calN = false;
				boolean two = true;
				boolean three = false;
				boolean four = false;
				int cnt2 = 0;
				int cnt3 = 0;
				int cnt4 = 0;
				codeArray = new int[8];
				int cnt = 0;
				
				// each number calculate
				for (int i = 0; i < n; i++) {
					for (int n : array[i]) {
						
						if (cnt == 8) {
							break;
						}

						if(n == 1)  {
							if(four) {
								cnt4++;
								three = false;
								calN = true;
							}
							
							if (two) {
								cnt2++;
								three = true;
							}
						}
						
						if (n == 0) {
							if (four) {
								four = false;
								two = true;
								
								if (calN) {
									codeArray[cnt] = findN(cnt2, cnt3, cnt4);
									cnt++;
									
									cnt2 = 0;
									cnt3 = 0;
									cnt4 = 0;
									calN = false;
								}
							}
							
							if (three) {
								cnt3++;
								two = false;
								four = true;
							}
						}
						
					}
				}

			}
	
	// find number
			static int findN (int b, int c, int d) {
				if (b == 2 && c == 1 && d == 1) {
					return 0;
				} else if (b == 2 && c == 2 && d == 1) {
					return 1;
				} else if (b == 1 && c == 2 && d == 2) {
					return 2;
				} else if (b == 4 && c == 1 && d == 1) {
					return 3;
				} else if (b == 1 && c == 3 && d == 2) {
					return 4;
				} else if (b == 2 && c == 3 && d == 1) {
					return 5;
				} else if (b == 1 && c == 1 && d == 4) {
					return 6;
				} else if (b == 3 && c == 1 && d == 2) {
					return 7;
				} else if (b == 2 && c == 1 && d == 3) {
					return 8;
				} else {
					return 9;
				}
				
			}
}
