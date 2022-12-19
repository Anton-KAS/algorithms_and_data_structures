package kas.anton.tasks.internship_spring_2019;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (19.12.2022)
 */

/*
Стажировка весна 2019. Задача 1
Полное условие задачи в doc/T01.png
 */

public class T01 {
    public static void main(String[] args) {
        String s;
        try (Scanner scanner = new Scanner(System.in)) {
            s = scanner.nextLine().trim();
        }
        String result = s.replaceAll(" +", " ");
        System.out.println(result);
    }
}
