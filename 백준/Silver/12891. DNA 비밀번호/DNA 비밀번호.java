import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int A = 0;
	static final int C = 1;
	static final int G = 2;
	static final int T = 3;

	static String DNAString;
	static int passwordLength;
	static int dnaLength;
	static int passwordCount;

	static int[] letterCount;
	static final int[] DNAminCount = new int[4];

	static void init(BufferedReader reader) throws IOException {
		String[] input = reader.readLine().split(" ");

		passwordLength = Integer.parseInt(input[1]);
		passwordCount = 0;
		DNAString = reader.readLine();
		dnaLength = DNAString.length();

		letterCount = new int[4];

		input = reader.readLine().split(" ");

		for (int idx = 0; idx < 4; idx++) {
			DNAminCount[idx] = Integer.parseInt(input[idx]);
		}
	}

	static void keygen() {
		int startIdx = 0;
		int endIdx = 0 + passwordLength;

		for (int idx = startIdx; idx < endIdx; idx++) {
			letterCount[getLetterIdx(DNAString.charAt(idx))]++;
		}
		checkPassword();
		while (endIdx < dnaLength) {

			letterCount[getLetterIdx(DNAString.charAt(startIdx++))]--;
			letterCount[getLetterIdx(DNAString.charAt(endIdx++))]++;

			checkPassword();

		}
	}

	static void checkPassword() {
		boolean valid = true;
		for (int idx = 0; idx < 4; idx++) {
			if (letterCount[idx] < DNAminCount[idx])
				valid = false;
		}

		if (valid)
			passwordCount++;

	}

	static int getLetterIdx(char letter) {
		switch (letter) {
			case 'A':
				return A;
			case 'C':
				return C;
			case 'G':
				return G;
			case 'T':
				return T;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		init(reader);
		keygen();
		System.out.println(passwordCount);
		reader.close();
	}

}
