import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int weight = scanner.nextInt();

		int five = weight / 5;

		while ((weight - five * 5) % 3 != 0) {
			five--;
			if (five < 0) {
				System.out.println(-1);
				return;
			}
		}

		int three = (weight - five * 5) / 3;
		System.out.println(five + three);
	}
}
