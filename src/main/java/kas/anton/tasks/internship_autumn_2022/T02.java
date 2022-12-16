package kas.anton.tasks.internship_autumn_2022;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Условие задачи в doc/T01.png
 */

public class T02 {
    public static void main(String[] args) {
        Map<List<String>, Integer> teamWinnerCount = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            int N = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < N; i++) {
                String[] names = scanner.nextLine().trim().split(" ");
                List<String> sortedNames = Arrays.stream(names).sorted().collect(Collectors.toList());
                if (teamWinnerCount.containsKey(sortedNames)) teamWinnerCount.put(sortedNames, teamWinnerCount.get(sortedNames) + 1);
                else teamWinnerCount.put(sortedNames, 1);
            }
        }
        System.out.println(teamWinnerCount.values().stream().max(Integer::compareTo).orElse(0));
    }
}
