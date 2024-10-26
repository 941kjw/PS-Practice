class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(-1,0,target,numbers);
        return answer;
    }
    
    private void dfs(int cur,int sum,int target, int[] numbers){
        if(cur == numbers.length - 1){
            if(sum == target)
                answer++;
            return;
        }
        cur++;
        dfs(cur, sum + numbers[cur] , target,numbers);
        dfs(cur, sum - numbers[cur] , target,numbers);
    }
}