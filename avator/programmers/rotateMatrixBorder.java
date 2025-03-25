import java.lang.*;
import java.util.*;

class Solution {
    static List<Integer> answerList = new ArrayList<>();
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        // 0. map 배열 초기화
        map = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = (i * columns) + (j + 1);
            }
        }
        
        // queries 사이즈만큼 회전 반복
        for (int[] query: queries) {
            rotation(query);
        }
        
        // 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    // 회전
    static void rotation(int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;
        int minValue = map[x1][y1]; // 현재 영역에서의 최솟값
        int firstValue = map[x1][y1];
        
        // left. bottom -> top [정방향]
        for (int i = x1; i < x2; i++) {
            map[i][y1] = map[i + 1][y1];
            minValue = Math.min(minValue, map[i][y1]);
        }
        
        // bottom. right -> left [정방향]
        for (int j = y1; j < y2; j++) {
            map[x2][j] = map[x2][j + 1];
            minValue = Math.min(minValue, map[x2][j]);
        }
        
        // right. top -> bottom [역방향] i = x2; i > x1; i--
        for (int i = x2; i > x1; i--) {
            map[i][y2] = map[i - 1][y2];
            minValue = Math.min(minValue, map[i][y2]);
        }
        
        // top. left -> right [역방향] j = y2; j > y1; --
        for (int j = y2; j > y1; j--) {
            map[x1][j] = map[x1][j - 1];
            minValue = Math.min(minValue, map[x1][j]);
        }

        map[x1][y1 + 1] = firstValue;
        answerList.add(minValue);
    }
}

/***
[ 요구사항 ]
회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return

[ 조건 ]
• rows x columns 크기인 행렬
• 처음에 행렬에는 가로 방향으로 숫자가 1부터 하나씩 증가하면서 적혀있음
    => 아무 회전도 하지 않았을 때, i 행 j 열에 있는 숫자는 ((i-1) x columns + j)
• 행렬에서 직사각형 모양의 범위를 여러 번 선택 -> 테두리 부분에 있는 숫자들을 시계방향으로 회전
• 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현
    => x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전
    => 이때, 중앙의 영역은 회전하지 않는 것을 주의
• 모든 회전은 순서대로 이루어집니다.

[ 입력 ]
int rows 행렬 세로 길이 (행 개수)
int columns 가로 길이 (열 개수)
int[][] queries 회전들의 목록 [x1, y1, x2, y2]
    => (1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns)
    
[ 도출 흐름 ]
rows = 6, colums = 6
queries = [[2,2,5,4],[3,3,6,6],[5,1,6,3]]]
result = [8, 10, 25]

0. map 배열 초기화

queries 사이즈만큼 반복
1. 시계방향으로 한칸씩 회전
2. 해당 범위에서의 최솟값을 answerList에 추가

(1) [2,2,5,4]
x1 = 1, y1 = 1, x2 = 4, y2 = 3

돌리면서 계속해서 최솟값 갱신 (minValue)
left. bottom -> top [정방향] i = x1; i < x2; i++

bottom. right -> left [정방향] int j = y1; j < y2; j++
map[x2][j] = map[x2][j + 1]

right. top -> bottom [역방향] i = x2; i > x1; i--
map[i][y2] = map[i - 1][y2]
map[4][3] = map[3][3] = 22
map[3][3] = map[2][3] = 16
map[2][3] = map[1][3] = 10

top. left -> right [역방향] j = y2; j > y1; --
map[x1][j] = map[x1][j - 1]
map[1][3] = map[1][2] = 9
map[1][2] = map[1][1] = 8



***/
