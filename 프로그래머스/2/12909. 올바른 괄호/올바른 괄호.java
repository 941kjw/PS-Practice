import java.util.*;
import java.util.stream.*;

class Solution {
    boolean solution(String s) {
        Stack<String> st = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            st.push(String.valueOf(s.charAt(i)));
        }
        int count = 0;
        while(!st.isEmpty()){
            String c = st.pop();
            if(c.equals(")"))
                count++;
            else count--;
            if(count < 0)
                return false;
        }

        return count == 0;
    }
}