import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 한 개의 회의실에서 최대한 많은 회의를 진행할 때,그 개수를 찾자.
 * 
 * 1.입력을 통한 초기화
 * 2.
 * 
 *
 */
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

	static void init(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		int meetingCount = (int) tokenizer.nval;
		queue = new Meeting[meetingCount];

		for (int idx = 0; idx < queue.length; idx++) {
			tokenizer.nextToken();
			int startTime = (int) tokenizer.nval;
			tokenizer.nextToken();
			int endTime = (int) tokenizer.nval;

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
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		init(tokenizer);

		System.out.println(find());
	}

}
