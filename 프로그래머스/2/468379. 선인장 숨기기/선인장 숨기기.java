import java.util.*;

class Solution {

    private int[][] map;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        map = new int[m][n];

        for (int[] row : map) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int time = 0; time < drops.length; time++) {
            int r = drops[time][0];
            int c = drops[time][1];

            map[r][c] = time + 1;
        }

        int windowColumnCount = n - w + 1;

        int[][] rowMin = new int[m][windowColumnCount];

        for (int r = 0; r < m; r++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int c = 0; c < n; c++) {

                while (!deque.isEmpty() && map[r][deque.peekLast()] >= map[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(c);

                while (!deque.isEmpty() && deque.peekFirst() <= c - w) {
                    deque.pollFirst();
                }

                if (c >= w - 1) {
                    int startColumn = c - w + 1;
                    rowMin[r][startColumn] = map[r][deque.peekFirst()];
                }
            }
        }

        int[] answer = {0, 0};
        int max = Integer.MIN_VALUE;

        for (int c = 0; c < windowColumnCount; c++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int r = 0; r < m; r++) {

                while (!deque.isEmpty() && rowMin[deque.peekLast()][c] >= rowMin[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(r);

                while (!deque.isEmpty() && deque.peekFirst() <= r - h) {
                    deque.pollFirst();
                }

                if (r >= h - 1) {
                    int startRow = r - h + 1;
                    int min = rowMin[deque.peekFirst()][c];

                    if (min > max || (min == max && (startRow < answer[0] || (startRow == answer[0] && c < answer[1])))) {
                        max = min;
                        answer[0] = startRow;
                        answer[1] = c;
                    }
                }
            }
        }

        return answer;
    }
}