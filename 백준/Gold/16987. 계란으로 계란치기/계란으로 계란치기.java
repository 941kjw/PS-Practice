import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static short[] weightInfo = new short[8];
    static short[] durabilityInfo = new short[8];
    static short eggCount;
    static int max = 0;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer tokenizer = new StreamTokenizer(reader);

    static void init() throws IOException {
        eggCount = readShort();
        for (int idx = 0; idx < eggCount; idx++) {
            durabilityInfo[idx] = readShort();
            weightInfo[idx] = readShort();
        }
    }

    static void breakEggsWith(int eggIdx) {
        if (eggIdx == eggCount) {
            int brokenEggCount = 0;
            for (int idx = 0; idx < eggCount; idx++) {
                if (durabilityInfo[idx] <= 0) {
                    brokenEggCount++;
                }
            }
            max = Math.max(max, brokenEggCount);
            return;
        }

        if (durabilityInfo[eggIdx] <= 0) {
            breakEggsWith(eggIdx + 1);
            return;
        }

        boolean strokeSomething = false;
        for (int idx = 0; idx < eggCount; idx++) {
            if (eggIdx != idx && durabilityInfo[idx] > 0) {
                durabilityInfo[idx] -= weightInfo[eggIdx];
                durabilityInfo[eggIdx] -= weightInfo[idx];

                strokeSomething = true;
                breakEggsWith(eggIdx + 1);

                durabilityInfo[idx] += weightInfo[eggIdx];
                durabilityInfo[eggIdx] += weightInfo[idx];
            }
        }

        if (!strokeSomething) {
            breakEggsWith(eggIdx + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        init();
        breakEggsWith(0);
        System.out.println(max);
    }

    static short readShort() throws IOException {
        tokenizer.nextToken();
        return (short) tokenizer.nval;
    }
}