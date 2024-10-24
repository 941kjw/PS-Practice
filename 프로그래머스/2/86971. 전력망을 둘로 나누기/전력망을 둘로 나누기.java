import java.util.ArrayList;
 
class Solution {
    boolean[][] graph;
    int min;
 
    public int solution(int n, int[][] wires) {
        graph = new boolean[n+1][n+1];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1][v2] = true;
            graph[v2][v1] = true;
        }
 
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
 
            boolean[] visited = new boolean[n + 1];
 
            graph[v1][v2] = false;
            graph[v2][v1] = false;
 
            int cnt = dfs(1,n, visited);
            min = Math.min(min, Math.abs(2*cnt -n));

            graph[v1][v2] = true;
            graph[v2][v1] = true;
        }
 
        return min;
    }
 
    private int dfs(int v,int n,boolean[] visited) {
        visited[v] = true;
        int cnt = 1;
        
        for(int i=1;i<=n;i++){
            if(graph[v][i] && !visited[i])
                cnt+= dfs(i,n,visited);
        }     
 
        return cnt;
    }
}