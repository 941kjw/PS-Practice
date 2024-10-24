class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        int x = 0,y = 0;
        for(int i=3;i<=area/i;i++){
            if(area % i == 0){
                y = i;
                x = area / i;
                if((x-2)*(y-2) == yellow)
                    break;
            }
        }
        answer[0] = Math.max(x,y);
        answer[1] = Math.min(x,y);
        return answer;
    }
}