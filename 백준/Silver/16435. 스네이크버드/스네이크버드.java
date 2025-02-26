import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 
 * 과일들을 먹으면서 길이가 1 늘어나는 뱀이 있다.
 * 이 뱀이 더 이상 클 수 없을때 길이를 찾자.
 * 뱀은 한 방향으로만 이동할 수 있는건 아니다.
 * 
 * 1. 입력 초기화. 이때, 과일 높이를 오름차순으로 정렬한다.
 * 2. 앞에서부터 자기 자신보다 작거나 같은 높이의 과일을 전부 먹어간다. (이때,뱀 길이가 현재 과일 높이와 같은데, 다음 과일 높이와의 간격이 2 이상이면 성장이 중단된다.)
 * 3. 길이 출력.
 *
 */
public class Main {

	static short length;
	static short[] fruits;

	static void init(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		int fruitCount = (int) tokenizer.nval;
		tokenizer.nextToken();
		length = (short) tokenizer.nval;
		fruits = new short[fruitCount];
		for (int idx = 0; idx < fruits.length; idx++) {
			tokenizer.nextToken();
			fruits[idx] = (short) tokenizer.nval;
		}

		Arrays.sort(fruits);
	}

	static void eat() {
		for (int idx = 0; idx < fruits.length; idx++) {
			if (fruits[idx] <= length) {
				length++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		init(tokenizer);

		eat();

		System.out.println(length);
	}
}
