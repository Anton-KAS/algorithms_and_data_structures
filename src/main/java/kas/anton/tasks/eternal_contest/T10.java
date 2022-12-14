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
    private final static BigDecimal ACCURACY = new BigDecimal("0.000001");
    public final static int SCALE = 10;
    public final static int FINAL_SCALE = 7;
    private final static int X = 0;
    private final static int Y = 1;


    public static void main(String[] args) {
        int n; // количество вершин многоугольника (1 ≤ n ≤ 1000)
        List<BigDecimal[]> tops = new ArrayList<>(); // координаты — целые числа, не превосходящие по модулю 10^3
        BigDecimal xMin = null;
        BigDecimal xMax = null;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                BigDecimal[] xy = new BigDecimal[2];
                xy[X] = new BigDecimal(scanner.nextInt()).setScale(SCALE, RoundingMode.HALF_UP);
                xy[Y] = new BigDecimal(scanner.nextInt()).setScale(SCALE, RoundingMode.HALF_UP);
                tops.add(xy);
                if (xMin == null) xMin = xy[X];
                if (xMax == null) xMax = xy[X];
                xMin = xMin.min(xy[X]);
                xMax = xMax.max(xy[X]);
            }
        }
        BigDecimal xCentre;
        while (true) {
            assert xMax != null;
            xCentre = findCenter(xMin, xMax);
            BigDecimal xUsed = new BigDecimal(xCentre.toString());
            List<BigDecimal[]> newTops = getNewTops(tops, xCentre);
            List<BigDecimal[]> leftTops = newTops.stream().filter(t -> t[0].compareTo(xUsed) <= 0).collect(Collectors.toList());
            List<BigDecimal[]> rightTops = newTops.stream().filter(t -> t[0].compareTo(xUsed) >= 0).collect(Collectors.toList());

            BigDecimal leftSqr = getSquare(leftTops);
            BigDecimal rightSqr = getSquare(rightTops);
            BigDecimal deltaSqr = leftSqr.subtract(rightSqr);

            if (deltaSqr.abs().compareTo(ACCURACY) <= 0) break;

            if (deltaSqr.compareTo(new BigDecimal(0)) > 0) xMax = xCentre;
            else xMin = xCentre;
        }
        System.out.println(xCentre.setScale(FINAL_SCALE, RoundingMode.HALF_UP).toPlainString());
    }

    public static BigDecimal getSquare(List<BigDecimal[]> tops) {
        BigDecimal result = new BigDecimal(0).setScale(SCALE, RoundingMode.HALF_UP);
        for (int i = 0; i < tops.size(); i++) {
            int j = (i + 1) >= tops.size() ? 0 : i + 1;
            BigDecimal XiYj = tops.get(i)[X].multiply(tops.get(j)[Y]);
            BigDecimal XjYi = tops.get(i)[Y].multiply(tops.get(j)[X]);
            result = result.add(XiYj).subtract(XjYi);
        }
        return result.abs().divide(new BigDecimal(2), RoundingMode.HALF_UP);
    }

    public static BigDecimal getY(BigDecimal m, BigDecimal[] t1, BigDecimal[] t2) {
        return (m.subtract(t2[X])).multiply(t2[Y].subtract(t1[Y])).divide(t2[X].subtract(t1[X]), RoundingMode.HALF_UP).add(t2[Y]);
    }

    public static BigDecimal findCenter(BigDecimal xMin, BigDecimal xMax) {
        return xMax.subtract(xMin).divide(new BigDecimal(2), RoundingMode.HALF_UP).add(xMin);
    }

    public static List<BigDecimal[]> getNewTops(List<BigDecimal[]> tops, BigDecimal xCentre) {
        List<BigDecimal[]> newTops = new ArrayList<>();
        for (int i = 0; i < tops.size(); i++) {
            int j = (i + 1) >= tops.size() ? 0 : i + 1;
            newTops.add(tops.get(i));
            if ((tops.get(i)[X].compareTo(xCentre) < 0 && tops.get(j)[X].compareTo(xCentre) > 0) ||
                    (tops.get(i)[X].compareTo(xCentre) > 0 && tops.get(j)[X].compareTo(xCentre) < 0)) {
                BigDecimal[] nexXY = new BigDecimal[2];
                nexXY[X] = xCentre;
                nexXY[Y] = getY(xCentre, tops.get(i), tops.get(j));
                newTops.add(nexXY);
            }
        }
        return newTops;
    }
}
