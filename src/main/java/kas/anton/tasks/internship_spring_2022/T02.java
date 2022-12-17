package kas.anton.tasks.internship_spring_2022;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 2
Полное условие задачи в doc/T01.png
 */
public class T02 {
    public static void main(String[] args) {
        long n, m; // // 1 ≤ n, m ≤ 10^18
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextLong();
            m = scanner.nextLong();
        }
        long result = 0;
        while(true) {
            long min = Math.min(m, n);
            long max = Math.max(m, n);
            if (max % min == 0) {
                result += Math.max(m, n) / Math.min(m, n);
                break;
            }
            m = max - min;
            n = min;
            result++;
        }
        System.out.println(result);
    }
}
