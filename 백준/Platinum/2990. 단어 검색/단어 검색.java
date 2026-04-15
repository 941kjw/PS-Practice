import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    static int n;

    static class Node {
        Node[] children = new Node[26];
        int visit = 0;
        int end;
        boolean isEnd = false;

        Node() {
            end = n - 1;
        }

        void insert(String s, int idx) {
            Node cur = this;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Node();
                }
                cur.children[c].visit++;
                cur = cur.children[c];
            }
            cur.end = idx;
            cur.isEnd = true;
        }

        int find(String s) {
            Node cur = this;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (cur.children[c] == null) return n - 1;
                cur = cur.children[c];
            }
            return cur.end;
        }

        int value(String s) {
            Node cur = this;
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                for (Node child : cur.children) {
                    if (child != null) result += child.visit;
                }
                if (cur.isEnd) result += 1;
                int c = s.charAt(i) - 'a';
                if (cur.children[c] == null) return result;
                cur = cur.children[c];
            }
            for (Node child : cur.children) {
                if (child != null) result += child.visit;
            }
            if (cur.isEnd) result += 1;
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        initReader();

        n = getNumber();
        String[] word = new String[n];
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            word[i] = getString();
            root.insert(word[i], i);
        }

        int q = getNumber();
        int[] result = new int[q];
        int[] qIdx = new int[q];
        String[] qSearch = new String[q];

        for (int i = 0; i < q; i++) {
            qSearch[i] = getString();
            qIdx[i] = root.find(qSearch[i]);
        }

        Integer[] sortedQ = new Integer[q];
        for (int i = 0; i < q; i++) sortedQ[i] = i;
        Arrays.sort(sortedQ, Comparator.comparingInt(a -> qIdx[a]));

        int li = -1;
        Node nroot = new Node();

        for (int qi : sortedQ) {
            int ni = qIdx[qi];
            for (int i = li + 1; i <= ni; i++) {
                nroot.insert(word[i], i);
            }
            result[qi] = nroot.value(qSearch[qi]);
            li = ni;
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append('\n');
        }
        writer.write(sb.toString());

        clear();
    }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void initReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    private static int getNumber() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static String getString() throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }
}
