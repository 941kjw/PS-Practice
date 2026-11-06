class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        startday--;
        for(int i = 0; i < schedules.length; ++i){
            int deadline = toMinute(schedules[i]) + 10;
            int count = 0;
            for(int n = 0 ; n < 7; ++n){
                if(!isWeekDay(changeToDayOfWeek(startday,n)))
                    continue;
                
                int time = toMinute(timelogs[i][n]);
                
                if(deadline >= time)
                    ++count;                
            }
            
            if(count == 5)
                ++answer;
        }
        
        return answer;
    }
    
    private int toMinute(int time){
        return (time / 100) * 60 + (time % 100);
    }
    
    private int changeToDayOfWeek(int startday, int date){
        return (startday + date) % 7;
    }
    
    private boolean isWeekDay(int dayOfWeek){
        return 5 > dayOfWeek;
    }
}