import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static Meeting[] queue;

	static class Meeting implements Comparable <Meeting> {

		private int startTime;
		private int endTime;

		public Meeting(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public boolean isAvailable(int time) {
			return this.startTime >= time ? true : false;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.endTime > o.endTime)
				return 1;
			else if (this.endTime < o.endTime)
				return -1;
			else {
				if (this.startTime > o.startTime)
					return 1;
				return -1;
			}
		}

	}

	static void init(BufferedReader reader) throws IOException {
		int meetingCount = Integer.parseInt(reader.readLine());
		queue = new Meeting[meetingCount];
		StringTokenizer tokenizer;

		for (int idx = 0; idx < queue.length; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startTime = Integer.parseInt(tokenizer.nextToken());
			int endTime = Integer.parseInt(tokenizer.nextToken());

			queue[idx] = new Meeting(startTime, endTime);
		}

		Arrays.sort(queue);
	}

	static int find() {
		int count = 0;
		int currentTime = 0;

		for (int idx = 0; idx < queue.length; idx++) {
			if (queue[idx].isAvailable(currentTime)) {
				currentTime = queue[idx].endTime;
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);

		System.out.println(find());
	}

}
