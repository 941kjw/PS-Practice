import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean targetExists = false;
        
        for(String word : words){
            if(word.equals(target)){
                targetExists = true;
                break;
            }
        }
        
        if(!targetExists){
            return 0;
        }
        
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        q.offer(new Node(begin,0));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.word.equals(target)){
                return cur.depth;
            }
            
            for(int i=0; i< words.length; ++i){
                if(visited[i])
                    continue;
                
                if(canConvert(cur.word, words[i])){
                    visited[i] = true;
                    q.offer(new Node(words[i],cur.depth + 1));
                }
            }
        }
        
        
        return 0;        
    }
    
    private boolean canConvert(String a, String b){
        int diff = 0;
        
        for(int i = 0; i < a.length(); ++i){
            if(a.charAt(i) != b.charAt(i)){
                ++diff;
                
                if(diff > 1)
                    return false;
            }
        }
        
        return diff == 1;
    }
    
    class Node {
        String word;
        int depth;
        
        Node(String w , int d){
            word = w;
            depth = d;
        }
    }
}