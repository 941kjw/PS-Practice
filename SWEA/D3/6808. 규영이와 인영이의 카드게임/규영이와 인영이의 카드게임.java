import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int[] myCards = new int[9];
	static int[] opponentCards = new int[9];
	static int[] order = new int[9];
	static boolean[] used = new boolean[9];
	static boolean[] owned;
	static int lose;
	static int win;
	
	private static void simulate(int selectedIdx) {
		if(selectedIdx == 9) {
			int mySum = 0;
			int oppoSum = 0;
			
			for(int idx = 0; idx < 9; idx++) {
				if(order[idx] < myCards[idx] )
					mySum += (order[idx]+ myCards[idx]);
				else
					oppoSum += (order[idx]+ myCards[idx]);
			}
			if(mySum > oppoSum)
				win++;
			else if(mySum < oppoSum)
				lose++;
			return;
		}
		
		for(int idx = 0; idx < 9;idx++) {
			if(used[idx])
				continue;
			
			used[idx]= true;
			order[selectedIdx] = opponentCards[idx];
			simulate(selectedIdx + 1);
			used[idx]= false; 
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int testCount = Integer.parseInt(reader.readLine());
		StringBuilder builder = new StringBuilder();
		for(int currentTest = 1; currentTest <= testCount; currentTest++) {
			owned = new boolean[19];	
			lose = 0;
			win = 0;
			String[] inputs = reader.readLine().split(" ");
			
			for(int idx = 0; idx < 9; idx++) {
				myCards[idx]= Integer.parseInt(inputs[idx]);
				owned[myCards[idx]] = true;
			}
			
			int cardIdx = 0;			
			for(int idx = 1; idx < 19; idx++) {
				if(owned[idx]== false)
					opponentCards[cardIdx++] = idx;
			}
			simulate(0);
			builder.append('#').append(currentTest).append(' ').append(win).append(' ').append(lose).append('\n');
			
		}
		System.out.print(builder);
	}
}
