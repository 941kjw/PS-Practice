import java.util.*;

class Solution { 
    
    class Job{
        public int priority;
        public int location;
        
        public Job(int p,int l){
            this.priority = p;
            this.location = l;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Deque<Job> dq = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
            dq.addLast(new Job(priorities[i], i));
        }
        
        while (!pq.isEmpty()) {
            int priority = pq.poll();
            while ( dq.peekFirst().priority != priority) {
                dq.addLast(dq.removeFirst());
            }
            answer++;
            if(dq.removeFirst().location == location)
                break;
        }

        return answer;
    }
}