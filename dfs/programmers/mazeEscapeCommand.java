import java.lang.*;
import java.util.*;

class Solution {
    // 사전: d -> l -> r -> u
    static int[][] move = {
        {1, 0}, // bottom (d)
        {0, -1}, // left (l)
        {0, 1}, // right(r) 
        {-1, 0} // top (u)
    };
    static String[] moveChar = {"d", "l", "r", "u"};
    static List<String> escapeList = new ArrayList<>();
    static String answer = null;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 0. 처음부터 너무 먼 경우 바로 impossible return
        // * k - minDist가 홀수면 불가능 -> 최단거리에서, "왔다 갔다" 해야하기때문에 짝수만 가능
        int minDist = Math.abs(r - x) + Math.abs(c - y);
        if (minDist > k || (k - minDist) % 2 != 0) return "impossible";
        
        // 1. k회 이동하여 탈출하는 모든 경로 도출 - escapeList
        findKEscapeDFS(k, new StringBuilder(), n, m, x, y, r, c, k);
        
        // 2. 만약 escapeList가 비었다면, impossible return
        if (answer == null) return "impossible";
        
        // 3. escapeList에서 문자열이 사전순으로 가장 빠른 경로 반환
        // Collections.sort(escapeList);
        
        // for (String str : escapeList) {
        //     System.out.println(str);
        // }
        
        // 미로를 탈출하기 위한 경로
        return answer;
    }
    
    // k회 이동하여 탈출하는 모든 경로 도출
    // 첫번째로 나오는 경로가 가장 짧은 경로여야지만 시간복잡도 줄일 수 있음
    static void findKEscapeDFS(int remain, StringBuilder method, int n, int m, int x, int y, int r, int c, int k) {
        // 첫번째로 나오는 경로가 가장 짧은 경로. 바로 RETURN
        if (answer != null) return;
        
        // 너무 먼 경우 건너뛰기 (어짜피 못함)
        int minDist = Math.abs(r - x) + Math.abs(c - y);
        if (minDist > remain) return;
        
        // 초과시에는 continue
        if (remain < 0) return;
        
        if (remain == 0 && x == r && y == c) {
            answer = method.toString();
            return;
        }
        
        // 사전: d -> l -> r -> u
        for (int i = 0; i < 4; i++) {
            int nx = x + move[i][0];
            int ny = y + move[i][1];
            
            if (nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
            
            // String nextMethod = method + returnMethodStr(i);
            
            // 너무 먼 경우 건너뛰기 (어짜피 못함)
            minDist = Math.abs(r - nx) + Math.abs(c - ny);
            if (minDist > remain) continue;
            
            method.append(moveChar[i]);
            findKEscapeDFS(remain - 1, method, n, m, nx, ny, r, c, k);
            // 백트래킹
            method.setLength(method.length() - 1);
            
            // stringBuilder 문자 제거 메서드 -> setCharAt
        }
    }
    
    static class Miro {
        int mx;
        int my;
        int dist;
        String method;
        
        public Miro(int x, int y, int dist, String method) {
            this.mx = x;
            this.my = y;
            this.dist = dist;
            this.method = method;
        }
    }
}

/***
[ 요구사항 ]
미로를 탈출하기 위한 경로를 return 하도록 solution 함수를 완성
단, 위 조건대로 미로를 탈출할 수 없는 경우 "impossible"을 return

[ 조건 ]
1. 격자의 바깥으로는 나갈 수 없다
2. (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 
    이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다. (중복 가능)
3. 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.

• l: 왼쪽으로 한 칸 이동 => move[0]
• r: 오른쪽으로 한 칸 이동 => move[1]
• u: 위쪽으로 한 칸 이동 => move[2]
• d: 아래쪽으로 한 칸 이동 => move[3]

[ 입력 ]
n, m: 격자의 크기
x, y: 출발 위치
r, c: 탈출 지점
k: 탈출까지 이동해야 하는 거리

[ solution ]
0. 처음부터 너무 먼 경우 바로 impossible return
1. k회 이동하여 탈출하는 모든 경로 도출(dfs) - escapeList
2. 만약 escapeList가 비었다면, impossible return
// 3. escapeList에서 문자열이 사전순으로 가장 빠른 경로 반환
//     3.1 sort

[ 최적화 ]
16.3 -> 19 -> 97.4
• 메모이제이션, dp ?? 
• 방문처리 -> 중복 가능하다고해서... (x)
• 남은 거리 판단해서 남은 거리 내에 어떻게해도 못가면 바로 탈락시키기 (19)
• 사전순 탐색 (19) -> 이게 효과가 없다고? 말도 안돼

• bfs -> dfs 변경 (정답? 97.4)
• SORT 제거
• SWITCH 제거
• String -> StringBuilder (효과 없음)

[ 엣지 케이스 처리 ]
• 절대! 불가능한 경우를 파악하자
• 제약 조건을 극한까지 밀어붙여보자

1. 최단 거리(minDist)와 k의 관계 극한 테스트
    • k < minDist 체크
    • k - minDist가 홀수인 경우 체크
2. 경계 테스트
    • (1, 1), (1, m), (n ,1), (n, m) 에서 시작하거나 끝나는 경우
3. 사전순 이동 정확도 테스트 (d -> l -> r -> u)
4. 도달 가능한 길이 실제로 열려있는지 확인 (만약 벽이 있는 경우)
5. 입력값 최대 크기(n = 50, m = 50, k = 2499)로 성능(시간초과) 테스트



***/
