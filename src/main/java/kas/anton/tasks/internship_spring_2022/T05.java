package kas.anton.tasks.internship_spring_2022;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 5
Полное условие задачи в doc/T05.png
 */
public class T05 {
    public static void main(String[] args) {
        int n; // 1 ≤ n ≤ 300_000
        int[] ai; // n чисел 1 ≤ ai ≤ i
        int[] bi; // n чисел 1 ≤ bi ≤ n - 1
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            ai = new int[n];
            for (int i = 0; i < n; i++) {
                ai[i] = scanner.nextInt();
            }
            bi = new int[n];
            for (int i = 0; i < n; i++) {
                bi[i] = scanner.nextInt();
            }
        }

        int[] jumps = new int[n];
        for (int i = 1; i < n; i++) jumps[i] = -1;

        for (int i = 0; i < n; i++) {
            int lastJumpCount = jumps[i];
            if (lastJumpCount == -1) continue;

            int canJump = ai[ai.length - i - 1];
            for (int j = 1; j <= canJump; j++) {
                if (i + j > n) continue;
                int resultUp = i + j;

                if (resultUp == n) {
                    if (jumps[n - 1] == -1 || (jumps[n - 1] > 0 && jumps[n - 1] > lastJumpCount + 1)) {
                        jumps[n - 1] = lastJumpCount + 1;
                        continue;
                    }
                }

                int result = resultUp - bi[n - i - j - 1];
                if (result == i || result < 0) continue;

                int countInResult = Math.max(jumps[result], 0);

                if (countInResult == 0 || lastJumpCount + 1 < countInResult) {
                    jumps[result] = lastJumpCount + 1;
                }
            }
        }
        System.out.println(jumps[n - 1]);
    }
}
