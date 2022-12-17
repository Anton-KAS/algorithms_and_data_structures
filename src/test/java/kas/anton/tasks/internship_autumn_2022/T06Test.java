package kas.anton.tasks.internship_autumn_2022;

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
 * {@link T06}
 *
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
7
2 6
5 6
2 5
2 2
6 8
2 2
0 2
Вывод:
6
 */

@DisplayName("Стажировка осень 2022. Задача 6")
public class T06Test {
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
        T06.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("7\n" +
                                "2 6\n" +
                                "5 6\n" +
                                "2 5\n" +
                                "2 2\n" +
                                "6 8\n" +
                                "2 2\n" +
                                "0 2",
                        "6"),
                Arguments.of("7\n" +
                                "0 2\n" +
                                "2 2\n" +
                                "2 5\n" +
                                "6 8\n" +
                                "2 2\n" +
                                "5 6\n" +
                                "2 6",
                        "6"),
                Arguments.of("13\n" +
                                "2 10\n" +
                                "2 6\n" +
                                "2 6\n" +
                                "2 6\n" +
                                "2 6\n" +
                                "2 6\n" +
                                "2 6\n" +
                                "5 6\n" +
                                "2 5\n" +
                                "2 2\n" +
                                "6 8\n" +
                                "2 2\n" +
                                "0 2",
                        "6"),
                Arguments.of("4\n" +
                                "1 2\n" +
                                "3 4\n" +
                                "5 6\n" +
                                "7 8\n",
                        "1"),
                Arguments.of("6\n" +
                                "1 2\n" +
                                "3 4\n" +
                                "3 4\n" +
                                "4 6\n" +
                                "5 6\n" +
                                "7 8\n",
                        "2"),
                Arguments.of("13\n" +
                                "200000000 1000000000\n" +
                                "200000000 600000000\n" +
                                "200000000 600000000\n" +
                                "200000000 600000000\n" +
                                "200000000 600000000\n" +
                                "200000000 600000000\n" +
                                "200000000 600000000\n" +
                                "500000000 600000000\n" +
                                "200000000 500000000\n" +
                                "200000000 200000000\n" +
                                "600000000 800000000\n" +
                                "200000000 200000000\n" +
                                "0 200000000",
                        "6")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
