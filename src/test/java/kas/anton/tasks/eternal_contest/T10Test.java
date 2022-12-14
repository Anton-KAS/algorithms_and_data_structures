package kas.anton.tasks.eternal_contest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static kas.anton.tasks.eternal_contest.T10.SCALE;

/**
 * {@link T10}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Шаблон для тестов
Пример 1
Ввод: 4\n0 0\n0 2\n2 2\n2 0
Вывод: 1.000000000
 */

@DisplayName("Вечный контекст. Задача 10")
public class T10Test {
    private final InputStream stdin = System.in;
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testMain(String givenData, String expected) {
        //when
        System.setIn(new ByteArrayInputStream(givenData.getBytes()));
        T10.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("4\n0 0\n0 2\n2 2\n2 0", "1.0000000"),
                Arguments.of("5\n0 0\n0 2\n1 3\n2 2\n2 0", "1.0000000"),
                Arguments.of("6\n0 0\n0 2\n1 3\n2 2\n2 0\n1 -1", "1.0000000"),
                Arguments.of("6\n0 0\n0 2\n1 3\n1 4\n2 2\n2 0", "1.0635083"),
                Arguments.of("4\n0 0\n1 2\n0 3\n2 4\n", "1.0000000"),
                Arguments.of("3\n2 1\n4 5\n7 8", "4.2613871"),
                Arguments.of("5\n3 4\n5 11\n12 8\n9 5\n5 6", "7.0770372"),
                Arguments.of("6\n-1000 -1000\n-1000 1000\n0 0\n1000 1000\n1000 -1000\n0 0", "0.0000000")
        );
    }

    @ParameterizedTest
    @MethodSource("sourceSqr")
    public void testSqr(List<BigDecimal[]> givenData, BigDecimal expected) {
        //when
        BigDecimal result = T10.getSquare(givenData);

        //then
        Assertions.assertEquals(expected, result);
    }

    protected static Stream<Arguments> sourceSqr() {
        return Stream.of(
                Arguments.of(getTops(2, 1, 4, 5, 7, 8), new BigDecimal(3).setScale(SCALE, RoundingMode.HALF_UP)),
                Arguments.of(getTops(0, 0, 0, 2, 2, 2, 2, 0), new BigDecimal(4).setScale(SCALE, RoundingMode.HALF_UP)),
                Arguments.of(getTops(-2, -2, -2, 2, 2, 2, 2, -2), new BigDecimal(16).setScale(SCALE, RoundingMode.HALF_UP)),
                Arguments.of(getTops(3, 4, 5, 11, 12, 8, 9, 5, 5, 6), new BigDecimal(30).setScale(SCALE, RoundingMode.HALF_UP))
        );
    }

    private static List<BigDecimal[]> getTops(int...xys) {
        List<BigDecimal[]> tops = new ArrayList<>();
        for (int i = 0; i < xys.length; i++) {
            BigDecimal[] xy = new BigDecimal[2];
            tops.add(xy);
            xy[0] = new BigDecimal(xys[i]);
            i++;
            xy[1] = new BigDecimal(xys[i]);
        }
        return tops;
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
