import java.util.*;

class Solution {
    public int findParent(int[] parent, int node){
        if(parent[node] == node)
            return node;
        return findParent(parent,parent[node]);
    }
    
    
    public void union(int[] parent, int n1, int n2){
        int p1 = findParent(parent,n1);
        int p2 = findParent(parent,n2);
        if(p1 < p2)
            parent[p2] = p1;
        else
            parent[p1] = p2;
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        for(int i = 0; i< n;i++)
            parent[i] = i;
        Arrays.sort(costs,(o1,o2) -> o1[2]-o2[2]);
        for(int i=0; i<costs.length;i++){
            if(findParent(parent,costs[i][0]) != findParent(parent,costs[i][1])){
                answer+= costs[i][2];
                union(parent,costs[i][0],costs[i][1]);
            }
        }
        return answer;
    }
}