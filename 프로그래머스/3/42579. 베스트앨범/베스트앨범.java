import java.util.*;
import java.util.stream.*;

class Solution {
    
    public class Music implements Comparable<Music>{
        public int played;
        public String genre;
        public int index;
        
        public Music(int played,String genre,int index){
            this.played = played;
            this.genre = genre;
            this.index = index;
        }
        
        public String getGenre(){
            return this.genre;
        }
        
        @Override
        public int compareTo(Music other){
            if(this.played == other.played)
                return this.index - other.index;
            return other.played - this.played;
            
        }
    }
  public int[] solution(String[] genres, int[] plays) {
               
        return IntStream.range(0,genres.length)
            .mapToObj(i->new Music(plays[i],genres[i],i))
            .collect(Collectors.groupingBy(Music::getGenre))
            .entrySet().stream()
            .sorted((a,b) -> sum(b.getValue()) - sum(a.getValue()))
            .flatMap(x -> x.getValue().stream().sorted().limit(2))
            .mapToInt(x->x.index).toArray();
    }
    
    private int sum(List<Music> values){
        return values.stream().mapToInt(i -> i.played).sum();
    }
}