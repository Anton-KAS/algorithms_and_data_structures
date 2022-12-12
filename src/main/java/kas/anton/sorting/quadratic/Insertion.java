package kas.anton.sorting.quadratic;

import java.util.Arrays;

/**
 * <b>Сортировка вставками</b>
 * <br>Идея: двигаем элемент влево, пока он не встанет на свое место
 *
 * @author Anton Komrachkov
 * @since (09.12.2022)
 */

public class Insertion {
    static int[] numbers = {5, 6, 2, 3, 9, -1};

    public static void main(String[] args) {
        System.out.println("Сортировка вставками:");
        sortingByInserts(numbers);
    }

    public static void sortingByInserts(int[] numbers) {
        System.out.println("Имеем: " + Arrays.toString(numbers));
        int step = 0;
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    System.out.println("\t↓  Меняем местами числа под номерами: " + (j - 1) + " и " + j
                            + " (" + numbers[j - 1] + " <-> " + numbers[j] + ")");
                    int setValue = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = setValue;
                    step++;
                    System.out.println("Шаг " + step + ": " + Arrays.toString(numbers));
                } else {
                    System.out.println("\t|  Числа под номерами: " + (j - 1) + " и " + j
                            + " (" + numbers[j - 1] + " < " + numbers[j] + ") на своих местах");
                }

            }
        }
    }

    public static void sortingIndexByInserts(int[] numbers, int index) {
        for (int j = index; j > 0; j--) {
            if (numbers[j] < numbers[j - 1]) {
                int setValue = numbers[j - 1];
                numbers[j - 1] = numbers[j];
                numbers[j] = setValue;
            }
        }
    }
}
