import java.util.*;

class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] parts = term.split(" ");

            String type = parts[0];
            int duration = Integer.parseInt(parts[1]);

            termMap.put(type, duration);
        }

        int todayDays = convertToDays(today);
        List<Integer> expired = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");

            String date = parts[0];
            String type = parts[1];

            int collectedDays = convertToDays(date);
            int duration = termMap.get(type);

            int expirationDays = collectedDays + duration * 28;

            if (expirationDays <= todayDays) {
                expired.add(i + 1);
            }
        }

        return expired.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int convertToDays(String date) {
        String[] parts = date.split("\\.");

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return year * 12 * 28
                + month * 28
                + day;
    }
}