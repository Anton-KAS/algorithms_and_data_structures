package kas.anton.tasks.internship_autumn_2022;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T02.png
Аня — координатор стажировок в Тинькофф. Она хочет нанять самых сильных олимпиадников.
Для того чтобы понять, кто же лучший, Аня решила проанализировать результаты командной олимпиады за последние N лет.
Она знает все команды, занявшие первое место.
Каждая команда задается тройкой имен, причем их порядок не важен,
то есть записи ANTON BORIS CHRIS и BORIS ANTON CHRIS задают одну и ту же команду.
Ане нужны лучшие из лучших, поэтому она хочет знать,
какое максимальное число раз побеждала команда в одном и том же составе.
Вы дружите с Аней, поэтому согласились ей помочь.
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
