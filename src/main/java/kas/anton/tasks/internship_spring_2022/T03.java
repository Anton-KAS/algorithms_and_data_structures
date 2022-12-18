package kas.anton.tasks.internship_spring_2022;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 3
Полное условие задачи в doc/T01.png
 */
public class T03 {
    public static void main(String[] args) {
        int n; // 1 ≤ n ≤ 10^5
        int[] ai; // 1 ≤ ai ≤ 10^18
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            ai = new int[n];
            for (int i = 0; i < n; i++) {
                ai[i] = scanner.nextInt();
            }
        }

        int result = 0;
        System.out.println(result);
    }
}
