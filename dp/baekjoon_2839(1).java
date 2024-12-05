import java.util.*;

public class Main {
	static int[] dp;
    static int N;
    
    public static void main(String[] args) {
  		Scanner sc = new Scanner(System.in);
	    N = sc.nextInt();
    
    	// DP 테이블 초기화
	    dp = new int[N+1];
    	Arrays.fill(dp, Integer.MAX_VALUE); // 초기값 설정: 만들 수 없는 상태
	   	dp[0] = 0;
        
        //DP 점화식 적용
        for (int i = 3; i <= N; i++) {
        	if (i >= 3 && dp[i - 3] != Integer.MAX_VALUE) {
            	dp[i] = Math.min(dp[i], dp[i - 3] + 1);
            }
            
            if (i >= 5 && dp[i - 5] != Integer.MAX_VALUE) {
            	dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }
        
        int result = dp[N] == Integer.MAX_VALUE ? -1 : dp[N];
        System.out.println(result);
    }
}
