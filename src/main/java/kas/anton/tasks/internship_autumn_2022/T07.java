package kas.anton.tasks.internship_autumn_2022;

import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T07.png
После работы ваш коллега, стажер Павел, решил зайти в магазин.
Как и вы, Павел — разработчик, поэтому каждое свое действие он выполняет по алгоритму в строгой последовательности.
Супермаркет для Павла — прямая с полками.
На каждой полке стоят товары одной категории,
а каждая полка помечена какой-то строчной буквой латинского алфавита (a, . . . , z),
то есть весь супермаркет можно представлять как строку s.
Павел хочет взять по одному товару с каждой полки в каком-то порядке. Для этого он делает две операции:
• Взять товар с текущей полки и положить в корзину, если он этого не сделал ранее.
• Передвинуться к следующей полке. Если он стоял у последней полки, он возвращается к первой.
Павел любит порядок и хочет складывать товары в отсортированном порядке,
а именно сначала он хочет взять по одному товару с полок с буквой a, если они есть,
затем — с буквой b и так далее до z.
У Павла был тяжелый день, он хочет домой, поэтому планирует закончить с покупками как можно быстрее.
Для этого он решил брать товары не со всех полок, а только с какого-то подотрезка,
т.е. рассматривать все полки с l-й по r-ю. Пожалуйста, помогите Павлу быстрее попасть домой и посчитайте,
сколько передвижений, то есть операций второго типа, ему нужно будет сделать.
 */

public class T07 {
    public static void main(String[] args) {
        String s;
        int q;
        try (Scanner scanner = new Scanner(System.in)) {
            s = scanner.nextLine();
            q = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < q; i++) {
                int l = scanner.nextInt();
                int r = scanner.nextInt();

                String[] plan = s.substring(l - 1, r).split("");
                String[] order = plan.clone();
                Arrays.sort(order);
                int count = 0;
                int cursor = 0;
                for (String step : order) {
                    while (true) {
                        if (cursor >= order.length) cursor = 0;
                        if (plan[cursor].equals(step)) {
                            break;
                        }
                        cursor++;
                        count++;
                    }
                }
                System.out.println(count);
            }

        }
    }
}
