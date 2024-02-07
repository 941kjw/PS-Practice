import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> partMap = new HashMap<>();
        for(String s : participant){
            partMap.computeIfPresent(s,(k,v) -> ++v);
            partMap.putIfAbsent(s,1);
        }
        
        for(String s : completion){
            partMap.compute(s,(k,v) -> --v);
        }
        
        for(Entry<String,Integer> e : partMap.entrySet()){
            if(e.getValue() >= 1){
                answer = e.getKey();
            }
        }
        return answer;
    }
}