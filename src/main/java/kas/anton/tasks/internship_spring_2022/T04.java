package kas.anton.tasks.internship_spring_2022;

import java.util.*;

/**
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Стажировка весна 2022. Задача 4
Полное условие задачи в doc/T01.png
 */
public class T04 {
    public static void main(String[] args) {
        int n, m; // 1 ≤ n, m ≤ 50
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            m = scanner.nextInt();
        }
        int[][] steps = new int[][]{{1, 2}, {2, 1}};
        int[][] ways = new int[n][m];
        ways[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int lastStepWaysCount = ways[i][j];
                if (lastStepWaysCount == 0) continue;

                for (int[] step : steps) {
                    int ii = i + step[0];
                    int jj = j + step[1];
                    int currentStepWaysCount;
                    if (ii < n && jj < m) {
                        currentStepWaysCount = ways[ii][jj];
                        ways[ii][jj] = currentStepWaysCount + lastStepWaysCount;
                    }
                }
            }
        }
        System.out.println(ways[n - 1][m - 1]);
    }
}
