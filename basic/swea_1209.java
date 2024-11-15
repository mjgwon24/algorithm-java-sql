
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int caseNum = 0;
	static int[][] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 10; i++) {
			array = new int[100][100];
			
			// 1. input test case number
			st = new StringTokenizer(br.readLine());
			caseNum = Integer.parseInt(st.nextToken());
			
			// 2. each table number input
			for (int j = 0; j < 100; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < 100; k++) {
					array[j][k]= Integer.parseInt(st.nextToken());
				}
			}
			
			// print result
			System.out.print("#" + caseNum + " ");
			System.out.print(calMax());
			System.out.println();
		}
	}
	
	// calculate result
	static int calMax() {
		int max = 0;
		int[] maxRowArray = new int[100];
		int[] maxColArray = new int[100];
		int rightMax = 0;
		int leftMax = 0;
		int sum1 = 0;
		int sum2 = 0;
		
		// 1. cal each max
		for (int i = 0; i < 100; i++) {
			sum1 = 0;
			sum2 = 0;
			
			for (int j = 0; j < 100; j++) {
				sum1 += array[i][j];
				sum2 += array[j][i];
				
				if (i == 0) {
					rightMax += array[j][j];
				}
				
				if (i == 99) {
					leftMax += array[j][i-j];
				}
			}
			
			maxRowArray[i] = sum1;
			maxColArray[i] = sum2; 
		}
		
		// 2. cal real max
		for (int i = 0; i < 100; i++) {
			if (max < maxRowArray[i]) {
				max = maxRowArray[i];
			}
		}
		
		for (int i = 0; i < 100; i++) {
			if (max < maxColArray[i]) {
				max = maxColArray[i];
			}
		}
		
		if (max < rightMax) {
			max = rightMax;
		}
		
		if (max < leftMax) {
			max = leftMax;
		}
		
		return max;
	}
}
