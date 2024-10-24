import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] one = {1,2,3,4,5};
        int ol = one.length;
        int[] two = {2,1,2,3,2,4,2,5};
        int tl = two.length;
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        int hl = three.length;
        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        for(int i =0; i<answers.length; i++){
            if(one[i%ol] == answers[i])
                score1++;
            if(two[i%tl] == answers[i])
                score2++;
            if(three[i%hl] == answers[i])
                score3++;
        }
        int result = Math.max(score1,Math.max(score2,score3));
        if(result == score1) answer.add(1);
        if(result == score2) answer.add(2);
        if(result == score3) answer.add(3);
        int cnt = 0;
        int[] r = new int[answer.size()];
        for(int i : answer)
            r[cnt++] = i;
        
        return r;
    }
}