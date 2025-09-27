import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        int prev = -1;
        List<Integer> ans = new ArrayList<>();
        for(int i : arr){
            if(prev != i){
               ans.add(i);
                prev = i;
            }
        }
        
        int[] answer = new int[ans.size()];
        
        for(int idx = 0 ; idx < ans.size(); ++idx){
            answer[idx] = ans.get(idx);
        }

        return answer;
    }
}