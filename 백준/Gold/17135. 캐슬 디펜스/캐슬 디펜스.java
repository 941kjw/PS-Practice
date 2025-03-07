import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

	static int rowCount;
	static int colCount;
	static int range;

	static int[][] simulationField;
	static int[][] originalField;

	static int[] archerIdx;

	static int maxKillCount;
	static int turn;

	static int[] dy = { 0, -1, 0 };
	static int[] dx = { -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		rowCount = read(tokenizer);
		colCount = read(tokenizer);
		range = read(tokenizer);
		maxKillCount = 0;

		archerIdx = new int[3];
		simulationField = new int[rowCount + 1][colCount];
		originalField = new int[rowCount + 1][colCount];

		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				int value = read(tokenizer);
				originalField[row][col] = value;
				simulationField[row][col] = value;
			}
		}

	}

	//공격 시도하기
	public static int attack(int[][] status, int distance, int row, int archerCol) {

		int aimedRow;
		//궁수 좌~우로 현재 영점거리만큼
		for (int aimedCol = archerCol - distance; aimedCol <= archerCol + distance; aimedCol++) {
			//현재 조준 거리에서 최대한 조준 가능한 곳의 행 좌표
			aimedRow = row - (distance - Math.abs(aimedCol - archerCol));

//			printMap(aimedRow, aimedCol);
			//조준점이 벗어나는 경우
			if (aimedRow < 0 || aimedRow >= row || aimedCol < 0 || aimedCol >= colCount)
				continue;

			//적이 없을 경우
			if (simulationField[aimedRow][aimedCol] == 0)
				continue;

			//사격한 적 없음.
			if (status[aimedRow][aimedCol] == 0) {
				status[aimedRow][aimedCol] = row;
				return 1;

				//동일 라인의 다른 사수가 사격함.
			} else if (status[aimedRow][aimedCol] == row)
				return 0;
		}

		//다른 라인에서 사격??
		return -1;
	}

	static void printMap(int r, int c) {
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {

				if (r == row && c == col) {
					System.out.print("X ");
					continue;
				}

				System.out.print(simulationField[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static int simulate() {
		int count = 0;
		int[][] status = new int[rowCount][colCount];

		//가까운 것부터 처리
		for (int row = rowCount; row > 0; row--) {
			//각 사수
			for (int archerCol : archerIdx) {
				//거리별 조준
				for (int rangeIdx = 1; rangeIdx <= range; rangeIdx++) {
					//공격 결과 합
					int cnt = attack(status, rangeIdx, row, archerCol);
					if (cnt < 0)
						continue;
					count += cnt;
					break;
				}
			}
		}

		return count;
	}

	static void advanceEnemy() {
		for (int row = rowCount - 1; row > -1; row--) {
			for (int col = 0; col < colCount; col++) {
				simulationField[row + 1][col] = simulationField[row][col];
			}
		}

		Arrays.fill(simulationField[0], 0);
	}

	static void placeArcher(int elementIdx, int selectedCount) {
		if (selectedCount == 3) {
			int killCount = simulate();
			maxKillCount = Math.max(killCount, maxKillCount);
			restoreMap();
			return;
		}

		if (elementIdx == colCount)
			return;

		archerIdx[selectedCount] = elementIdx;
		placeArcher(elementIdx + 1, selectedCount + 1);

		placeArcher(elementIdx + 1, selectedCount);
	}

	static void restoreMap() {
		for (int row = rowCount; row > -1; row--) {
			for (int col = 0; col < colCount; col++) {
				simulationField[row][col] = originalField[row][col];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		placeArcher(0, 0);

		System.out.println(maxKillCount);
	}

	static int getDistance(int row1, int col1, int row2, int col2) {
		return Math.abs(row1 - row2) + Math.abs(col1 - col2);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
