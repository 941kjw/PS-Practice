import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Map<Character,Integer> skillMap = new HashMap<>();
        for(int i = 0; i < skill.length(); ++i){
            skillMap.put(skill.charAt(i),i);
        }
        
        int count = 0;
        
        for(String skillTree : skill_trees){
            int idx = -1;
            boolean wrong = false;
            for(int i = 0; i < skillTree.length(); ++i){
                Integer skillIdx = skillMap.get(skillTree.charAt(i));
                
                if(skillIdx != null){
                    if(idx + 1 == skillIdx){
                        idx = skillIdx;
                    } else{
                        wrong = true;
                        break;
                    }
                }
            }
            
            if(!wrong)
                ++count;
            
        }
        return count;
    }
}