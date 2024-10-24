import java.util.*;
class Solution {
    HashSet<Integer> set = new HashSet<>();
    boolean[] visited = new boolean[7];
    public int solution(String numbers) {
        int answer = 0;
        dfs(numbers,"",0);
        
        for(int i : set){
            if(isPrime(i))
                answer++;
        }
        return answer;
    }
    
    private boolean isPrime(int num){
        if(num<2)
            return false;
        for(int i=2; i <= (int) Math.sqrt(num); i++){
            if(num%i == 0)
                return false;
        }
        return true;
    }
    
    private void dfs(String s, String c, int d){
        if(d == s.length()){
            return;
        }
        
        for(int i = 0; i<s.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                String temp = c+s.charAt(i);
                set.add(Integer.parseInt(temp));
                dfs(s,temp,d+1);
                visited[i] = false;
            }                
        }
    }
}