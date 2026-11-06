class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        String[] availablePronunciation = {"aya","ye","woo","ma"};
        
        for(String s : babbling){
            String cur = s;
            boolean available = true;
            String before = "";
            
            while(!cur.isEmpty()){
                boolean matched = false;
                
                for(String p : availablePronunciation){
                    if(cur.startsWith(p) && !before.equals(p)){
                        before = p;
                        cur = cur.substring(p.length());
                        matched = true;
                        break;
                    }
                }
                
                if(!matched){
                    available = false;
                    break;
                }
            }
            
            if(available)
                ++answer;
        }
        
        return answer;
    }
}