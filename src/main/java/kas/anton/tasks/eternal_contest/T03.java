package kas.anton.tasks.eternal_contest;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (12.12.2022)
 */

/*
У Кати насыщенный день на работе. Ей надо передать n разных договоров коллегам.
Все встречи происходят на разных этажах, а между этажами можно перемещаться только по лестничным пролетам
— считается, что это улучшает физическую форму сотрудников.
Прохождение каждого пролета занимает ровно 1 минуту.

Сейчас Катя на парковочном этаже, планирует свой маршрут.
Коллег можно посетить в любом порядке, но один из них покинет офис через t минут.
С парковочного этажа лестницы нет — только лифт, на котором можно подняться на любой этаж.

В итоге план Кати следующий:
Подняться на лифте на произвольный этаж. Считается, что лифт поднимается на любой этаж за 0 минут.
Передать всем коллегам договоры, перемещаясь между этажами по лестнице.
Считается, что договоры на этаже передаются мгновенно.
В первые t минут передать договор тому коллеге, который планирует уйти.
Пройти минимальное количество лестничных пролетов.
Помогите Кате выполнить все пункты ее плана.

Формат входных данных
В первой строке вводятся целые положительные числа n и t (2 ≤ n, t ≤ 100)
— количество сотрудников и время, когда один из сотрудников покинет офис (в минутах).
В следующей строке n чисел — номера этажей, на которых находятся сотрудники.
Все числа различны и по абсолютной величине не превосходят 100.
Номера этажей даны в порядке возрастания.
В следующей строке записан номер сотрудника, который уйдет через t минут.

Формат выходных данных
Выведите одно число — минимально возможное число лестничных пролетов, которое понадобится пройти Кате.

Замечание
В первом примере времени достаточно, чтобы Катя поднялась по этажам по порядку.
Во втором примере Кате понадобится подняться к уходящему сотруднику,
а потом пройти всех остальных — например, в порядке {1, 2, 3, 4, 6}

Примеры данных
Пример 1
Ввод:
5 5
1 4 9 16 25
2
Вывод: 24

Пример 2
Ввод:
6 4
1 2 3 6 8 25
5
Вывод: 31
 */

public class T03 {
    public static void main(String[] args) {
        int n, t, p;
        int[] floors;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt(); // количество сотрудников и время
            t = scanner.nextInt(); // время, когда один из сотрудников покинет офис (в минутах)
            floors = new int[n];
            for (int i = 0; i < n; i++) {
                floors[i] = scanner.nextInt(); // n чисел — номера этажей, на которых находятся сотрудники
            }
            p = scanner.nextInt() - 1; // Индекс (номер -1) сотрудника, который уйдет через t минут
        }
        int allFloors = floors[n - 1] - floors[0];
        int downFloors = floors[p] - floors[0];
        int upFloors = floors[n - 1] - floors[p];

        if ((downFloors) <= t || (upFloors) <= t) {
            System.out.println(allFloors);
        } else {
            System.out.println(allFloors + Math.min(downFloors, upFloors));
        }
    }
}
