import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0;
        int jobIndex = 0;
        int end = 0;
        Arrays.sort(jobs,(o1,o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq =  new PriorityQueue<>((o1,o2) -> o1[1]-o2[1]);
        while(count < jobs.length){
            while(jobIndex < jobs.length && jobs[jobIndex][0] <= end)
                pq.add(jobs[jobIndex++]);
            if(pq.isEmpty())
                end = jobs[jobIndex][0];
            else{
                int[] temp = pq.poll();
                answer += temp[1] + end - temp[0];
                end += temp[1];
                count++;
            }
                
        }
        return (int) Math.floor(answer / jobs.length);
    }
}