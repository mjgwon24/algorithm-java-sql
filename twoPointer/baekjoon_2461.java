import java.io.*;
import java.util.*;

/**
 대표 선수
 투포인터, 우선순위 큐, 2중배열 탐색, compareTo 비교
 
 최종 제출 시간: 1196ms
 최종 메모리: 164964KB
 
 사용 시간 복잡도
 1. 입력 및 정렬 (O(NM log M))
  - stats 배열에 입력을 저장하는 과정 → O(NM)
  - 각 학급의 능력치 배열을 정렬 (Arrays.sort(stats[i])) → O(M log M)
  - N개의 학급이 있으므로 O(NM log M)
 
 2. 초기 우선순위 큐 삽입 (O(N log N)) 
  - minStats와 maxStats 두 개의 PriorityQueue에 N개의 학생을 삽입 → O(N log N)

 3. 대표 선수 변경 과정 (O(NM log N))
  - while 루프에서 각 학급의 대표 선수를 변경하며 탐색.
  - 최악의 경우 모든 반에서 학생을 M번 교체 → 최대 NM번 실행
  - PriorityQueue는 삽입/삭제 연산이 O(log N)이므로 → O(NM log N)

  최종 시간 복잡도: O(NM logM + NM logN) ~ O(N*M + N*M)
**/

public class Main {
    static int N, M;
    static int[][] stats;
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stats = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(stats[i]); // 각 반의 능력치 오름차순 정렬
        }

        PriorityQueue<Student> minStats = new PriorityQueue<>();
        PriorityQueue<Student> maxStats = new PriorityQueue<>(
            (a, b) -> b.stat - a.stat);

        // 가장 능력치가 좋은 학생들로 초기화
        int[] selected = new int[N]; // 학급별 대표선수로 선택된 인덱스 저장 배열
        for (int i = 0; i < N; i++) {
            minStats.add(new Student(stats[i][M - 1], i)); // 능력치, 반
            maxStats.add(new Student(stats[i][M - 1], i)); // 능력치, 반
            selected[i] = M - 1;
        }

        result = maxStats.peek().stat - minStats.peek().stat;

        while (true) {
            // 최댓값 학생의 인덱스가 0일 경우 더이상 낮출 수 없으므로 break
            // selected[반] = 대표 학생 인덱스
            int maxStudentIdx = selected[maxStats.peek().classIdx]; 
            if (maxStudentIdx == 0) break;
            
            // 최댓값 학생을 보유한 학급의 능력치를 한단계 줄임
            Student maxStudent = maxStats.poll(); // 능력치, 반
            int maxStudentClass = maxStudent.classIdx;
            selected[maxStudentClass]--;
            int nextStat = stats[maxStudentClass][selected[maxStudentClass]]; // [반][학생번호]
            maxStats.add(new Student(nextStat, maxStudentClass));

            // 최솟값 PriorityQueue에서도 해당 학생을 교체
            minStats.add(new Student(nextStat, maxStudentClass)); // 능력치, 소속 학급
            
            // 최댓값, 최솟값 차이 갱신
            long diff = maxStats.peek().stat - minStats.peek().stat;
            
            if (diff == 0) {
                System.out.println(0);
                return;
            }
            
            result = Math.min(result, diff);
        }
        
        System.out.println(result);
    }

    static class Student implements Comparable<Student> {
        int stat, classIdx; // 능력치, 반

        Student(int stat, int classIdx) {
            this.stat = stat;
            this.classIdx = classIdx;
        }

        @Override
        public int compareTo(Student other) {
            return this.stat - other.stat;
        }
    }
}
