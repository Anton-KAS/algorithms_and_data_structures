package kas.anton.tasks.internship_autumn_2022;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*

 */

public class T01 {
    public static void main(String[] args) {
        int[] xy1 = new int[4];
        int[] xy2 = new int[4];
        try (Scanner scanner = new Scanner(System.in)) {
            xy1 = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            xy2 = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int result = 0;
        System.out.println(result);
    }
}
