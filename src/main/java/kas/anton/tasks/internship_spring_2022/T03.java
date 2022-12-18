package kas.anton.tasks.internship_spring_2022;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 3
Полное условие задачи в doc/T03.png

Не уверен, что решение абсолютно верное, так как к условию есть вопросы:
Нужно найти х0 для всех возможных комбинаций с a или подходит любая комбинация?
 */
public class T03 {
    public static void main(String[] args) {
        int n; // 1 ≤ n ≤ 10^5
        long[] ai; // 1 ≤ ai ≤ 10^18
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            ai = new long[n];
            for (int i = 0; i < n; i++) {
                ai[i] = scanner.nextLong();
            }
        }
        long[] sortedAi = Arrays.stream(ai).sorted().toArray();
        long x = (long) Math.sqrt(sortedAi[sortedAi.length - 1]);
        long result;
        while (true) {
            boolean resultCheck = checkChain(ai, x);
            if (resultCheck) {
                result = x;
                break;
            }
            else x++;
        }
        System.out.println(result);
    }

    private static boolean checkChain(long[] ai, long x) {
        long xk1 = x;
        for (long a : ai) {
            long xk2 = xk1 * xk1 - a;
            if (xk2 < 0) return false;
            xk1 = xk2;
        }
        return true;
    }
}
