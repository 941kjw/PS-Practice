import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> bq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> sq = new PriorityQueue<>();
        
        for(String s : operations){
            String[] temp = s.split(" ");
            String op = temp[0];
            Integer num = Integer.parseInt(temp[1]);
            if(op.equals("I")){
                bq.add(num);
                sq.add(num);
            }else if(op.equals("D")){         
                if(num > 0 && !bq.isEmpty()){
                    sq.remove(bq.poll());
                } else if(num < 0 && !sq.isEmpty()){
                    bq.remove(sq.poll());
                }
            }
            
            
        }
        answer[0] = bq.peek() != null ? bq.peek() : 0;
        answer[1] = sq.peek() != null ? sq.peek() : 0;
        return answer;
    }
}