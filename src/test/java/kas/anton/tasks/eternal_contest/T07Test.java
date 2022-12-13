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
 * {@link T07}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 3\n1 2 3
Вывод: -1 -1

Пример 2
Ввод: 3\n1 3 1
Вывод: 1 2
 */

@DisplayName("Вечный контекст. Задача 7")
public class T07Test {
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
        T07.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("3\n1 2 3", "-1 -1"),
                Arguments.of("3\n1 3 1", "1 2"),
                Arguments.of("2\n2 1", "-1 -1"),
                Arguments.of("2\n2 2", "2 1"),
                Arguments.of("2\n1 2", "-1 -1"),
                Arguments.of("2\n1 1", "1 2"),
                Arguments.of("4\n2 3 4 1", "-1 -1"),
                Arguments.of("5\n3 4 2 5 1", "-1 -1"),
                Arguments.of("5\n3 4 4 5 1", "3 2"),
                Arguments.of("5\n2 1 4 5 3", "-1 -1"),
                Arguments.of("10\n1 2 3 4 5 6 7 8 9 10", "-1 -1"),
                Arguments.of("10\n1 1 2 3 4 5 6 7 8 9", "1 10"),
                Arguments.of("10\n2 1 2 3 4 5 6 7 8 9", "1 10"),
                Arguments.of("4\n4 1 1 2", "2 3"),
                Arguments.of("4\n4 1 2 1", "4 3"),
                Arguments.of("4\n4 2 1 1", "-1 -1"),
                Arguments.of("4\n2 2 4 1", "2 3"),
                Arguments.of("4\n2 3 4 4", "4 1"),
                Arguments.of("5\n2 3 4 5 1", "-1 -1"),
                Arguments.of("5\n2 3 4 3 1", "4 5")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
