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
 * {@link T04}
 *
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод:
5 2\n1 2 1 3 5
Вывод: 16

Пример 2
Ввод:
3 1\n99 5 85
Вывод: 10

Пример 3
Ввод:
1 10\n9999
Вывод: 0
 */

@DisplayName("Вечный контекст. Задача 4")
public class T04Test {
    private final InputStream stdin = System.in;
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test1(String givenData, String expected) {
        //when
        System.setIn(new ByteArrayInputStream(givenData.getBytes()));
        T04.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("5 2\n1 2 1 3 5", "16"),
                Arguments.of("3 1\n99 5 85", "10"),
                Arguments.of("1 10\n9999", "0"),
                Arguments.of("2 1\n100 199", "800"),
                Arguments.of("2 100\n100 199", "1699"),
                Arguments.of("5 100\n100 199 199 1 299999999", "700002507"),
                Arguments.of("1 1\n1", "8"),
                Arguments.of("9 10000\n1 10 100 1000 10000 1000000 10000000 100000000 1000000000", "9999099990"),
                Arguments.of("9 1\n1 10 100 1000 10000 1000000 10000000 100000000 1000000000", "8000000000")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }

//    @ParameterizedTest
//    @MethodSource("sourceMaxLong")
//    public void testMaxLong(int length, long expected) {
//        //when
//        long actual = T04.getMaxLong(length);
//
//        //then
//        Assertions.assertEquals(expected, actual);
//    }
//
//    private static Stream<Arguments> sourceMaxLong() {
//        return Stream.of(
//                Arguments.of(1, 9L),
//                Arguments.of(2, 99L),
//                Arguments.of(3, 999L),
//                Arguments.of(4, 9999L),
//                Arguments.of(15, 999999999999999L)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("sourceSubLong")
//    public void testSubLong(long number, int size, long expected) {
//        //when
//        long actual = T04.getSubLong(number, size);
//
//        //then
//        Assertions.assertEquals(expected, actual);
//    }
//
//    private static Stream<Arguments> sourceSubLong() {
//        return Stream.of(
//                Arguments.of(1, 0, 0L),
//                Arguments.of(10, 1, 0L),
//                Arguments.of(19, 1, 9L),
//                Arguments.of(100, 2, 0L),
//                Arguments.of(199, 2, 99L),
//                Arguments.of(1234, 2, 34L),
//                Arguments.of(12345, 1, 5L),
//                Arguments.of(12345, 5, 12345L)
//        );
//    }
//
//
//    @ParameterizedTest
//    @MethodSource("sourceGetNum")
//    public void testGetNum(long number, int size, int expected) {
//        //when
//        int actual = T04.getNum(number, size);
//
//        //then
//        Assertions.assertEquals(expected, actual);
//    }
//
//    private static Stream<Arguments> sourceGetNum() {
//        return Stream.of(
//                Arguments.of(1, 1, 1),
//                Arguments.of(10, 2, 1),
//                Arguments.of(19, 1, 9),
//                Arguments.of(100, 2, 0),
//                Arguments.of(199, 2, 9),
//                Arguments.of(1234, 2, 3),
//                Arguments.of(12345, 1, 5),
//                Arguments.of(12345, 5, 1)
//        );
//    }
}
