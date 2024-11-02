import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        int min = 0,max=people.length -1;
        Arrays.sort(people);
        
        while(min <= max){
            if(people[min] + people[max] <= limit)
                min++;
            answer++;
            max--;
        }
        return answer;
    }
}