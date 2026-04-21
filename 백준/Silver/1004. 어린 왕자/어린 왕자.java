import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int x1, y1, x2, y2;
        int n;
        int cx;
        int cy;
        int r;
        int T;

        T = readInt();

        while (T-- >= 1) {
            int in = 0, out = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            x1 = Integer.parseInt(stringTokenizer.nextToken());
            y1 = Integer.parseInt(stringTokenizer.nextToken());
            x2 = Integer.parseInt(stringTokenizer.nextToken());
            y2 = Integer.parseInt(stringTokenizer.nextToken());

            n = readInt();

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                cx = Integer.parseInt(stringTokenizer.nextToken());
                cy = Integer.parseInt(stringTokenizer.nextToken());
                r = Integer.parseInt(stringTokenizer.nextToken());

                int d1 = (x2 - cx) * (x2 - cx) + (y2 - cy) * (y2 - cy);
                int d2 = (x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy);
                int dr = r * r;
                if (d2 < dr) {
                    if (d1 > dr) {
                        in++;
                    }
                }
                if (d1 < dr) {
                    if (d2 > dr) {
                        out++;
                    }
                }
            }
            System.out.println(in + out);
        }
    }

    private static int readInt() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }
}