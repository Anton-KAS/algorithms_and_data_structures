package kas.anton.tasks.eternal_contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (12.12.2022)
 */

/*
В школе перед Новым Годом устраивают игру в Тайного Санту.
Каждому ученику i выдается ученик a_i, которому он должен подарить подарок.

Костя, как администратор игры, определил каждому школьнику свое число a_i.
Но потом его коллега Маша спросила:
А правда ли, что если начать цепочку подарков от школьника 1 к школьнику a_1, потом a_a1 и так далее,
то цепочка замкнется на школьнике 1, после того, как задействует всех остальных учеников ровно по одному разу?

Костя не знает, правда это или нет, но он собирается изменить ровно одно число a_i,
чтобы получить конфигурацию, которая устроит Машу. Помогите ему с этим.

Формат входных данных
В первой строке находится натуральное число n — количество школьников (2 ≤ n ≤ 10^5).
В следующей строке находится n натуральных чисел a_i
— ученик, который достался Тайному Санте с номером i (1 ≤ a_i ≤ n).

Формат выходных данных
В первой строке выведите два числа (1 ≤ x, y ≤ n,x != y) — номер ученика x,
которому нужно изменить число a_x, и новое значение a_x.
Должно выполняться условие a_x != y. Если ответов несколько — выведите любой.
Если сделать это невозможно — выведите "-1 -1"

Замечание
В первом примере хотя бы один школьник будет дарить подарок сам себе.
Во втором примере после изменения происходят передачи подарков 1 → 2 → 3 → 1.

Примеры данных
Пример 1
Ввод:
3
1 2 3
Вывод: -1 -1

Пример 2
Ввод:
3
1 3 1
Вывод: 1 2
 */

// Задача проходит все возможные мною придуманные тесты, но в Контесте проходит всего 7 тестов
public class T07 {
    private static List<Integer[]> answers = new ArrayList<>();
    private final static int SHIFT = 1;

    public static void main(String[] args) {
        int n; // 2 ≤ n ≤ 10^5 — количество школьников
        int[] ai; // 1 ≤ a_i ≤ n
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            ai = new int[n];
            for (int i = 0; i < n; i++) {
                ai[i] = scanner.nextInt();
            }
        }
        int x = -1;
        int y = -1;

        int[] count = count(ai); // Считаем, сколько раз каждому студенту дарят подарки
        // Осуществляется поиск только для случая когда есть 1 студент, которому никто не дарит
        // и студент, которому дарят 2 раза
        if (Arrays.stream(count).filter(a -> a == 0).toArray().length == 1
                && Arrays.stream(count).filter(a -> a == 2).toArray().length == 1
                && Arrays.stream(count).filter(a -> a != 1).toArray().length == 2) {
            // Поиск номера студентов кому не дарят и кому дарят 2 раза
            int setY = -1;
            int xValue = -1;
            for (int i = 0; i < count.length; i++) {
                if (count[i] == 0) setY = i + SHIFT;  // Номер студента кому никто не дарит
                if (count[i] == 2) xValue = i + SHIFT; // Номер студента, которому дарят два раза
                if (setY != -1 && xValue != -1) break;
            }

            // Поиск индексов 2 студентов, которые дарят одному и тому же студенту
            int set = 0;
            int[] xes = new int[2];
            for (int i = 0; i < ai.length; i++) {
                if (ai[i] == xValue) {
                    xes[set] = i;
                    set++;
                }
                if (set > 1) break;
            }

            // Пытаемся поменять поочереди каждому студенту из 2-х студентов номер кому он дарит и проверяем цепочку
            for (int t : xes) {
                int[] setAi = ai.clone();
                setAi[t] = setY;
                boolean result = checkArray(setAi);
                if (result && (t + SHIFT) != setY) {
                    x = t + SHIFT;
                    y = setY;
                }
            }
        }
        System.out.printf("%s %s\n", x, y);
    }

    private static int[] count(int[] ai) {
        int[] counts = new int[ai.length];
        for (int j : ai) {
            int setAi = j - SHIFT;
            counts[setAi]++;
        }
        return counts;
    }

    private static boolean checkArray(int[] setAi) {
        int count = 0;
        int index = 0;
        List<Integer> chain = new ArrayList<>();
        while (count < setAi.length) {
            if (chain.contains(setAi[index])) return false;
            chain.add(setAi[index]);
            index = setAi[index] - SHIFT;
            count++;
        }
        return true;
    }
}
/*
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String defaultResult = "-1 -1";

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String inputAi = scanner.nextLine();
        int[] ai = Arrays.stream(inputAi.split(" ")).mapToInt(Integer::parseInt).toArray();

        String result = defaultResult;
        String preResult = defaultResult;
        int[] aiProcessing = ai.clone();

        boolean resultCheck = checkChain(ai);
        if (!resultCheck) {
            for (int i = 0; i < n; i++) {
                for (int ii = 1; ii <= n; ii++) {
                    if ((i + 1) == ii) {
                        continue;
                    }
                    aiProcessing = ai.clone();
                    aiProcessing[i] = ii;
                    preResult = (i + 1) + " " + ii;
                    resultCheck = checkChain(aiProcessing);
                    if (resultCheck) {
                        break;
                    }
                }
                if (resultCheck) {
                    break;
                }
            }

        }
        if (resultCheck) {
            result = preResult;
        }
        System.out.println(result);
    }

    public static boolean checkChain(int[] students) {
        int n = students.length;
        int position = 0;
        List<Integer> chain = new ArrayList<>();
        while (true) {

            int nextStudent = students[position];

            if ((nextStudent - 1) != position & !chain.contains(nextStudent)) {
                chain.add(nextStudent);
                position = nextStudent - 1;
            } else {
                break;
            }
        }
        return chain.size() == n;
    }
}
 */
