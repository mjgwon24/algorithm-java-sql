import java.lang.*;
import java.util.*;

class Solution {
    // 연결되어있는 노드 List (인덱스, (연결 노드 리스트))
    static List<Integer>[] graph; // 연결 현황 
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
//         computers
//         [[1, 1, 0], 
//         [1, 1, 0], 
//         [0, 0, 1]]	
        
//         computers[0][1] = 1
//         => 0과 1 연결
//         computers[1][0] = 1
//         => 0과 1 연결
        
//         graph[0] = (1)
//         graph[1] = (0)
        
        // 0. graph 초기화 - 양방향
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        // 1. 인덱스를 돌며 연결되어있는 노드 탐색 - BFS
        visited = new boolean[n];
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i);
                answer++;
            }
        }
        
        
        // 네트워크 개수
        return answer;
    }
    
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next: graph[cur]) {
                if (visited[next]) continue;
                q.add(next);
                visited[next] = true;
            }
        }
    }
}

/***
int n 컴퓨터 개수
int[][] computers 연결에 대한 정보

[ex1]
n = 3
computers
[[1, 1, 0], 
[1, 1, 0], 
[0, 0, 1]]	

computers[0][1] = 1
=> 0과 1 연결
computers[1][0] = 1
=> 0과 1 연결

graph.get(0) = (1)
graph.get(1) = (0)

connectList.get(0) = (0, 1)
connectList.get(1) = (2)
2개

[ex2]
[[1, 1, 0], 
[1, 1, 1], 
[0, 1, 1]]	

computers[0][1] = 1
=> 0과 1 연결
computers[1][0] = 1
=> 0과 1 연결

computers[1][2] = 1
=> 1과 2 연결
computers[2][1] = 1
=> 1과 2 연결

graph.get(0) = (1)
graph.get(1) = (0, 2)
graph.get(2) = (1)

connectList.get(0) = (0, 1, 2)
1개

• i와 연결되어있는 모든 노드 찾기

[필요 변수]
List<List<Integer>> graph 연결 현황 

0. graph 초기화
1. 인덱스를 돌며 연결되어있는 노드 탐색 - BFS

***/
