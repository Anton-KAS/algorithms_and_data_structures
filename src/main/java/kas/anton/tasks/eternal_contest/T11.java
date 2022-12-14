package kas.anton.tasks.eternal_contest;

import java.util.Scanner;

/**
 * @author Anton Komrachkov
 * @since (14.12.2022)
 */

/*
Леше в институте рассказали Малую теорему Ферма:
a^(p−1) ≡ 1 (mod p), где p — простое
С помощью этого утверждения можно искать обратное по модулю. А именно, если
a !≡ (mod p), то a^(-1)a = a^(p-2).
Теперь Леша решает домашнюю работу, в которой есть задача:
надо посчитать Σ(от x=l по r) 1/x (mod p).

Помогите ему с этим.

Формат входных данных
Вводится три числа l, r, p (1 ≤ l ≤ r < p, r − l ≤ 2 × 10^7, p ≤ 10^9, p − простое число)

Формат выходных данных
Выведите одно число — искомую сумму.

Замечания
1/1 ≡ 1 (mod 7)
1/2 ≡ 4 (mod 7)
1/3 ≡ 5 (mod 7)
1/4 ≡ 2 (mod 7)
1/5 ≡ 3 (mod 7)

Тогда искомая сумма это 1 + 4 + 5 + 2 + 3 ≡ 1 (mod 7).

Примеры данных
Пример 1
Ввод: 1 5 7
Вывод: 1

Пример 2
Ввод: 3 10 31
Вывод: 4
 */

public class T11 {
    public static void main(String[] args) {
        long l, r, p;
        try (Scanner scanner = new Scanner(System.in)) {
            l = scanner.nextLong(); // 1 ≤ l ≤ r < p
            r = scanner.nextLong(); // r − l ≤ 2 × 10^7
            p = scanner.nextLong(); // p ≤ 10^9, p − простое число
        }
        System.out.println("TEST");
    }
}
