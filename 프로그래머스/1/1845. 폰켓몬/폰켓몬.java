import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> inventory = new HashSet<Integer>();
        
        for(int i : nums){
            inventory.add(i);
        }
        
        return inventory.size() > (nums.length / 2) ? nums.length/2 : inventory.size();
    }
}