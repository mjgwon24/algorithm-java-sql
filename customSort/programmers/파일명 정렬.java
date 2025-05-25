import java.util.*;

class Solution {
    static class File {
        String head;
        String number;
        String tail;
        
        File (String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }
    
    static List<File> list = new ArrayList<>();
    
    public String[] solution(String[] files) {
        // 1. 파일명을 세 부분으로 나누기 (head, number, tail)
        for (String file: files) {
            File f = splitHNT(file);
            // System.out.println(f.head + ", " + f.number + ", " + f.tail);
            list.add(f);
        }
        
        // 정렬
        list.sort((a, b) -> {
            String aHead = a.head.toLowerCase();
            String bHead = b.head.toLowerCase();
            Integer aNumber = Integer.parseInt(a.number);
            Integer bNumber = Integer.parseInt(b.number);
            
            // 2. head 기준 사전 순 정렬 - 문자열 비교시 대소문자 구분 x
            if (!aHead.equals(bHead)) return aHead.compareTo(bHead);
            else if (aNumber != bNumber) {
                // 3. head가 같을 경우, number순으로 정렬 
                // (9 < 0011) (숫자 앞의 0은 무시) (12와 012는 같음)
                return aNumber - bNumber;
            } else {
                // 4. head, number가 같을 경우, 원래 입력 순서 유지
                return 0;
            }
        });
        
        String[] answer = new String[list.size()];
        int idx = 0;
        for (File f: list) {
            answer[idx++] = f.head + f.number + f.tail;
        }
        
        return answer;
    }
    
    // foo9.txt
    static File splitHNT(String s) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        
        boolean head = true;
        boolean number = false;
        for (char c: s.toCharArray()) {
            if (head && !number && !Character.isDigit(c)) sb1.append(c);
            else if (head && !number && Character.isDigit(c)) {
                sb2.append(c);
                head = false;
                number = true;
            } else if (!head && number && Character.isDigit(c)) {
                sb2.append(c);
            } else if (!head && number && !Character.isDigit(c)) {
                sb3.append(c);
                number = false;
            } else {
                sb3.append(c);
            }
        }
        
        return new File(sb1.toString(), sb2.toString(), sb3.toString());
    }
}

/*
files 파일명
- HEAD, NUMBER, TAIL의 세 부분으로 구성

files
["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]
answer
["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]

1. 파일명을 세 부분으로 나누기 (head, number, tail)
2. head 기준 사전 순 정렬 - 문자열 비교시 대소문자 구분 x
3. head가 같을 경우, number순으로 정렬 (9 < 0011) (숫자 앞의 0은 무시) (12와 012는 같음)
4. head, number가 같을 경우, 원래 입력 순서 유지



*/
