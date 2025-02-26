import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 목표 무게를 5와 3만으로 최소 갯수만큼만 사용하여 만들기.
 * 
 * 1.목표 무게 설정
 * 2.최초 5kg 봉투 갯수 설정
 * 3.5kg 봉투에 담은 무게를 제외한 나머지가 3의 배수가 될 때까지 5kg 봉투를 줄이면서 반복
 * 		3-1. 5kg봉투를 하나도 쓰지 않아도 안되면 -1 출력.
 * 4. 5kg 봉투와 3kg 봉투의 갯수의 합을 출력.
 * 
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		tokenizer.nextToken();
		int weight = (int) tokenizer.nval;

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
