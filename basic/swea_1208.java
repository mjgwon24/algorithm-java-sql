import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int dumpNumber = 0;
	static int[] lengthArray;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// each case input
		for (int i = 0; i < 10; i++) {
			// 1. input dump number
			st = new StringTokenizer(br.readLine());
			dumpNumber = Integer.parseInt(st.nextToken());
			
			// 2. each length input
			st = new StringTokenizer(br.readLine());
			lengthArray = new int[100];
			
			for (int j = 0; j < 100; j++) {
				lengthArray[j]= Integer.parseInt(st.nextToken());
			}
			
			// caculate result
			runDump();
			
			// print output
			System.out.print("#" + (i + 1) + " ");
			System.out.print(diff());
			System.out.println();
		}
	}
	
	static void runDump() {
		int n = dumpNumber;
		
		while (n > 0) {
			int max = lengthArray[0];
			int maxIndex = 0;
			int min = lengthArray[0];
			int minIndex = 0;
			
			// 1. find max, min
			for (int i = 0; i < 100; i++) {
				int now = lengthArray[i];
				
				if (max < now) {
					max = now;
					maxIndex = i;
				}
				if (min > now) {
					min = now;
					minIndex = i;
				}
			}
			
			// 2. switch
			lengthArray[maxIndex]--;
			lengthArray[minIndex]++;
			
			n--;
		}
	}
	
	static int diff() {
		int diff = 0;
		int max = lengthArray[0];
		int min = lengthArray[0];
		
		// find max, min
					for (int i = 0; i < 100; i++) {
						int now = lengthArray[i];
						
						if (max < now) {
							max = now;
						}
						if (min > now) {
							min = now;
						}
					}
		
					diff = max - min;
		
		return diff;
	}
}
