package kas.anton.tasks.internship_autumn_2022;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T08.png
В Тинькофф решили освоить новую нишу, а именно открыть внутренний стартап по продаже доменов.
Каждый домен представляет из себя строку, состоящую только из букв S, T, A, R.
Каждому покупателю хочется не просто продать строку, а продать нужную строку,
а именно, пусть у каждого покупателя есть строки P и Q.
Тогда ему подходят только строки,
у которых первые |P| символов совпадают с P и последние |Q| символов совпадают с Q.
Для начала было закуплено n доменов для m покупателей.
Теперь хочется понять, хватит ли их для всех покупателей.
Чтобы получить ответ на этот вопрос, посчитайте число подходящих для каждого покупателя строк.
 */

// Решение - первое что пришло в голову, наверняка на больших данных будет медленным
public class T08 {
    public static void main(String[] args) {
        int n, m; // 1 ≤ n, m ≤ 10^5
        String[] star;
        try (Scanner scanner = new Scanner(System.in)) {
            String[] nm = scanner.nextLine().split(" ");
            n = Integer.parseInt(nm[0]);
            m = Integer.parseInt(nm[1]);
            star = new String[n];
            for (int i = 0; i < n; i++) {
                star[i] = scanner.nextLine();
            }
            for (int i = 0; i < m; i++) {
                String[] query = scanner.nextLine().split(" ");
                int result = (int) Arrays.stream(star).filter(x -> x.matches(query[0] + "[A-Z]*" + query[1])).count();
                System.out.println(result);
            }
        }
    }
}
