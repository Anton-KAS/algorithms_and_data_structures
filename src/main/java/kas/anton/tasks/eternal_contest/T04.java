package kas.anton.tasks.eternal_contest;

import java.util.*;

/**
 * @author Anton Komrachkov
 * @since (12.12.2022)
 */

/*
У Кости есть бумажка, на которой написано n чисел.
Также у него есть возможность не больше, чем k раз, взять любое число с бумажки,
после чего закрасить одну из старых цифр, а на ее месте написать новую произвольную цифру.

На какое максимальное значение Костя сможет увеличить сумму всех чисел на листочке?

Формат входных данных
В первой строке входного файла даны два целых числа
n, k — количество чисел на бумажке и ограничение на число операций.
(1 ≤ n ≤ 1000, 1 ≤ k ≤ 10^4)

Во второй строке записано n чисел a_i — числа на бумажке (1 ≤ a_i ≤ 10^9)

Формат выходных данных
В выходной файл выведите одно число — максимальную разность между конечной и начальной суммой.

Замечание
В первом примере Костя может изменить две единицы на две девятки,
в результате чего сумма чисел увеличится на 16.

Во втором примере Костя меняет число 85 на 95.

В третьем примере можно ничего не менять.
Обратите внимание, что ответ может превышать вместимость 32 - битного типа данных.

Примеры данных
Пример 1
Ввод:
5 2
1 2 1 3 5
Вывод: 16

Пример 2
Ввод:
3 1
99 5 85
Вывод: 10

Пример 3
Ввод:
1 10
9999
Вывод: 0
 */

public class T04 {
    public static void main(String[] args) {
        int n, k;
        List<Long> values;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            k = scanner.nextInt();

            int[] numbers = new int[n];
            values = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();

                int multiplier = 1;
                while (numbers[i] > 0) {
                    int number = numbers[i] % 10;
                    numbers[i] /= 10;
                    long addSum = (long) (9 - number) * multiplier;
                    values.add(addSum);
                    multiplier *= 10;
                }
            }
        }

        long addToSum = values.stream().sorted(Comparator.reverseOrder()).limit(k).reduce(0L, Long::sum);
        System.out.println(addToSum);
    }
}
