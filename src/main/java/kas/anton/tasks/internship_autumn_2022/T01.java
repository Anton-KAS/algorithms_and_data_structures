package kas.anton.tasks.internship_autumn_2022;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Условие задачи в doc/T01.png
 */

public class T01 {
    public static void main(String[] args) {
        int[] x = new int[4];
        int[] y = new int[4];
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < 4; i++) {
                x[i] = scanner.nextInt();
                y[i] = scanner.nextInt();
            }
        }
        int deltaX = Arrays.stream(x).max().orElse(0) - Arrays.stream(x).min().orElse(0);
        int deltaY = Arrays.stream(y).max().orElse(0) - Arrays.stream(y).min().orElse(0);
        int deltaMax = Math.max(deltaX, deltaY);
        System.out.println(deltaMax * deltaMax);
    }
}
