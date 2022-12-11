package komrachkov.anton.sorting.effective;

import java.util.Arrays;

/**
 * Сортировка слиянием
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

public class Merge {
    static int[] numbers = {5, 6, 2, 3, 9, -1, 6};

    public static void main(String[] args) {
        System.out.println("Сортировка слиянием");
        System.out.println("Имеем: " + Arrays.toString(numbers));
        MergeInt.sort(numbers);
        System.out.println("ИТОГ: " + Arrays.toString(numbers));
    }

    public static class MergeInt {
        public static void sort(int[] numbers) {
            int l = 0;
            int r = numbers.length;
            sort(numbers, l, r);
        }

        private static void sort(int[] numbers, int l, int r) {
            if ((r - l) < 2) return;
            int m = (l + r) / 2;
            sort(numbers, l, m);
            sort(numbers, m, r);
            merge(numbers, l, m, r);
        }

        private static void merge(int[] numbers, int l, int m, int r) {
            int l1 = l;
            int l2 = m;
            int addIndex = 0;
            int[] result = new int[r - l];
            while (l1 < m || l2 < r) {
                if (l1 < m && l2 < r) {
                    if (numbers[l1] == numbers[l2]) {
                        result[addIndex] = numbers[l1];
                        l1++;
                        addIndex++;
                        result[addIndex] = numbers[l2];
                        l2++;
                    } else if (numbers[l1] < numbers[l2]) {
                        result[addIndex] = numbers[l1];
                        l1++;
                    } else if (numbers[l1] > numbers[l2]) {
                        result[addIndex] = numbers[l2];
                        l2++;
                    }
                } else {
                    if (l1 < m) {
                        result[addIndex] = numbers[l1];
                        l1++;
                    } else {
                        result[addIndex] = numbers[l2];
                        l2++;
                    }
                }
                addIndex++;
            }
            int[] int1 = Arrays.copyOfRange(numbers, l, m);
            int[] int2 = Arrays.copyOfRange(numbers, m, r);
            System.out.print(Arrays.toString(int1) + " and " + Arrays.toString(int2) + " Merge to: " + Arrays.toString(result) + " => ");
            for (int i : result) {
                numbers[l] = i;
                l++;
            }
            System.out.println(Arrays.toString(numbers));
        }
    }
}
