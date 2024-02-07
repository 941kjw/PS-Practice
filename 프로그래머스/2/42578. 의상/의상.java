import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String,Integer> map =  new HashMap<>();
        
        for(String[] cloth : clothes){
            map.computeIfPresent(cloth[1] , (k,v) -> ++v);
            map.putIfAbsent(cloth[1],1);
        }
        
        for(Integer i : map.values()){
            answer *= i+1;
        }
        return --answer;
    }
}