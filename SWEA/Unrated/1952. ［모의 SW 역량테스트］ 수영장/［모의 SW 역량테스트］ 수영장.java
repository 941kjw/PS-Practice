import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 월별 사용 횟수가 주어지고, 각 이용권의 정보가 주어짐.
 * 이때, 각 이용권의 조합을 통해 어떻게 조합하는 것이 가장 적게 돈을 내는지 찾아내야함.
 * 
 * !! 주의 !!
 * 중간에 1년 이용권을 사는 멍청이는 없으므로 최초 초기화 시 최소값을 연간 이용권 가격으로 잡는다.
 * 
 * 1.입력으로 초기화. 이때, 최소값을 연간 이용권가격으로 설정.
 * 2.최저가 조합을 찾는 재귀 실행.
 *      2-1.만약 현재 합이 최소값을 이미 넘어섰다면 return.
 *      2-2.현재 달 수가 12월을 넘어섰다면 최솟값을 갱신한다. (11월에 3개월 이용권을 사는 경우)
 * 
 * 
 *
 */
public class Solution {

	static short[] planFeeInfo = new short[4];
	static short[] monthlyUsageInfo = new short[12];
	static short feeSum;
	static int[] minFee;

	static StringBuilder builder = new StringBuilder();

	static void init(StreamTokenizer tokenizer) throws IOException {

		for (int idx = 0; idx < planFeeInfo.length; idx++) {
			planFeeInfo[idx] = readShort(tokenizer);
		}

		minFee = new int[13];
		for (int idx = 0; idx < monthlyUsageInfo.length; idx++) {
			monthlyUsageInfo[idx] = readShort(tokenizer);
		}

	}

	static void findBestPlan() {

		for (int month = 1; month <= 12; ++month) {

			int ifDaily = minFee[month - 1] + monthlyUsageInfo[month - 1] * planFeeInfo[0];
			int ifMonthly = minFee[month - 1] + planFeeInfo[1];

			minFee[month] = Math.min(ifDaily, ifMonthly);

			if (month >= 3) {
				int ifTriple = minFee[month - 3] + planFeeInfo[2];
				minFee[month] = Math.min(ifTriple, minFee[month]);
			}
		}

		minFee[12] = Math.min(minFee[12], planFeeInfo[3]);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		short testCount = readShort(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			findBestPlan();

			builder.append('#').append(testNumber).append(' ').append(minFee[12]).append('\n');
		}
		System.out.println(builder);
	}

	static short readShort(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (short) tokenizer.nval;
	}
}