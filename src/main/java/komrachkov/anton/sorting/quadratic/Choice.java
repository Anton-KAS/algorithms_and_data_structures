package komrachkov.anton.sorting.quadratic;

import java.util.Arrays;

/**
 * <b>Сортировка выбором</b><br>
 * Идея: мы можем n раз выбрать минимум и составить отсортированный массив
 * @author Anton Komrachkov
 * @since (09.12.2022)
 */

public class Choice {
    static int[] numbers = {5, 6, 2, 3, 9, -1};

    public static void main(String[] args) {
        sortingByChoice(numbers);
    }

    public static void sortingByChoice(int[] numbers) {
        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i++) {

            System.out.println("\t↓");

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
            int valueByI = numbers[i];
            numbers[i] = numbers[minValueIndex];
            numbers[minValueIndex] = valueByI;

            System.out.println(Arrays.toString(numbers));
        }
    }
}
