import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	static int length;
	static int targetTime;
	static int groupCount;

	static MicroOrganism head;
	static MicroOrganism tail;

	static int[][] countMap;

	static int microOrganismSum;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);

			simulate();
			builder.append('#').append(testNumber).append(' ').append(microOrganismSum).append('\n');
		}

		System.out.println(builder);
	}

	static void init(StreamTokenizer tokenizer) throws IOException {
		length = read(tokenizer);
		targetTime = read(tokenizer);
		groupCount = read(tokenizer);

		countMap = new int[length][length];
		head = null;
		tail = null;
		microOrganismSum = 0;

		for (int idx = 0; idx < groupCount; ++idx) {
			int row = read(tokenizer);
			int col = read(tokenizer);
			int amount = read(tokenizer);
			int direction = read(tokenizer) - 1;
			if (head == null) {
				head = new MicroOrganism(row, col, amount, direction);
				tail = head;
			} else {
				tail.attach(new MicroOrganism(row, col, amount, direction));
				tail = tail.next;
			}
		}
	}

	static void simulate() {
		for (int time = 0; time < targetTime; ++time) {
			updateAll();

			checkCollision();

			//removeDead();
		}
		getSum();
	}

	static void updateAll() {
		MicroOrganism current = head;

		while (current != null) {
			current.update();

			if (current.isDead()) {
				current = current.quitSelf();
			} else {
				countMap[current.row][current.col]++;
				current = current.next;
			}
		}
	}

	static void removeDead() {
		MicroOrganism current = head;
		while (current != null) {
			MicroOrganism next = current.next;
			if (current.isDead()) {
				current.removeSelf();
			}
			current = next;
		}
	}

	static void getSum() {
		MicroOrganism iterator = head;
		while (iterator != null) {
			microOrganismSum += iterator.amount;
			iterator = iterator.next;
		}
	}

	static void checkCollision() {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (countMap[row][col] >= 2) {
					handleCollision(row, col);
				}
				countMap[row][col] = 0;
			}
		}
	}

	static void handleCollision(int row, int col) {
		MicroOrganism current = head;
		while (current != null) {
			if (current.row == row && current.col == col) {
				break;
			}
			current = current.next;
		}

		int max = current.amount;

		MicroOrganism other = current.next;

		while (other != null) {
			if (other.row == row && other.col == col) {
				current.amount += other.amount;

				if (max < other.amount) {
					current.direction = other.direction;
					max = other.amount;
				}
				other.amount = 0;
			}

			if (other.isDead())
				other = other.quitSelf();
			else
				other = other.next;
		}
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static class MicroOrganism {
		int row, col;
		int amount;
		int direction;

		MicroOrganism before;
		MicroOrganism next;

		public MicroOrganism(int row, int col, int amount, int direction) {
			this.row = row;
			this.col = col;
			this.amount = amount;
			this.direction = direction;
			this.before = null;
			this.next = null;
		}

		void attach(MicroOrganism other) {
			this.next = other;
			other.before = this;
		}

		void update() {
			if (isDead()) {
				return;
			}

			this.row += dy[direction];
			this.col += dx[direction];

			if (this.row == 0 || this.row == length - 1 || this.col == 0 || this.col == length - 1) {
				this.direction ^= 1;
				this.amount /= 2;
			}
		}

		boolean isCollided(MicroOrganism other) {
			return (this.row == other.row) && (this.col == other.col);
		}

		boolean isDead() {
			return this.amount == 0;
		}

		int posHash() {
			return this.row * 1000 + this.col;
		}

		MicroOrganism quitSelf() {
			removeSelf();
			return this.next;
		}

		void removeSelf() {
			if (this != head) {
				this.before.next = this.next;
			} else {
				this.next.before = null;
				head = next;
			}

			if (this != tail) {
				this.next.before = this.before;
			} else {
				this.before.next = null;
				tail = this.before;
			}
		}
	}
}
