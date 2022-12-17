package kas.anton.tasks.internship_autumn_2022;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T06.png
В офисе Тинькофф есть несколько лифтов для минимизации времени ожидания и ускорения перемещения по зданию.
У лифтов есть особенность: i-й лифт едет только с этажа si до этажа fi без промежуточных остановок.
По задумке строителей лифты везут пассажиров только вверх (вниз все ходят по лестницам).
В первый день стажировки вы решили воспользоваться этими особенностями,
а именно прокатиться на максимальном числе лифтов подряд, составив цепь.
Цепью вы называете последовательность лифтов, для которых для любых двух лифтов,
имеющих в цепи номера i и i + 1 выполняется условие fi = si+1,
то есть между двумя лифтами вам не нужно пользоваться лестницей, чтобы добраться от одного до другого.
Определите максимально возможную длину цепи лифтов, на которых вам удастся прокатиться.
 */

public class T06 {
    public static void main(String[] args) {
        int n; // 1 ≤ n ≤ 10^5
        int[] si, fi; // 1 ≤ i ≤ n,  0 ≤ si ≤ fi ≤ 10^9
        SortedMap<Integer, Integer> allFloors = new TreeMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            si = new int[n];
            fi = new int[n];
            for (int i = 0; i < n; i++) {
                si[i] = scanner.nextInt();
                fi[i] = scanner.nextInt();
                allFloors.put(fi[i], 1);
                if (!allFloors.containsKey(si[i])) allFloors.put(si[i], 0);
            }
        }

        for (int floor : allFloors.keySet()) {
            for (int i = 0; i < fi.length; i++) {
                if (fi[i] != floor) continue;
                int dp = allFloors.get(si[i]);
                if (allFloors.get(floor) < dp + 1) allFloors.replace(floor, dp + 1);
            }
        }

        int result = allFloors.values().stream().max(Integer::compareTo).orElse(1);
        System.out.println(result);
    }
}
