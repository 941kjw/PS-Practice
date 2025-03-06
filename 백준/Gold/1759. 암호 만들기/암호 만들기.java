import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

	static int pwLength;
	static int clueLength;

	static StringBuilder builder;

	static char[] clues;
	static char[] selected;

	static int MIN_VOWEL = 1;
	static int MIN_CONSONANTS = 2;

	static void init(StreamTokenizer tokenizer) throws IOException {
		pwLength = readInt(tokenizer);
		clueLength = readInt(tokenizer);

		clues = new char[clueLength];
		selected = new char[pwLength];

		for (int idx = 0; idx < clues.length; idx++) {
			clues[idx] = readChar(tokenizer);
		}

		Arrays.sort(clues);

	}

	static boolean checkWordValid() {
		int vowels = 0;
		int consonants = 0;

		for (char c : selected) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
				vowels++;
			else
				consonants++;
		}

		if (vowels >= MIN_VOWEL && consonants >= MIN_CONSONANTS)
			return true;

		return false;
	}

	static void makePw(int elementIdx, int selectedIdx) {
		if (selectedIdx == pwLength) {

			if (!checkWordValid())
				return;

			for (char word : selected) {
				builder.append(word);
			}
			builder.append('\n');
			return;
		}

		if (elementIdx == clueLength)
			return;

		selected[selectedIdx] = clues[elementIdx];
		makePw(elementIdx + 1, selectedIdx + 1);
		makePw(elementIdx + 1, selectedIdx);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		builder = new StringBuilder();
		init(tokenizer);

		makePw(0, 0);

		System.out.println(builder);
	}

	static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static char readChar(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval.charAt(0);
	}
}
