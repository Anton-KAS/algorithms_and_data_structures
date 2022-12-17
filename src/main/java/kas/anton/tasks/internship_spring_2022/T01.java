package kas.anton.tasks.internship_spring_2022;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 1
Полное условие задачи в doc/T01.png
 */
public class T01 {
    public static void main(String[] args) {
        int A, B, n; // 1 ≤ A, B, n ≤ 10^9
        try (Scanner scanner = new Scanner(System.in)) {
            A = scanner.nextInt();
            B = scanner.nextInt();
            n = scanner.nextInt();
        }
        if (A <= B) System.out.println("NO");
        else {
            if ((A + B) % 2 != 0) System.out.println("NO");
            else {
                if ((A - ((A + B) / 2)) / n == 0) System.out.println("NO");
                else System.out.println("YES");
            }
        }
    }
}
