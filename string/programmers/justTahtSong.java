import java.util.*;

class Solution {
    static HashMap<String, String> hm = new HashMap<>();
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int time = 0; // 가장 긴 재생 시간
        
        // # 음계 알파벳 치환
        hm.put("C#", "c");
        hm.put("D#", "d");
        hm.put("F#", "f");
        hm.put("G#", "g");
        hm.put("A#", "a");
        hm.put("B#", "b");
        
        // 음악 정보 순회
        for (String str: musicinfos) {
            String[] arr = str.split(","); // 시작시간, 종료시간, 제목, 악보
            String[] st = arr[0].split(":");
            String[] ed = arr[1].split(":");
            
            int minute = (Integer.parseInt(ed[0]) - Integer.parseInt(st[0])) * 60
                + (Integer.parseInt(ed[1]) - Integer.parseInt(st[1]));
            
            String melody = converting(arr[3]);
            String neo = converting(m);
            String record = ""; // 실제 라디오에서 재생된 멜로디
            
            for (int i = 0; i < minute; i++) {
                record += melody.charAt(i % melody.length());
            }
            
            if (record.contains(neo)) {
                if (minute > time) {
                    answer = arr[2];
                    time = minute;
                }
            }
        }
        
        if (answer == "") answer = "(None)";
        
        return answer;
    }
    
    // '#'이 포함된 음을 한 글자로 치환
    static String converting(String s) {
        String str = "";
        
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i + 1) == '#') {
                str += hm.get(s.substring(i, i + 2));
                i++;
            } else str += s.charAt(i);
        }
        
        return str;
    }
}

