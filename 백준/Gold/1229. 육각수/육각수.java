import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    private static List<Integer> hex = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initReader();

        int targetNumber = getNumber();

        initHex(targetNumber);
        int result = findMinimum(targetNumber);

        System.out.println(result);
        clear();
    }

    private static void initHex(int max) {
        hex.add(0);
        for(int k = 1;;++k){
            int i = k * (2 * k - 1);
            if(i > max)
                break;
            hex.add(i);
        }
    }

    private static int findMinimum(int targetNumber){
        int[] dp = new int[targetNumber + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        int idx = 0;
        //1부터 목표 값까지 최소 개수를 계산해보기
        for (int i = 1; i <= targetNumber; i++) {
            // idx를 올바르게 갱신
            while (calcSixNumbers(idx + 1) <= i) idx++;

            // 딱 육각수면 바로 1
            if (calcSixNumbers(idx) == i) {
                dp[i] = 1;
                continue;
            }

            for (int j = idx; j >= 1; --j) {
                int min = calcSixNumbers(j);
                if (dp[min] == Integer.MAX_VALUE) continue;
                if (dp[i - min] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i - min] + dp[min], dp[i]);
            }
        }

        return dp[targetNumber];
    }

    //육각수의 점 개수는 n(2n - 1)이다.
   private static int calcSixNumbers(int n){
        if(n <= 0)
            return 0;
       if (n >= hex.size())
           return Integer.MAX_VALUE;
       return hex.get(n);
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
