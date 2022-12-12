package kas.anton.sorting.effective;

import java.util.Arrays;

/**
 * Сортировка подсчетом
 *
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

public class Counting {
    static int[] numbers = {5, 6, 2, 3, 9, -1, 6};

    public static void main(String[] args) {
        System.out.println("Сортировка слиянием");
        System.out.println("Имеем: " + Arrays.toString(numbers));
        CountingInt.sort(numbers);
        System.out.println("ИТОГ: " + Arrays.toString(numbers));
    }

    public static class CountingInt {
        public static void sort(int[] numbers) {
            int min = Arrays.stream(numbers).min().orElse(0);
            int max = Arrays.stream(numbers).max().orElse(0);
            int size = (max - min) + 1;
            int[] counts = new int[size];
            for (int i : numbers) {
                counts[i - min] += 1;
            }
            System.out.println("Массив подсчета: " + Arrays.toString(counts));
            int addIndex = 0;
            for (int i = 0; i < counts.length; i++) {
                for (int j = 0; j < counts[i]; j++) {
                    numbers[addIndex] = i + min;
                    addIndex++;
                }
            }
        }
    }
}
