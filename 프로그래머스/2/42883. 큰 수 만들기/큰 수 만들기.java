class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int len = number.length() - k;
        int start = 0;
        
        while(start < number.length() && answer.length() != len){
            int notConfirmed = k + answer.length() + 1;
            int max = 0;
            for(int i = start; i< notConfirmed;i++){
                if(max < number.charAt(i) - '0'){
                    max = number.charAt(i) - '0';
                    start = i+1;
                }
            }
            answer.append(Integer.toString(max));
        }
        return answer.toString();
    }
}