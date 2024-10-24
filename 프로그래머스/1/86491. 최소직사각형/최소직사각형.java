import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int x = 0,y = 0;
        for(int i=0;i<sizes.length;i++){
            int tx = sizes[i][0];
            int ty = sizes[i][1];
            if(tx<ty){
                int temp = tx;
                tx = ty;
                ty = temp;
            }
            x = Math.max(x,tx);
            y = Math.max(y,ty);                
        }
        return answer = x*y;
    }
}