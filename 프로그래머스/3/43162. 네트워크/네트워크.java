import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i< n; i++){
            if(!visited[i]){
                q.add(i);
                answer++;
            }
            while(!q.isEmpty()){
                int cur = q.poll();
                visited[cur] = true;
                for(int j=0;j<n;j++){
                    if(computers[cur][j] == 1 && !visited[j]){
                        q.add(j);
                    }
                }
            }
        }
        return answer;
    }
}