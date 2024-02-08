import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> s = new LinkedList<>();
        for (int i = 0; i < speeds.length; i++) {
            s.add((100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i]
                    : (100 - progresses[i]) / speeds[i] + 1);
        }

        List<Integer> arr = new ArrayList<>();
        
        while (!s.isEmpty()) {
            int num = 1;
            int former = s.remove();
            while (s.peek() != null && s.peek() <= former) {
                s.remove();
                num++;
            }
            arr.add(num);
        }
        answer = arr.stream().mapToInt(i->i).toArray();
            
        return answer;
    }
}