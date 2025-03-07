import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean[] used;
	static int elementCount;
	static int selectCount;
	static char[] answer;
	static StringBuilder builder = new StringBuilder();
	private static void permutation(int selectedIdx) throws IOException {
		if(selectedIdx == selectCount) {
			builder.append(answer);
			return;
		}
		
		for(int idx = 1; idx <= elementCount; idx++) {
			if(!used[idx]) {			
				used[idx]= true; 
				answer[2*selectedIdx] = (char) (idx + '0');
				permutation(selectedIdx+1);
				used[idx]= false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
		
		elementCount = Integer.parseInt(input[0]);
		selectCount = Integer.parseInt(input[1]);

		answer = new char[2 * selectCount];
		for(int i= 1;i < selectCount; i++)
			answer[2*i-1] = ' ';
		answer[2*selectCount - 1] = '\n';
		used = new boolean[elementCount + 1];		
		permutation(0);
		System.out.println(builder);
	}
}
