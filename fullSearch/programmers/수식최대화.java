import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution(String expression) {
        List<String> parts = new ArrayList<>();
        int s = 0;
        for (int d = 1; d < expression.length(); d++) {
            if (!Character.isDigit(expression.charAt(d))) {
                parts.add(expression.substring(s, d));
                parts.add(expression.substring(d, d + 1));
                s = d + 1;
            }
        }
        parts.add(expression.substring(s));

        // 연산자 분리
        List<String> ops = Arrays.stream(expression.split("[0-9]"))
            .filter(ss -> !ss.isEmpty() && !ss.isBlank())
            .distinct()
            .collect(Collectors.toList());
        
        // 2. 연산자 우선순위 조합 출력
        List<List<String>> result = new ArrayList<>();
        permute(ops, 0, result);

        // 연산자 우선순위별로 결과 계산
        Long max = Long.MIN_VALUE;
        for (List<String> o : result) {
            max = Math.max(max, Math.abs(calResult(new ArrayList<>(parts), o)));
        }

        // 최댓값 결과 반환
        return max;
    }
    
    /**
    /* 연산자 우선순위별로 계산된 결과 값 도출 함수
    /* @param List<String> parts: 깊은 복사로 복사된 원본 parts
    **/
    static Long calResult(List<String> parts, List<String> ops) {
        for (String nowOps : ops) {
            for (int i = 1; i < parts.size(); i++) {
                String part = parts.get(i);
                
                if (part.equals(nowOps)) {
                    Long result = 0L;
                    
                    if (nowOps.equals("+")) {
                        result = Long.parseLong(parts.get(i - 1)) + Long.parseLong(parts.get(i + 1));
                    } else if (nowOps.equals("-")) {
                        result = Long.parseLong(parts.get(i - 1)) - Long.parseLong(parts.get(i + 1));
                    } else if (nowOps.equals("*")) {
                        result = Long.parseLong(parts.get(i - 1)) * Long.parseLong(parts.get(i + 1));
                    }

                    parts.remove(i + 1);
                    parts.remove(i);
                    parts.set(i - 1, result.toString());
                    i--;
                }
            }
        }

        return Long.parseLong(parts.get(0));
    }

    /**
    /* 연산자 기호 리스트로 우선순위 재정의 리스트 도출 함수
    /* @param List<String> ops: 중복이 없는 존재하는 연산자 목록
    **/
    static void permute(List<String> ops, int depth, List<List<String>> result) {
        if (depth == ops.size()) {
            result.add(new ArrayList<>(ops));
            return;
        }

        for (int i = depth; i < ops.size(); i++) {
            Collections.swap(ops, depth, i);
            permute(ops, depth + 1, result);
            Collections.swap(ops, depth, i); // 백트레킹
        }
    }
}
