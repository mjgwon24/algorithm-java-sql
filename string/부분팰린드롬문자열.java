import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine(); // 1000글자
		int answer = 1;

		if (S.length() == 1) {
			System.out.println(answer);
			return;
		}

		// 부분 문자열 만들기 ala
		for (int len = S.length(); len > 1; len--) { 
			// 슬라이딩 윈도우
			for (int start = 0; start <= S.length() - len; start++) {
				if (isPal(S.substring(start, start + len))) {
					System.out.println(len);
					return;
				}
			}
		}

		// 부분 문자열들 중 가장 긴 팰린드롬 길이 출력
		System.out.println(answer);
	}

	static boolean isPal(String s) {
		// System.out.printf("들어온 문자열: %s\n", s);
		int N = s.length();
		if (N == 1) return true;

		int end = N % 2 == 0 ? N/2 - 1 : N/2;

		int j = N - 1;
		for (int i = 0; i <= end; i++) {
			// System.out.printf("대칭 검사: %c - %c\n", s.charAt(i), s.charAt(j));
			if (s.charAt(i) != s.charAt(j--)) return false;
		}
		
		return true;
	}
}
