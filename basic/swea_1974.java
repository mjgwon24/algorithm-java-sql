import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int t = 0;
	static int[][] puzzles;
	
	public static void main(String[] args) throws IOException {
		// 1. test case t input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		
		// 2. input each case
		for (int i = 0; i < t; i++) {
			puzzles = new int[9][9];
			
			for (int j = 0; j < 9; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < 9; k ++) {
					puzzles[j][k]= Integer.parseInt(st.nextToken());
				}
			}
			
			// print result
			System.out.print("#" + (i + 1) + " ");
			System.out.print(calResult());
			System.out.println();
		}
	}
	
	// calculate result
	static int calResult() {
		// 1. row true?
		boolean[] visited;
		for (int i = 0; i < 9; i++) {
			visited = new boolean[10];
			
			for (int j = 0; j < 9; j++) {
				int n = puzzles[i][j];
				
				if (!visited[n]) {
					visited[n] = true; 
				} else {
					return 0;
				}
			}
		}
		
		// 2. column true?
		for (int i = 0; i < 9; i++) {
			visited = new boolean[10];
			
			for (int j = 0; j < 9; j++) {
				int n = puzzles[j][i];
				
				if (!visited[n]) {
					visited[n] = true; 
				} else {
					return 0;
				}
			}
		}
		
		// 3. 3 x 3 true?
		for (int i = 0; i < 3; i++) {
			visited = new boolean[10];
			
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					int y = i*3;
					int n = puzzles[j][y + k];
					
					if (!visited[n]) {
						visited[n] = true; 
					} else {
						return 0;
					}
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			visited = new boolean[10];
			
			for (int j = 3; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					int y = i*3;
					int n = puzzles[j][y + k];
					
					if (!visited[n]) {
						visited[n] = true; 
					} else {
						return 0;
					}
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			visited = new boolean[10];
			
			for (int j = 6; j < 9; j++) {
				for (int k = 0; k < 3; k++) {
					int y = i*3;
					int n = puzzles[j][y + k];
					
					if (!visited[n]) {
						visited[n] = true; 
					} else {
						return 0;
					}
				}
			}
		}
		
		return 1;
	}
}
