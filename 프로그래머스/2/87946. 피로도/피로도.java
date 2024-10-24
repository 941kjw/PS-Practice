import java.util.*;
class Solution {
    int maxd = -1;
    boolean[] visited = new boolean[8];
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        dfs(dungeons,k,0,0);
        answer = maxd;
        return answer;
    }
    
    private void dfs(int[][] dungeons,int k,int count,int depth){
        if(dungeons.length == depth){
            maxd = Math.max(maxd,count);
            return;
        }
        
        for(int i=0; i<dungeons.length;i++){
            if(!visited[i]){
                visited[i] = true;
                if(k >= dungeons[i][0])
                    dfs(dungeons,k-dungeons[i][1],count + 1, depth + 1);
                else
                    dfs(dungeons,k,count, depth + 1);
                visited[i] = false;
            }
        }
    }
}