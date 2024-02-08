import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> s = new LinkedList<>();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < speeds.length; i++) {
            int period = (int) Math.ceil((100 - progresses[i])/(double) speeds[i]);
            if(!s.isEmpty() && s.peek() < period){
                arr.add(s.size());
                s.clear();
            }
            
            s.offer(period);
        }
        arr.add(s.size());
            
        return arr.stream().mapToInt(i->i).toArray();
    }
}