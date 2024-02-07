import java.util.*;
import java.util.Map.Entry;

class Solution {
  public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String,Integer> map = new HashMap<>();
        HashMap<Integer,Integer> map2 = new HashMap<>();


        for(int i=0;i<genres.length;i++)    {
            map.put(genres[i], map.getOrDefault(genres[i],0) + plays[i]);
        }

        ArrayList<String> lists =new ArrayList<>(map.keySet());

        lists.sort(((o1,o2) -> map.get(o2) - map.get(o1)));

        ArrayList<Integer> answerList = new ArrayList<>();

        lists.forEach(li ->{
            HashMap<Integer,Integer> playsMap = new HashMap<>();
            for(int i=0;i<genres.length;i++){
                if(li.equals(genres[i]))
                    playsMap.put(i,plays[i]);
            }

            ArrayList<Integer> playsList = new ArrayList<>(playsMap.keySet());
            playsList.sort((o1,o2) -> playsMap.get(o2) - playsMap.get(o1));

            answerList.add(playsList.get(0));
            if(playsList.size() != 1)
                answerList.add(playsList.get(1));
        });

        answer = new int[answerList.size()];

        for (int idx = 0; idx < answerList.size(); idx++) {
            answer[idx] = answerList.get(idx);
        }

        return answer;
    }
}