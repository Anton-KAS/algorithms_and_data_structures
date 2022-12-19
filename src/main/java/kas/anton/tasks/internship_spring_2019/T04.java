package kas.anton.tasks.internship_spring_2019;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (19.12.2022)
 */

/*
Стажировка весна 2019. Задача 4
Полное условие задачи в doc/T04.jpeg
 */

public class T04 {
    private static int[][] used;
    public static void main(String[] args) {
        int N; // 3 ≤ n ≤ 20
        String[][] tables;
        int[] choice = new int[2];
        try (Scanner scanner = new Scanner(System.in)) {
            N = Integer.parseInt(scanner.nextLine());
            tables = new String[N][N];
            for (int i = 0; i < N; i++) {
                tables[i] = scanner.nextLine().split("");
            }
            choice[0] = scanner.nextInt() - 1;
            choice[1] = scanner.nextInt() - 1;
        }
        used = new int[N][N];
        countRec(choice[0], choice[1], tables);
        int result = 0;
        for (int[] i : used) {
            result += Arrays.stream(i).sum();
        }
        System.out.println(result);
    }

    private static void countRec(int i, int j, String[][] tables) {
        if (tables[i][j].equals("*")) {
            return;
        }
        if (used[i][j] == 1) {
            return;
        }
        used[i][j] = 1;
        countRec(i + 1, j, tables);
        countRec(i, j + 1, tables);
        countRec(i - 1, j, tables);
        countRec(i, j - 1, tables);
    }
}
