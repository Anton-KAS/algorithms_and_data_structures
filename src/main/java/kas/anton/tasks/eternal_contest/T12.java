package kas.anton.tasks.eternal_contest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (14.12.2022)
 */

/*
У Артемия есть бесконечное число монет, каждая из которых одного из трех номиналов.
Его интересует, какие суммы от 1 до N рублей он может набрать в свой кошелек,
если там заранее лежала монета величиной в 1 рубль.

Формат входных данных
Первая строка содержит число N — ограничение на суммарную стоимость монет в кошельке (1 ≤ N ≤ 10^18).
Вторая строка содержит три числа A, B и C, задающие достоинства типов монет (1 ≤ A, B, C ≤ 100000).

Формат выходных данных
Выведите единственное число — количество сумм, которые можно набрать в кошельке.

Замечание
В первом примере возможны следующие варианты:
1 = 1
1 + 4 = 5
1 + 7 = 8
1 + 4 + 4 = 9
1 + 9 = 10
1 + 4 + 7 = 12
1 + 4 + 4 + 4 = 13
1 + 4 + 9 = 14
1 + 7 + 7 = 15

Примеры данных
Пример 1
Ввод:
15
4 7 9
Вывод: 9
*/

// Проходит 8 тестов в Контесте. Очень долго выполняется на тесте при больших A, B, C и максимальном N;
public class T12 {
    public static void main(String[] args) {
        long N; // ограничение на суммарную стоимость монет (1 ≤ N ≤ 10^18)
        long[] ABC = new long[3]; // задающие достоинства типов монет (1 ≤ A, B, C ≤ 100_000)
        try (Scanner scanner = new Scanner(System.in)) {
            N = scanner.nextLong();
            ABC[0] = scanner.nextInt();
            ABC[1] = scanner.nextInt();
            ABC[2] = scanner.nextInt();
        }
        if (N > 0) {
            // Проверка, можем ли мы сократить все числа
            long nod = getNod(ABC, N);
            if (nod > 1) {
                ABC[0] = ABC[0] / nod;
                ABC[1] = ABC[1] / nod;
                ABC[2] = ABC[2] / nod;
                N = N / nod;
            }
        }

        // Проверка, можем ли найти НОК и посчитать только для НОК и для остатка для НОК
        long nok = getNOK(ABC);
        long remainsMax;
        if (nok < N) {
            remainsMax = N % nok;
        } else {
            remainsMax = N;
            nok = 0;
        }

        // Проверяем, что монеты не кратны друг другу
        long[] coins = optimiseABC(ABC);

        long countNok = 1;
        long countRemains = 0;
        Map<Long, Boolean> remainsSums = new HashMap<>();
        remainsSums.put(1L, true);
        Map<Long, Boolean> processingSums = new HashMap<>(remainsSums);
        while (true) {
            Long[] iterSums = processingSums.keySet().toArray(new Long[0]);
            Arrays.sort(iterSums);
            if (iterSums.length == 0) break;
            long min = iterSums[0];
            for(long r : remainsSums.keySet().stream().filter(k -> k < min).collect(Collectors.toSet())) {
                remainsSums.remove(r);
            }
            processingSums.clear();

            label:
            for (long s : iterSums) {
                for (long c : coins) {
                    long sum = s + c;
                    if (sum > nok && sum > remainsMax) break label;
                    if (!remainsSums.containsKey(sum) && !processingSums.containsKey(sum)) {
                        if (sum <= nok + 1) countNok++;
                        if (sum <= remainsMax) countRemains++;
                        processingSums.put(sum, true);
                    }
                }
            }
            remainsSums.putAll(processingSums);
        }
        long result = 0;
        if (remainsMax > 0) result += countRemains + 1;
        if (nok > 0) {
            result += countNok * (N / nok);
        }
        System.out.println(result);
    }

    public static long getNOK(long[] ABC) {
        Map<Long, Long> multipliers = new HashMap<>();
        for (long i : ABC) {
            long multiplier = 2;
            long number = i;
            long n = 0;
            while (number >= multiplier) {
                if ((number % multiplier) == 0) {
                    number = number / multiplier;
                    n++;
                    if (number >= multiplier) continue;
                }
                if (n > 0) {
                    if (multipliers.containsKey(multiplier)) {
                        multipliers.put(multiplier, Math.max(multipliers.get(multiplier), n));
                    } else {
                        multipliers.put(multiplier, n);
                    }
                }
                n = 0;
                multiplier++;
            }
        }
        long result = 1;
        for (long multiplier : multipliers.keySet()) {
//            result *= Math.pow(multiplier, multipliers.get(multiplier));
            result = result * binPowerIter(multiplier, multipliers.get(multiplier));
        }
        return result;
    }

    private static long binPowerIter(long x, long power) {
        long result = 1;
        while (power > 0) {
            if ((power & 1) != 0) {
                result = result * x;
            }
            x = x * x;
            power >>= 1;
        }
        return result;
    }

    public static long getNod(long[] ABC, long N) {
        long min = Long.min(ABC[0], ABC[1]);
        min = Long.min(min, ABC[2]);
        min = Long.min(min, N);
        if (checkNod(ABC, N, min)) {
            return min;
        }
        long nod = 1;
        long checkingNod = 1;
        for (long i = 1; i <= min / 2; i++) {
            if (checkNod(ABC, N, checkingNod)) {
                nod = checkingNod;
                checkingNod++;
            }
        }
        return nod;
    }

    private static boolean checkNod(long[] ABC, long N, long nod) {
        return ABC[0] % nod == 0 && ABC[1] % nod == 0 && ABC[2] % nod == 0 && N % nod == 0;
    }

    public static long[] optimiseABC(long[] ABC) {
        Arrays.sort(ABC);
        List<Long> coinsList = new ArrayList<>();
        for (long coin : ABC) {
            coinsList.add(coin);
        }
        List<Integer> removeIndexes = new ArrayList<>();

        for (int i = 0; i < ABC.length; i++) {
            for (int j = 0; j < ABC.length; j++) {
                if (i == j) continue;
                if (ABC[i] % ABC[j] == 0 && !removeIndexes.contains(i)) {
                    removeIndexes.add(i);
                }
            }
        }

        removeIndexes.sort(Collections.reverseOrder());
        int n = 1;
        for (int i : removeIndexes) {
            if (n >= ABC.length) break;
            coinsList.remove(i);
            n++;
        }

        if (coinsList.size() >= ABC.length) return ABC;
        long[] coins = new long[coinsList.size()];
        for (int i = 0; i < coinsList.size(); i++) {
            coins[i] = coinsList.get(i);
        }
        Arrays.sort(coins);
        return coins;
    }
}
