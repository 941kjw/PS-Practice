
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int[][] board = new int[10][10];
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));		
		int testCount = Integer.parseInt(reader.readLine());
		StringBuilder stringBuilder = new StringBuilder();
		for(int testNumber= 1; testNumber <= testCount; testNumber++) {
			int length = Integer.parseInt(reader.readLine());
			for(int rowIdx = 0; rowIdx < length; rowIdx++) {
				for(int colIdx = 0; colIdx < length; colIdx++) {
					board[rowIdx][colIdx] = 0;
				}
			
			}
			int row = 0;
			int col = 0;
			int number = 1;
			int direction = 0;
			
			int endpointRow = length/2;
			int endpointCol = length % 2 == 1 ? length/2 : length/2 -1;
			board[0][0] = 1;
			while(true) {
				if(row == endpointRow && col == endpointCol)
					break;
				int nrow = row + dy[direction];
				int ncol = col + dx[direction];
				
				if(nrow > -1 && nrow < length && ncol < length && ncol > -1 && board[nrow][ncol]== 0 ) {
					row = nrow;
					col = ncol;
					board[row][col] = ++number;
				}else {
					direction = (direction + 1) % 4;
				}				
			}
			

			stringBuilder.append('#').append(testNumber).append('\n');
			
			for(int rowIdx = 0; rowIdx < length; rowIdx++) {
				for(int colIdx = 0; colIdx < length; colIdx++) {
					stringBuilder.append(board[rowIdx][colIdx]).append(' ');
				}
				stringBuilder.append('\n');
			}
		}
		
		System.out.print(stringBuilder);
		reader.close();	
	}
}
