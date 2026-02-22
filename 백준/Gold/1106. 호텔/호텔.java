import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;



    public static void main(String[] args) throws IOException {
        initReader();

        int minCost = getNumber();
        int cityCount = getNumber();

        int[] costs = new int[cityCount];
        int[] rewards = new int[cityCount];
        for(int i = 0 ; i < cityCount; ++i){

            costs[i] = getNumber();
            rewards[i] = getNumber();
        }

        int result = searchOptimal(minCost, cityCount,costs,rewards);

        System.out.println(result);
        clear();
    }

    private static int searchOptimal(int minCost, int cityCount, int[] costs,int[] rewards) {
        //dp -> cost내 만들 수 있는 최대 보상
        int[] dp = new int[100001];

        //각각 i번 도시에 홍보하는 경우를 시뮬레이션
        for(int i = 0; i < cityCount ; ++i){
            //j 코스트 내 얻을 수 있는 최대 보상
            for(int j = costs[i]; j < dp.length ; ++j){
                dp[j] = Math.max(dp[j] , dp[j - costs[i]] + rewards[i]);
            }
        }

        for(int i = 0; i< dp.length; ++i){
            if(dp[i] >= minCost){
                return i;
            }
        }

        return 0;
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
