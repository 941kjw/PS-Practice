import java.util.*;

class Solution {
   private String[][] tickets;
    private boolean[] visited;
    private String[] route;
    private boolean finished;

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        this.tickets = tickets;
        this.visited = new boolean[tickets.length];
        this.route = new String[tickets.length + 1];

        route[0] = "ICN";
        dfs("ICN", 0);

        return route;
    }

    private void dfs(String current, int depth) {
        if (finished) {
            return;
        }

        if (depth == tickets.length) {
            finished = true;
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (!tickets[i][0].equals(current)) {
                continue;
            }

            visited[i] = true;
            route[depth + 1] = tickets[i][1];

            dfs(tickets[i][1], depth + 1);

            if (finished) {
                return;
            }

            visited[i] = false;
        }
    }
}