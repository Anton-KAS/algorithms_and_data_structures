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
import java.util.stream.Stream;

/**
 * {@link T12}
 *
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 15\n4 7 9
Вывод: 9
 */

@DisplayName("Вечный контекст. Задача 12")
public class T12Test {
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
        T12.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("15\n4 7 9", "9"),
                Arguments.of("19\n4 7 9", "13"),
                Arguments.of("25\n4 7 9", "13"),
                Arguments.of("15\n9 7 4", "9"),
                Arguments.of("15\n9 4 7", "9"),
                Arguments.of("15\n4 9 7", "9"),
                Arguments.of("12\n1 2 4", "12"),
                Arguments.of("11\n1 2 4", "11"),
                Arguments.of("10\n1 2 4", "10"),
                Arguments.of("9\n1 2 4", "9"),
                Arguments.of("8\n1 2 4", "8"),
                Arguments.of("7\n1 2 4", "7"),
                Arguments.of("6\n1 2 4", "6"),
                Arguments.of("5\n1 2 4", "5"),
                Arguments.of("4\n1 2 4", "4"),
                Arguments.of("3\n1 2 4", "3"),
                Arguments.of("2\n1 2 4", "2"),
                Arguments.of("1\n1 2 4", "1"),
                Arguments.of("1\n1 1 1", "1"),
                Arguments.of("2\n1 1 1", "2"),
                Arguments.of("3\n1 1 1", "3"),
                Arguments.of("10\n4 6 8", "4"),
                Arguments.of("10\n2 2 4", "5"),
                Arguments.of("600\n4 7 9", "582"), // Ответ посчитал мой же алгоритм
                Arguments.of("1000000000000000000\n1 2 3", "1000000000000000000"),
                Arguments.of("1000000000000000000\n2 3 4", "916666666666666666"), // Ответ посчитал мой же алгоритм
                Arguments.of("1000000000000000000\n100000 100000 100000", "10000000000000"),
                Arguments.of("1000\n876 754 750", "4"), // Ответ посчитал мой же алгоритм
                Arguments.of("10000\n876 754 750", "467"), // Ответ посчитал мой же алгоритм
                Arguments.of("100000\n876 754 750", "42124"), // Ответ посчитал мой же алгоритм
                Arguments.of("1000000\n876 754 750", "492124"), // Ответ посчитал мой же алгоритм
                Arguments.of("10000000\n876 754 750", "4992124"), // Ответ посчитал мой же алгоритм > 2 sec
                Arguments.of("100000000\n876 754 750", "49976372"), // Ответ посчитал мой же алгоритм > 15 sec
                Arguments.of("1000000000\n876 754 750", "499803100"), // Ответ посчитал мой же алгоритм > 19 sec
                Arguments.of("10000000000\n876 754 750", "4998086132"), // Ответ посчитал мой же алгоритм > 13 sec
                Arguments.of("100000000000\n876 754 750", "49980916452"), // Ответ посчитал мой же алгоритм > 14 sec
                Arguments.of("1000000000000\n876 754 750", "499809211776"), // Ответ посчитал мой же алгоритм > 10 sec
                Arguments.of("10000000000000\n876 754 750", "4998092117760"), // Ответ посчитал мой же алгоритм > 14 sec
                Arguments.of("100000000000000\n876 754 750", "49980921232732"), // Ответ посчитал мой же алгоритм > 12 sec
                Arguments.of("1000000000000000\n876 754 750", "499809212358824"), // Ответ посчитал мой же алгоритм > 12 sec
                Arguments.of("10000000000000000\n876 754 750", "4998092123588240"), // Ответ посчитал мой же алгоритм > 12 sec
                Arguments.of("100000000000000000\n876 754 750", "49980921235898152"), // Ответ посчитал мой же алгоритм > 12 sec
                Arguments.of("1000000000000000000\n876 754 750", "499809212359044528"), // Ответ посчитал мой же алгоритм > 12 sec
                Arguments.of("1000000000000000000\n1734 1321 1500", "999869345075127580"), // Ответ посчитал мой же алгоритм > 7 min
                Arguments.of("1000000000000000000\n3534 2621 3000", "?"), // очень долго....
                Arguments.of("1000000000000000000\n6534 5321 6000", "?"), // очень долго....
                Arguments.of("1000000000000000000\n12534 11321 12500", "?"), // очень долго....
                Arguments.of("1000000000000000000\n25234 22321 25000", "?"), // очень долго....
                Arguments.of("1000000000000000000\n51234 44321 50000", "?"), // очень долго....
                Arguments.of("1000000000000000000\n99998 99999 100000", "?") // очень долго....
        );
    }

    @ParameterizedTest
    @MethodSource("sourceNOK")
    public void testNOK(long[] givenData, long expected) {
        //when
        long result = T12.getNOK(givenData);

        //then
        Assertions.assertEquals(expected, result);
    }

    protected static Stream<Arguments> sourceNOK() {
        return Stream.of(
                Arguments.of(getLongArray(4, 7, 9), 252),
                Arguments.of(getLongArray(1, 1, 1), 1),
                Arguments.of(getLongArray(1, 2, 3), 6),
                Arguments.of(getLongArray(2, 3, 4), 12),
                Arguments.of(getLongArray(10, 37, 100000), 3700000),
                Arguments.of(getLongArray(123, 1234, 100000), 7589100000L),
                Arguments.of(getLongArray(1234, 12345, 100000), 152337300000L),
                Arguments.of(getLongArray(1234, 99999, 100000), 6169938300000L),
                Arguments.of(getLongArray(99998, 99999, 100000), 499985000100000L)
        );
    }

    @ParameterizedTest
    @MethodSource("sourceNOD")
    public void testNOD(long[] givenABC, long givenN, long expected) {
        //when
        long result = T12.getNod(givenABC, givenN);

        //then
        Assertions.assertEquals(expected, result);
    }

    protected static Stream<Arguments> sourceNOD() {
        return Stream.of(
                Arguments.of(getLongArray(4, 7, 9), 15, 1),
                Arguments.of(getLongArray(4, 8, 10), 16, 2),
                Arguments.of(getLongArray(9877, 568, 124), 3456, 1),
                Arguments.of(getLongArray(9874, 568, 124), 3456, 2),
                Arguments.of(getLongArray(99998, 99996, 100000), 1000000000000000000L, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("sourceOptABC")
    public void testOptABC(long[] givenABC, long[] expected) {
        //when
        long[] result = T12.optimiseABC(givenABC);

        //then
        Assertions.assertArrayEquals(expected, result);
    }

    protected static Stream<Arguments> sourceOptABC() {
        return Stream.of(
                Arguments.of(getLongArray(2, 4, 6), getLongArray(2)),
                Arguments.of(getLongArray(2, 4, 5), getLongArray(2, 5)),
                Arguments.of(getLongArray(2, 5, 4), getLongArray(2, 5)),
                Arguments.of(getLongArray(1, 2, 4), getLongArray(1)),
                Arguments.of(getLongArray(1, 1, 1), getLongArray(1)),
                Arguments.of(getLongArray(2, 3, 5), getLongArray(2, 3, 5))
        );
    }

    private static long[] getLongArray(long... longValue) {
//        for (int i = 0; i < longValue.length; i++) {
//
//        }
//        long[] ABC = new long[3];
//        ABC[0] = A;
//        ABC[1] = B;
//        ABC[2] = C;
        return longValue;
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
