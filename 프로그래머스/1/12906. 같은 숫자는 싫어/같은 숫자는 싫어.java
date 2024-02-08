import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        Stack<Integer> numStack = new Stack<>();
        numStack.push(arr[0]);
        for (int i : arr) {
            if (numStack.peek() != i) {
                numStack.push(i);
            }
        }
        answer = numStack.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}