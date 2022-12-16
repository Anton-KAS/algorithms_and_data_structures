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
 * {@link T11}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 1 5 7
Вывод: 1

Пример 1
Ввод: 3 10 31
Вывод: 4
 */

@DisplayName("Вечный контекст. Задача 11")
public class T11Test {
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
        T11.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("1 5 7", "1"),
                Arguments.of("3 10 31", "4"),
                Arguments.of("1 1 7", "1"),
                Arguments.of("2 2 7", "4"),
                Arguments.of("3 3 7", "5"),
                Arguments.of("4 4 7", "2"),
                Arguments.of("5 5 7", "3"),
                Arguments.of("6 6 7", "6"),
                Arguments.of("7 7 8", "1"),
                Arguments.of("123 4567 56789", "294"),
                Arguments.of("1000000 20000000 1000000000", "723500000"),
                Arguments.of("1 20000000 1000000000", "130000000")
        );
    }

    @ParameterizedTest
    @MethodSource("sourceBinPower")
    public void testBinPowerRec(int x, int power, int mod, long expected) {
        //when
        long result = T11.binPowRec(x, power, mod);

        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceBinPower")
    public void testBinPower(int x, int power, int mod, long expected) {
        //when
        long result = T11.binPowerIter(x, power, mod);

        //then
        Assertions.assertEquals(expected, result);
    }

    protected static Stream<Arguments> sourceBinPower() {
        return Stream.of(
                Arguments.of(2, 2, 3, 1),
                Arguments.of(2, 3, 3, 2),
                Arguments.of(342, 34234223, 700, 188),
                Arguments.of(20000000, 1000000000 - 2, 1000000000, 0),
                Arguments.of(20000000 - 1, 1000000000 - 2, 1000000000, 40000001)
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
