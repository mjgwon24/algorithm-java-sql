import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Deque<Integer> bucket = new ArrayDeque<>();
        Deque<Integer>[] boardDq = new ArrayDeque[board[0].length];
        
        // 게임판 초기화
        for (int i = 0; i < board[0].length; i++) {
            boardDq[i] = new ArrayDeque<>();
            
            for (int idx = board.length - 1; idx >= 0; idx--) {
                if (board[idx][i] == 0) break;
                
                boardDq[i].addLast(board[idx][i]);
            }
        }
        
        // 인형 2개가 연속해서 쌓이면, 두 인형은 터짐 (터진 카운트 세기)
        for (int move: moves) {
            if (boardDq[move - 1].isEmpty()) continue;
            
            // 뽑은 인형의 인덱스
            int nowIdx = boardDq[move - 1].pollLast();
            
            // 바구니 마지막이 뽑은 인형 인덱스와 같으면, 카운트 후 제거
            if (!bucket.isEmpty() && bucket.peekLast() == nowIdx) {
                answer += 2;
                bucket.removeLast();
                continue;
            } else {
                // 바구니에 넣기
                bucket.addLast(nowIdx);
            }
        }
        
        // 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return
        return answer;
    }
}

/*
[[0,0,0,0,0],
[0,0,1,0,3],
[0,2,5,0,1],
[4,2,4,4,2],
[3,5,1,3,1]]	
*/
