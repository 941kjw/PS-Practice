import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1번 자리, 2번 자리, 3번자리 ... 모두 다 소수여야 한다.
 *
 * 소수의 특징을 잘 관찰해보면....
 * 한 자리 소수는 2,3,5,7이 있다.
 * 두자리 소수는  11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97가 있다.
 * 두 자리 소수 중 '신기한' 소수를 걸러보면,
 * 23,29,31,37,53,59,71,73,79가 있다.
 * 세자리는 계산하기 너무 많으니 제외하고
 * 4자리는 예시 출력을 보면,
 * 2333
 * 2339
 * 2393
 * 2399
 * 2939
 * 3119
 * 3137
 * 3733
 * 3739
 * 3793
 * 3797
 * 5939
 * 7193
 * 7331
 * 7333
 * 7393
 * 잘 보면 첫째 자리는 2,3,5,7이고, 둘째 자리부터는 1,3,7,9 안에서 논다.
 * 따라서, 2,3,5,7을 베이스로 1,3,7,9를 붙여가보면 어떨까...
 *
 *  1. 입력을 통한 초기화
 *  2. 베이스 숫자 2,3,5,7에 대해 모든 조합을 만들어본다.
 *      2-1. 전부 골랐다면, 출력하고 반환
 *      2-2. 뒤에 붙일수 있는 1,3,7,9를 순회
 *          2-2-a. 하나씩 붙여보고, 소수이면 다음 재귀로 진행.
 *
 */
public class Main {
    static int radix;
    static int[] base = {2, 3, 5, 7};
    static int[] element = {1, 3, 7, 9};

    /**
     * 소수인지 판별하는 메소드
     * 1은 소수가 아니다.
     * 2,3은 소수이지만, (int)sqrt(n)가 1이므로 제곱근을 이용한 계산이 어렵다..
     * 반절까지만 확인해도 소수인지 알 수 있다.
     */
    static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void appendAndCheck(int selectedIdx, int current) {
        if (selectedIdx == radix) {
            System.out.println(current);
            return;
        }
        for (int num : element) {
            int currentNumber = current * 10 + num;
            if (isPrime(currentNumber)) {
                appendAndCheck(selectedIdx + 1, currentNumber);
            }
        }
    }

    static void init(BufferedReader reader) throws IOException {
        radix = Integer.parseInt(reader.readLine());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        init(reader);

        for (int idx = 0; idx < 4; idx++) {
            appendAndCheck(1, base[idx]);
        }

        reader.close();
    }
}
