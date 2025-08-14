import java.io.*;
import java.util.*;

class Main {
	static List<int[]> divList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine(); // "abcd"
		int answer = 3;

		Set<String> set = new HashSet<>();

		// 1. 부분 문자열 집합 만들기 (길이 1 이상)
		getDivList(N, new int[3], 0, 0);
		List<Map<String, Integer>> listMap = new ArrayList<>(); // {a 1, b 1, cd 1}
		
		for (int[] div : divList) { // 1 1 2
			Map<String, Integer> map = new HashMap<>();
			String key = str.substring(0, div[0]);
			map.put(key, map.getOrDefault(key, 0) + 1);
			set.add(key);

			key = str.substring(div[0], div[0] + div[1]);
			map.put(key, map.getOrDefault(key, 0) + 1);
			set.add(key);

			key = str.substring(div[0] + div[1], N);
			map.put(key, map.getOrDefault(key, 0) + 1);
			set.add(key);

			listMap.add(map);
		}
		
		// 2. Set을 List로 변환 -> (인덱스에 따른 점수 줘야함 1-index)
		List<String> list = new ArrayList<>(set);
		list.sort((a, b) -> a.compareTo(b));
		// System.out.println(list);

		Map<String, Integer> scoreMap = new HashMap<>();
		int cnt = 1;
		for (String s : list) {
			scoreMap.put(s, cnt++);
		}
		
		// 3. 부분 문자열 집합을 순회하면서 최대 점수 도출
		for (Map<String, Integer> map : listMap) {
			// System.out.println(map.size());
			int sum = 0;
			for (Map.Entry<String, Integer> e : map.entrySet()) {
				// System.out.println(e.getKey() + ": " + e.getValue());
				sum += scoreMap.get(e.getKey()) * e.getValue();
			}

			// System.out.println();
			answer = Math.max(answer, sum);
		}

		// 부분 문자열 3개로 나누었을때 얻을 수 있는 최대 점수 출력
		System.out.println(answer);
	}

	static void getDivList(int N, int[] current, int idx, int sum) {
		if (idx == 2) {
			current[2] = N - sum;
			int[] newCurrent = Arrays.copyOf(current, current.length);
			divList.add(newCurrent);
			return;
		}

		for(int i = 1; i <= N - sum - 1; i++) {
			current[idx] = i;
			getDivList(N, current, idx + 1, sum + i); 
		}
	}
}

/*
문자열 S를 서로 겹치지 않는 3개의 부분문자열로 나누기 (길이는 모두 1 이상이어야 함, 원래 문자열에서 연속해야 함)
점수 획득 과정
- P : 부분문자열을 중복 제거하고 사전순으로 정렬한 결과
- 나누어진 3개의 문자열이 각각 P에서 i, j, k번째로 등장하는 문자열이라면, 얻을 수 있는 점수는 i+j+k
---
abcd -> 1/1/2 , 1/2/1, 2/1/1
1. 부분 문자열 집합 List<Map<String, Integer>> = {a 1, b 1, cd 1}, {a 1, bc 1, d 1}, {ab 1, c 1, d 1}
2. 사전순 정렬 결과 P(set) = a, ab, b, bc, c, cd, d
3. Set을 List로 변환 -> (인덱스에 따른 점수 줘야함 1-index)
4. 부분 문자열 집합을 순회하면서 최대 점수 도출
---
5
1/1/3
1/2/2
1/3/1
2/1/2
2/2/1
3/1/1
List<int[]> => {1, 1, 3}, {1, 2, 2} ...

*/
