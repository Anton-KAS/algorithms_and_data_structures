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
 * {@link T06}
 * @author Anton Komrachkov
 * @since (13.12.2022)
 */

/*
Пример 1
Ввод: 4\n2 1 4 6
Вывод: -1 -1

Пример 2
Ввод: 2\n1 2
Вывод: -1 -1

Пример 3
Ввод: 2\n2 1
Вывод: 1 2
 */

@DisplayName("Вечный контекст. Задача 6")
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
                Arguments.of("4\n2 1 4 6", "-1 -1"),
                Arguments.of("2\n1 2", "-1 -1"),
                Arguments.of("2\n2 1", "1 2"),
                Arguments.of("4\n1 2 3 4", "1 3"),
                Arguments.of("4\n1 1 1 1", "-1 -1"),
                Arguments.of("3\n1 1 1000000000", "2 3"),
                Arguments.of("2\n1000000000 1000000000", "-1 -1"),
                Arguments.of("10\n10 2 3 4 5 6 7 8 9 1", "1 10"),
                Arguments.of("10\n10 3 2 4 5 6 7 8 9 1", "-1 -1"),
                Arguments.of("9\n1 2 1 2 1 2 1 2 1", "1 3"),
                Arguments.of("9\n1 1 2 2 1 2 1 2 1", "2 3"),
                Arguments.of("3\n1 2 3", "1 3"),
                Arguments.of("8\n1 2 6 4 5 3 7 8", "3 6"),
                Arguments.of("9\n1 2 6 4 5 3 7 8 10", "-1 -1")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
