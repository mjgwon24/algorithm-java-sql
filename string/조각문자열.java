import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 1000번 반복
		for (int t = 0; t < T; t++) {
			String[] str = br.readLine().split(" ");

			// 가지고 있는 문자열과 원하는 문자열을 입력했을 때, 원하는 문자열로 만들 수 있는지 판별
			sb.append(isEqual(str[0], str[1]) ? "Yes\n" : "No\n");
		}

		System.out.println(sb.toString());
	}

	static boolean isEqual(String a, String b) {
		if (a.length() < b.length()) return false;

		if (a.length() == b.length()) {
			if (a.equals(b)) return true;
			return false;
		} 

		int j = 0;
		for (int i = 0; i < a.length(); i++) {
			if (j >= b.length()) return true;
			
			if (a.charAt(i) == b.charAt(j)) j++;
		}

		if (j >= b.length()) return true;
		
		return false;
	}
}
