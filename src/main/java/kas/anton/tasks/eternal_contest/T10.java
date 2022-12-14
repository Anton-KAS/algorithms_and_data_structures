package kas.anton.tasks.eternal_contest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (12.12.2022)
 */

/*
Соня с Сашей купили торт в форме выпуклого многоугольника на n вершинах.
Они хотят разделить этот торт на две равные части одним строго вертикальным разрезом.
А, именно, линия разреза на торте должна быть параллельна координатной оси O_y

Найдите x-координату, вдоль которой надо сделать искомый разрез.

Формат входных данных
В первой строке вводится число n — количество вершин многоугольника (1 ≤ n ≤ 1000)
В следующих n строках записаны записаны координаты вершин торта-многоугольника в порядке обхода.
Гарантируется, что координаты — целые числа, не превосходящие по модулю 10^3

Формат выходных данных
Выведите одно число — искомую x-координату. Ответ надо выводить с точностью не меньше 10^−6

Замечание
Первый тест — это квадрат, разделенный на две равные части.

Примеры данных
Пример 1
Ввод:
4
0 0
0 2
2 2
2 0
Вывод: 1.000000000
 */

public class T10 {
    private static final BigDecimal accuracy = new BigDecimal(0.000001);
    public static int scale = 10;


    public static void main(String[] args) {
        int n; // количество вершин многоугольника (1 ≤ n ≤ 1000)
        BigDecimal xMin = null;
        BigDecimal xMax = null;
        List<BigDecimal[]> tops = new ArrayList<>(); // координаты — целые числа, не превосходящие по модулю 10^3
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                BigDecimal[] xy = new BigDecimal[2];
                xy[0] = new BigDecimal(scanner.nextInt()).setScale(scale, RoundingMode.HALF_UP);
                xy[1] = new BigDecimal(scanner.nextInt()).setScale(scale, RoundingMode.HALF_UP);
                tops.add(xy);
                if (xMin == null) xMin = xy[0];
                if (xMax == null) xMax = xy[0];
                xMin = xMin.min(xy[0]);
                xMax = xMax.max(xy[0]);
            }
        }
        BigDecimal xCentre;
        while (true) {
            xCentre = findCenter(xMin, xMax);
            BigDecimal xUsed = new BigDecimal(xCentre.toString()).setScale(scale, RoundingMode.HALF_UP);
            List<BigDecimal[]> newTops = pushTop(tops, xCentre);
            List<BigDecimal[]> leftTops = newTops.stream().filter(t -> t[0].compareTo(xUsed) <= 0).collect(Collectors.toList());
            List<BigDecimal[]> rightTops = newTops.stream().filter(t -> t[0].compareTo(xUsed) >= 0).collect(Collectors.toList());

            BigDecimal leftSqr = getSquare(leftTops);
            BigDecimal rightSqr = getSquare(rightTops);
            BigDecimal deltaSqr = leftSqr.subtract(rightSqr);
            if (deltaSqr.abs().compareTo(accuracy) <= 0) break;
            if (deltaSqr.compareTo(new BigDecimal(0)) > 0) {
                xMax = xCentre;
            } else {
                xMin = xCentre;
            }
        }
        System.out.println(xCentre.setScale(7, RoundingMode.HALF_UP).toPlainString());
    }

    public static BigDecimal getSquare(List<BigDecimal[]> tops) {
        int x = 0;
        int y = 1;
        BigDecimal result = new BigDecimal(0).setScale(scale, RoundingMode.HALF_UP);
        for (int i = 0; i < tops.size(); i++) {
            int j = i + 1;
            if (j >= tops.size()) j = 0;
            BigDecimal XiYj = tops.get(i)[x].multiply(tops.get(j)[y]);
            BigDecimal XjYi = tops.get(i)[y].multiply(tops.get(j)[x]);
            result = result.add(XiYj).subtract(XjYi);
        }
        result = result.abs();
        return result.divide(new BigDecimal(2), RoundingMode.HALF_UP);
    }

    public static BigDecimal getY(BigDecimal m, BigDecimal[] t1, BigDecimal[] t2) {
        int x = 0;
        int y = 1;
        return (m.subtract(t2[x])).multiply(t2[y].subtract(t1[y])).divide(t2[x].subtract(t1[x]), RoundingMode.HALF_UP).add(t2[y]);
    }

    public static BigDecimal findCenter(BigDecimal xMin, BigDecimal xMax) {
        BigDecimal xDelta = xMax.subtract(xMin);
        BigDecimal xCentre = xDelta.divide(new BigDecimal(2), RoundingMode.HALF_UP).add(xMin);
        return xCentre;
    }

    public static List<BigDecimal[]> pushTop(List<BigDecimal[]> tops, BigDecimal xCentre) {
        int x = 0;
        List<BigDecimal[]> newTops = new ArrayList<>();
        for (int i = 0; i < tops.size(); i++) {
            int j = i + 1;
            if (j >= tops.size()) j = 0;
            newTops.add(tops.get(i));
            if ((tops.get(i)[x].compareTo(xCentre) < 0 && tops.get(j)[x].compareTo(xCentre) > 0) ||
                    (tops.get(i)[x].compareTo(xCentre) > 0 && tops.get(j)[x].compareTo(xCentre) < 0)) {
                BigDecimal yCentre = getY(xCentre, tops.get(i), tops.get(j));
                BigDecimal[] nexXY = new BigDecimal[2];
                nexXY[0] = xCentre;
                nexXY[1] = yCentre;
                newTops.add(nexXY);
            }
        }
        return newTops;
    }
}
