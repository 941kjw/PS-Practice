import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

    static Egg[] eggs = new Egg[8];
    static int eggCount;
    static int max = 0;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer tokenizer = new StreamTokenizer(reader);

    static void init() throws IOException {
        eggCount = read();
        for (int idx = 0; idx < eggCount; idx++) {
            eggs[idx] = new Egg(read(), read());
        }
    }

    static void breakEggsWith(int eggIdx) {
        if (eggIdx == eggCount) {
            int brokenEggCount = 0;
            for (int idx = 0; idx < eggCount; idx++) {
                if (eggs[idx].isBroken()) {
                    brokenEggCount++;
                }
            }
            max = Math.max(max, brokenEggCount);
            return;
        }

        if (eggs[eggIdx].isBroken()) {
            breakEggsWith(eggIdx + 1);
            return;
        }

        boolean strokeSomething = false;
        for (int idx = 0; idx < eggCount; idx++) {
            if (eggIdx != idx && !eggs[idx].isBroken()) {
                eggs[idx].durability -= eggs[eggIdx].weight;
                eggs[eggIdx].durability -= eggs[idx].weight;
                strokeSomething = true;
                breakEggsWith(eggIdx + 1);
                eggs[idx].durability += eggs[eggIdx].weight;
                eggs[eggIdx].durability += eggs[idx].weight;
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

    static int read() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static class Egg {

        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        public boolean isBroken() {
            return durability <= 0;
        }

        @Override
        public String toString() {
            return "Egg{" +
                    "durability=" + durability +
                    ", weight=" + weight +
                    '}';
        }
    }
}