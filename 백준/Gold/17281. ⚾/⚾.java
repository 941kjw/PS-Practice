import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int inningCount;
	static int max = Integer.MIN_VALUE;
	static boolean[] used = new boolean[9];
	static int[][] batterHitsOfInning;
	static int[] lineup = new int[9];

	static void init(BufferedReader reader) throws IOException {
		inningCount = Integer.parseInt(reader.readLine());
		batterHitsOfInning = new int[inningCount][9];
		StringTokenizer tokenizer;
		for (int inningIdx = 0; inningIdx < inningCount; inningIdx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int batterNumber = 0; batterNumber < 9; batterNumber++) {
				batterHitsOfInning[inningIdx][batterNumber] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}

	static void tryAllLineups(int selectedIdx) {
		if (selectedIdx == 9) {
			int simulatedScore = simulateMatch();
			max = Math.max(max, simulatedScore);
			return;
		}

		if (selectedIdx == 3) {
			tryAllLineups(selectedIdx + 1);
		}

		for (int idx = 1; idx < 9; idx++) {
			if (!used[idx] && selectedIdx != 3) {
				used[idx] = true;
				lineup[selectedIdx] = idx;
				tryAllLineups(selectedIdx + 1);
				used[idx] = false;
			}
		}
	}

	static int simulateMatch() {
		int score = 0;
		int currentBatter = 0;
		int[] base = new int[4];
		int inning = 0;
		int out = 0;

		while (inning < inningCount) {
			//아웃 ㅡㅡ
			if (batterHitsOfInning[inning][lineup[currentBatter]] == 0) {
				out++;
				if (out == 3) {
					inning++;
					out = 0;
					for (int idx = 0; idx < base.length; idx++) {
						base[idx] = 0;
					}
					currentBatter = (currentBatter + 1) % 9;
					continue;
				}
			}

			//안타!
			if (batterHitsOfInning[inning][lineup[currentBatter]] == 1) {
				score += base[3];
				base[3] = base[2];
				base[2] = base[1];
				base[1] = 1;
			}

			//2루타!!
			if (batterHitsOfInning[inning][lineup[currentBatter]] == 2) {
				score += (base[3] + base[2]);
				base[3] = base[1];
				base[2] = 1;
				base[1] = 0;
			}

			//3루타!!!
			if (batterHitsOfInning[inning][lineup[currentBatter]] == 3) {
				score += (base[3] + base[2] + base[1]);
				base[3] = 1;
				base[2] = 0;
				base[1] = 0;
			}

			if (batterHitsOfInning[inning][lineup[currentBatter]] == 4) {
				score += (base[3] + base[2] + base[1] + 1);
				for (int idx = 0; idx < base.length; idx++) {
					base[idx] = 0;
				}
			}
			currentBatter = (currentBatter + 1) % 9;
		}

		return score;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		init(reader);

		tryAllLineups(0);

		System.out.println(max);
	}
}
