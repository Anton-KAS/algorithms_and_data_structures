package komrachkov.anton.sorting.quadratic;

import java.util.Arrays;

/**
 * <b>Сортировка пузырьком</b>
 * <br>Идея: Каждый раз будем смещать самый "тяжелый" элемент в конец
 *
 * @author Anton Komrachkov
 * @since (09.12.2022)
 */

public class Bubble {
    static int[] numbers = {5, 6, 2, 3, 9, -1};

    public static void main(String[] args) {
        System.out.println("Сортировка пузырьком:");
        sortingByInserts();
    }

    public static void sortingByInserts() {
        System.out.println("Имеем: " + Arrays.toString(numbers));
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.println("Проход: " + (i + 1));
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    System.out.println("\t↓  Меняем местами числа под номерами: " + j + " и " + (j + 1)
                            + " (" + numbers[j] + " <-> " + numbers[j + 1] + ")");
                    int setValue = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = setValue;
                    System.out.println("Шаг " + (j + 1) + ": " + Arrays.toString(numbers));
                } else {
                    System.out.println("\t|  Числа под номерами: " + j + " и " + (j + 1)
                            + " (" + numbers[j] + " > " + numbers[j + 1] + ") на своих местах");
                }
            }
        }
    }
}
