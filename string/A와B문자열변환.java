import java.io.*;
class Main {
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();

		if (!B.contains(A)) {
			System.out.println(-1);
			return;
		}

		isEqual(A, B, 0);

		// S -> T 변환 가능 최소 연산 횟수 출력
		// 변환 불가할 경우 -1 출력
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	// DFS 2^100
	static void isEqual(String s, String target, int cnt) {
		// 같으면 갱신
		if (s.equals(target)) {
			answer = Math.min(cnt, answer);
			return;
		}

		// 불가능
		if (s.length() >= target.length()) return;

		// 시도
		// 1. 문자열 뒤에 A 추가
		isEqual(s + "A", target, cnt + 1);
		// 2. 문자열 뒤집고 뒤에 B 추가
		
		isEqual(reverse(s) + "B", target, cnt + 1);
	}

	static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = s.length() - 1; i >=0; i--) {
			sb.append(s.charAt(i));
		}

		return sb.toString();
	}
}
