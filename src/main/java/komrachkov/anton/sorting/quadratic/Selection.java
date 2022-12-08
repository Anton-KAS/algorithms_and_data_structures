package komrachkov.anton.sorting.quadratic;

import java.util.Arrays;

/**
 * <b>Сортировка выбором</b><br>
 * Идея: мы можем n раз выбрать минимум и составить отсортированный массив
 *
 * @author Anton Komrachkov
 * @since (09.12.2022)
 */

public class Choice {
    static int[] numbers = {5, 6, 2, 3, 9, -1};

    public static void main(String[] args) {
        System.out.println("Сортировка выбором:");
        sortingByChoice();
    }

    public static void sortingByChoice() {
        System.out.println("Имеем: " + Arrays.toString(numbers));

        for (int i = 0; i < numbers.length - 1; i++) {
            int minValueIndex = -1;
            for (int j = i; j < numbers.length; j++) {
                if (minValueIndex == -1) {
                    minValueIndex = j;
                    continue;
                }
                if (numbers[j] < numbers[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            int setValue = numbers[i];

            System.out.println("\t↓  Меняем местами числа под номерами: " + i + " и " + minValueIndex
                    + " (" + numbers[i] + " <-> " + numbers[minValueIndex] + ")");

            numbers[i] = numbers[minValueIndex];
            numbers[minValueIndex] = setValue;

            System.out.println("Шаг " + (i + 1) + ": " + Arrays.toString(numbers));
        }
    }
}
