import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    static List<Node>[] graph;
    static Set<Integer> gatesSet = new HashSet<>();
    static Set<Integer> summitsSet = new HashSet<>();

    static class Node implements Comparable<Node> {
        int v, w; // 목적지, 가중치

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.w - n.w; // 오름차순
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 출입구/목적지 집합 저장
        for (int gate : gates) gatesSet.add(gate);
        for (int summit : summits) summitsSet.add(summit);

        // 등산로 정보 저장 (양방향)
        for (int[] path : paths) {
            int a = path[0], b = path[1], w = path[2];

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        return dijkstra(n, gates, summits);
    }

    /**
     * 출발지(start) -> 목적지(end)
     * 목적지까지 가는 간선에서 가장 큰 가중치들 중 최솟값 도출
     */
    public int[] dijkstra(int n, int[] gates, int[] summits) {
        // 출입구에서 i번 지점까지 가는 경로 중에서 가장 큰 가중치들의 최솟값만을 저장하는 배열
        int[] intensity = new int[n + 1];
        // 최솟값만을 저장하기 위해 최댓값으로 초기화
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // intensity가 작은 순으로 정렬
        // -> 가장 작은 값이 먼저 나오기 때문에 최솟값을 찾기 쉬워짐
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 출발점에서 시작
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0; // 출발점은 0
        }
        
        int minSummit = Integer.MAX_VALUE; // 목적지중 가장 낮은 번호
        int minIntensity = Integer.MAX_VALUE; // intensity의 최솟값

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 꺼낸 현재 가중치가 저장된 가중치보다 크다면 무시
            // -> 현재 가중치가 저장된 가중치보다 크다면 이미 최소값이 아니기 때문
            if (current.w > intensity[current.v]) continue;
            
            // 목적지 도착 시, 최소값 갱신 후 종료
            if (summitsSet.contains(current.v)) {
                 // 현재 가중치가 최솟값보다 작거나 || 현재 가중치가 최솟값과 같고 현재 목적지가 최솟값보다 작다면
                if (current.w < minIntensity || (current.w == minIntensity && current.v < minSummit)) {
                    minIntensity = current.w;
                    minSummit = current.v;
                }
                continue;
            }
            
            // 다음 노드 탐색
            for (Node next : graph[current.v]) {
                if (gatesSet.contains(next.v)) continue; // 출입구 경유 불가능
                
                // 현재 경로에서 가장 높은 가중치 갱신
                int newIntensity = Math.max(current.w, next.w);
                
                if (newIntensity < intensity[next.v]) {
                    intensity[next.v] = newIntensity;
                    pq.offer(new Node(next.v, newIntensity)); // 현재 위치, 가중치
                }
            }
        }
        
        return new int[]{minSummit, minIntensity};
    }
}


/***
 입력
 n: 산 지점 개수
 paths: 각 등산로의 정보를 담은 2차원 정수 배열
 gates: 출입구들의 번호가 담긴 정수 배열
 summits: 산봉우리들의 번호가 담긴 정수 배열

 출력
 answer[0] = intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호
 answer[1] = intensity의 최솟값
 * intensity가 최소가 되는 등산코스가 여러 개라면, 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택

 각 지점: 출입구, 쉼터, 산봉우리 중 하나
 각 지점은 양방향 통행 가능 등산로로 연결
 서로 다른 지점을 이동할 때 이 등산로를 이용
 등산코스를 따라 이동하는 중 쉼터 혹은 산봉우리를 방문할 때마다 휴식

 intensity: 휴식 없이 이동해야 하는 시간 중 가장 긴 시간 (간선의 가중치)
 => 노드는 모두 휴식 가능함. 따라서 간선의 가중치 중 최댓값이 intensity가 됨.

 구하고자 하는 것
 출입구 중 한 곳에서 출발하여 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아오는 등산코스 정하기
 => 등산코스에서 출입구는 처음과 끝에 한 번씩, 산봉우리는 한 번만 포함
 (출입구 여러개 존재. 첫 출입구와 다른 출입구 두개는 모두 동일한 출입구여야함. 이외의 출입구는 존재할 수 없음.)

 ***/
