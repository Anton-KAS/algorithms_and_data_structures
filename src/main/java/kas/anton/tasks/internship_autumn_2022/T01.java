package kas.anton.tasks.internship_autumn_2022;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T01.png
В команде Тинькофф произошла реорганизация — объединились два отдела.
Новые коллеги хотят сидеть рядом, поэтому офису требуется ремонт.
Прежние места каждого отдела имели форму прямоугольника.
Новая площадка должна быть квадратной, а также содержать предыдущие места,
т.к. некоторые разработчики очень привязаны к ним.
К сожалению, размеры офиса ограничены, поэтому зона должна иметь минимальную площадь.
Строители пытаются посчитать, сколько материала им понадобится для ремонта.
Для этого им нужно знать итоговую площадь обновленной площадки.
Пожалуйста, помогите им.
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
