import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;



    public static void main(String[] args) throws IOException {
        initReader();

        int targetNumber = getNumber();
        int result = findMinimum(targetNumber);

        System.out.println(result);
        clear();
    }

    private static int findMinimum(int targetNumber){
        int[] dp = new int[targetNumber + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        //
        int idx = 0;
        int min;

        //1부터 목표 값까지 최소 개수를 계산해보기
        for(int i = 1; i <= targetNumber; i++){            
            //다음 육각수를 찾아서
            int next = calcSixNumbers(idx + 1);
            //딱 맞으면 1개만 더하면 됨.
            if(i == next){
                dp[i] = 1;
                continue;
            }
            //육각수가 더 작으면 다음 인덱스로 증가
            if(i > next)
                idx++;
            
            //위 -> 아래로 내려오면서, 최소 개수를 갱신.
            for(int j = idx; j >= 0; --j){
                min = calcSixNumbers(j);
                if(dp[min] == Integer.MAX_VALUE)
                    continue;
                dp[i] = Math.min(dp[i - min] + dp[min], dp[i]);
            }
        }

        return dp[targetNumber];
    }
    
    //육각수의 점 개수는 n(2n - 1)이다.
   private static int calcSixNumbers(int n){
        if(n <= 0)
            return 0;
        return 2 * n * n - n;
   }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void initReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    private static int getNumber() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
