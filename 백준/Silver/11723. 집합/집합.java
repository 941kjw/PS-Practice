import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int set;
	static StringBuilder builder;

	static void add(int arg) {
		if (!check(arg))
			set |= (1 << arg);
	}

	static boolean check(int arg) {
		return (set & (1 << arg)) != 0;
	}

	static void checkAndOutput(int arg) {
		boolean isPresent = check(arg);
		builder.append(isPresent ? 1 : 0).append('\n');
	}

	static void remove(int arg) {
		if (check(arg))
			set &= ~(1 << arg);
	}

	static void toggle(int arg) {
		set ^= (1 << arg);
	}

	static void empty() {
		set &= 0;
	}

	static void all() {
		set |= -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		int commandCount = Integer.parseInt(reader.readLine());
		builder = new StringBuilder();
		for (int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			String command = tokenizer.nextToken();
			int arg = 0;
			if (!command.equals("all") && !command.equals("empty")) {
				arg = Integer.parseInt(tokenizer.nextToken());
			}

			if (command.equals("add")) {
				add(arg);
			} else if (command.equals("remove")) {
				remove(arg);
			} else if (command.equals("check")) {
				checkAndOutput(arg);
			} else if (command.equals("toggle")) {
				toggle(arg);
			} else if (command.equals("all")) {
				all();
			} else if (command.equals("empty")) {
				empty();
			}
		}

		System.out.println(builder);
	}
}
