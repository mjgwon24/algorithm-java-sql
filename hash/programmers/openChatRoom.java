import java.util.*;
import java.lang.*;

class Solution {
    static HashMap<String, String> dictionary = new HashMap<>();
    static List<String[]> commandsList = new ArrayList<>(); // <user id, command>
    
    public String[] solution(String[] record) {
        // 1. 각 유저별 유저 아이디와 닉네임을 별도로 저장하는 HashMap 생성 
        //    && 반환할 명령어를 유저 아이디 기반 저장
        for (int i = 0; i < record.length; i++) {
            String[] str = record[i].split(" ");
            
            // 1.1 Enter -> 기존에 저장된 유저 아이디 키가 있는지 확인, 없다면 바로 저장, 있다면 닉네임 갱신
            if (str[0].equals("Enter")) {
                if (!dictionary.containsKey(str[0])) {
                    // 기존에 저장된 유저 아이디 키가 없다면 바로 저장
                    dictionary.put(str[1], str[2]);
                } else {
                    // 기존에 저장된 유저 아이디 키가 이미 있다면 닉네임 갱신
                    dictionary.replace(str[1], str[2]);
                }
                
                commandsList.add(new String[]{str[1], str[0]});
            }
            
            // 1.2 Change -> 닉네임 갱신 (명령어 리스트에 저장 x)
            if (str[0].equals("Change")) {
                dictionary.replace(str[1], str[2]);
            }
            
            if (str[0].equals("Leave")) {
                commandsList.add(new String[]{str[1], str[0]});
            }
        }
        
        // 2. record의 첫 단어와 유저 아이디에 따라 올바른 String 배열 반환
        String[] answer = new String[commandsList.size()];
        int cnt = 0;
        for (String[] strArr : commandsList) {
            // 2.1 저장해놓은 반환 명령어 목록에서 유저 아이디를 닉네임으로 전환
            String userNickname = dictionary.get(strArr[0]);
            
            // 2.2 Enter -> 님이 들어왔습니다.
            //     Leave -> 님이 나갔습니다.
            String command =  strArr[1].equals("Enter") ? "님이 들어왔습니다."
                : strArr[1].equals("Leave") ? "님이 나갔습니다."
                    : "";
            
            answer[cnt] = userNickname + command;
            cnt++;
        }
        
        // 방을 개설한 사람이 보게 되는 메시지
        return answer;
    }
    
    static class CommandDict {
        String userId;
        String command;
        
        public CommandDict(String userId, String command) {
            this.userId = userId;
            this.command = command;
        }
    }
}

/***
[ 요구사항 ]
모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return 

[ 조건 ]
• 채팅방은 중복 닉네임을 허용
• 모든 유저는 [유저 아이디]로 구분
• 첫 단어는 Enter, Leave, Change 중 하나

[ 입력 ]
String[] record: 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열

["Enter uid1234 Muzi", 
"Enter uid4567 Prodo",
"Leave uid1234",
"Enter uid1234 Prodo", => 이름이 변경됨 Muzi -> Prodo
"Change uid4567 Ryan"]

[ solution ]
1. 각 유저별 유저 아이디와 닉네임을 별도로 저장하는 HashMap 생성 & 반환할 명령어를 유저 아이디 기반 저장
    1.1 Enter -> 기존에 저장된 유저 아이디 키가 있는지 확인, 없다면 바로 저장, 있다면 닉네임 갱신
    1.2 Change -> 닉네임 갱신
2. record의 첫 단어와 유저 아이디에 따라 올바른 String 배열 반환
    2.1 저장해놓은 반환 명령어 목록에서 유저 아이디를 닉네임으로 전환
    2.2 Enter -> 님이 들어왔습니다.
        Leave -> 님이 나갔습니다.
    2.3 반환


***/
