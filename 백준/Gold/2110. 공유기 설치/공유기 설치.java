import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

	
	static int[] houses;
	
	static int houseCount;
	static int gasCount;
	static int min;
	
	static void init(StreamTokenizer st) throws IOException{
		
		houseCount = read(st);
		gasCount = read(st);
		min = 0;
		
		houses = new int[houseCount];
		
		for (int idx = 0; idx < houses.length; idx++) {
			houses[idx] = read(st);
		}		
		Arrays.sort(houses);
	}
	
	static int read(StreamTokenizer st) throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static void find() {
		int start = 1;
		int end = houses[houseCount-1] - houses[0];
		
		while(start <= end) {
			int range = (start + end) / 2;
			
			int count = 1;
			int prev = houses[0];
			
			for (int idx = 0; idx < houses.length; idx++) {
				if(houses[idx] - prev >= range) {
					++count;
					prev = houses[idx];
				}
			}
			
			if(count >= gasCount) {
				min = Math.max(min, range);
				start = range + 1;
			}else {
				end = range - 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(r);
		
		init(st);
		find();
		
		System.out.println(min);
	}
	
}
