package kas.anton.tasks.internship_autumn_2022;

import java.util.*;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T05.png
Пройдя тестовое задание от куратора из предыдущей задачи, вы получили новое.
На этой раз вам нужно улучшить систему поиска карточек в бухгалтерии Тинькофф.
Всего у нас работает n людей. Каждый человек определяется своей фамилией,
состоящей из строчных букв латинского алфавита (a,...,z).
К сожалению, бумажные записи со временем становятся нечитаемыми,
так как конец фамилии стирается, но команда бухгалтерии отлично знает систему хранения карточек
и умеет находить любого сотрудника по префиксу его фамилии.
Для более быстрой работы дополнительно требуется знать k-го в лексикографическом порядке человека
среди всех с заданным префиксом. Задачу быстрого поиска такого человека и поставил перед вами куратор.
 */

// Не уверен в правильности своей интерпретации условия задачи, но стандартный тест проходит
public class T05 {
    public static void main(String[] args) {
        int n, q;
        SortedMap<String, Integer> names = new TreeMap<>();
        String[][] queries;
        try (Scanner scanner = new Scanner(System.in)) {
            int[] aq = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = aq[0];
            q = aq[1];

            for (int i = 0; i < n; i++) names.put(scanner.nextLine(), i + 1);

            queries = new String[q][2];
            for (int i = 0; i < q; i++) queries[i] = scanner.nextLine().split(" ");
        }
        for (String[] query : queries) {
            int order = Integer.parseInt(query[0]);
            String prefix = query[1];
            String name = binSearch(names, prefix, order);
            if (name == null) System.out.println("-1");
            else System.out.println(names.get(name));
        }
    }

    private static String binSearch(SortedMap<String, Integer> names, String prefix, int order) {
        boolean[] resultBool = new boolean[names.size()];
        String[] namesArr = names.keySet().toArray(new String[0]);
        int l = -1;
        int r = names.size();
        int index = -1;
        while ((r - l) >= 2) {
            int m = ((r - l) / 2) + l;
            String name = namesArr[m];
            if (name.compareTo(prefix) >= 0) {
                for (int i = m; i < r; i++) resultBool[i] = true;
                r = m;
                index = m;
            } else l = m;
        }
        if (index < 0) return null;
        index += order - 1;
        if (index >= names.size()) return null;
        else return namesArr[index];
    }
}
