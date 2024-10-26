import java.util.*;

class Solution {
    
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    public int solution(int[][] maps) {
        int answer = -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == maps.length - 1 && cur[1] == maps[0].length -1)
                return cur[2];
            
            for(int i=0;i<4;i++){
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];
                
                if(tx>-1&&ty>-1&&tx<maps.length&&ty<maps[0].length&& maps[tx][ty] == 1){
                    q.add(new int[]{tx,ty,cur[2]+1});
                    maps[tx][ty] = 0;
                }
            }
        }
        return answer;
    }
}