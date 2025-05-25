import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] skillChar = skill.toCharArray();
        HashSet<Character> skillSet = new HashSet<>();
        for (char c: skill.toCharArray()) {
            skillSet.add(c);
        }
        
        // BACDE
        for (String skillTree: skill_trees) {
            int needIdx = 0;
            boolean flag = false;
            
            for (char c: skillTree.toCharArray()) {
                if (skillSet.contains(c)) {
                    // needIdx가 아니면, 불가능
                    if (c == skillChar[needIdx]) needIdx++;
                    else {
                        flag = true;
                        break;
                    }
                }
            }
            
            if (!flag) answer++;
        }
        
        
        // 가능한 스킬트리 개수 return
        return answer;
    }
}

/*
skill 선행 스킬 순서
skill_trees 유저들이 만든 스킬트리

skill CBD
skill_trees
["BACDE", "CBADF", "AECB", "BDA"]
return 2

*/
