package kas.anton.tasks.internship_spring_2019;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (19.12.2022)
 */

/*
Стажировка весна 2019. Задача 2
Полное условие задачи в doc/T02.jpeg
 */

public class T02 {
    public static void main(String[] args) {
        int N;
        int[][] roads;
        int result = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            N = scanner.nextInt();
            roads = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    roads[i][j] = scanner.nextInt();
                    if (roads[i][j] == 1) result++;
                }
            }
        }
        System.out.println(result / 2);
    }
}
