import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	static short[] planFeeInfo = new short[4];
	static short[] monthlyUsageInfo = new short[12];
	static short feeSum;
	static int minFee;
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer tokenizer = new StreamTokenizer(reader);

	static StringBuilder builder = new StringBuilder();

	static void init() throws IOException {

		for (int idx = 0; idx < planFeeInfo.length; idx++) {
			planFeeInfo[idx] = readShort();
		}

		minFee = planFeeInfo[3];
		for (int idx = 0; idx < monthlyUsageInfo.length; idx++) {
			monthlyUsageInfo[idx] = readShort();
		}
	}

	static void findBestPlan(int currentSum, int month) {
		if (currentSum > minFee)
			return;

		if (month >= 12) {
			minFee = Math.min(minFee, currentSum);
			return;
		}

		findBestPlan(currentSum + monthlyUsageInfo[month] * planFeeInfo[0], month + 1);
		findBestPlan(currentSum + planFeeInfo[1], month + 1);
		findBestPlan(currentSum + planFeeInfo[2], month + 3);
	}

	public static void main(String[] args) throws IOException {

		short testCount = readShort();

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init();
			findBestPlan(0, 0);

			builder.append('#').append(testNumber).append(' ').append(minFee).append('\n');
		}
		System.out.println(builder);
	}

	static short readShort() throws IOException {
		tokenizer.nextToken();
		return (short) tokenizer.nval;
	}
}
