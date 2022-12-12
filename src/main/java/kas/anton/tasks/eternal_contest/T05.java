package kas.anton.tasks.eternal_contest;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (12.12.2022)
 */

/*
Во время разработки некоторой задачи Саша решил сгенерировать несколько новых тестов.
Каждый тест Саши должен представлять собой натуральное число, не меньшее l и не большее r.
Кроме того, натуральное число в тесте обязательно должно состоять из одинаковых цифр.

Например, число 999 подходит под это требование, а число 123 — нет.
Какое максимальное число различных тестов сможет создать Саша?

Формат входных данных
В единственной строке вводятся два натуральных числа l, r (1 ≤ l, r ≤ 10^18) — ограничения на тесты Саши.

Обратите внимания, что эти числа не вместятся в 32-битный тип данных, используйте 64-битный при необходимости

Формат выходных данных
Выведите одно число — количество тестов, которое может сделать Саша.

Замечание
В первом тесте Саше подходят номера [4,5,6,7].
Во втором тесте подходят все числа, кратные 11, от 11 до 99.

Примеры данных
Пример 1
Ввод: 4 7
Вывод: 4

Пример 2
Ввод: 10 100
Вывод: 9
 */

public class T05 {
    public static void main(String[] args) {
        long l, r;
        try (Scanner scanner = new Scanner(System.in)) {
            l = scanner.nextLong();
            r = scanner.nextLong();
        }
        long result = 0;
        for (int i = String.valueOf(l).length(); i <= String.valueOf(r).length(); i++) {
            for (int n = 1; n <= 9; n++) {
                String stringNumber = new String(new char[i]).replace("\0", String.valueOf(n));
                if (stringNumber.length() > 18) continue;
                long targetNumber = Long.parseLong(stringNumber);
                if (targetNumber >= l & targetNumber <= r) result++;
            }
        }
        System.out.println(result);
    }
}
