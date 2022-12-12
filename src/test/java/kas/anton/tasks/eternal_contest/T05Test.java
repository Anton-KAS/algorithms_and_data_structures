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
 * {@link T05}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 4 7
Вывод: 4

Пример 2
Ввод: 10 100
Вывод: 9
 */

@DisplayName("Вечный контекст. Задача 5")
public class T05Test {
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
        T05.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("4 7", "4"),
                Arguments.of("10 100", "9"),
                Arguments.of("1 10", "9"),
                Arguments.of("1 1000", "27"),
                Arguments.of("1 1", "1"),
                Arguments.of("10 999", "18"),
                Arguments.of("10 1000", "18"),
                Arguments.of("200 1000", "8"),
                Arguments.of("111 111", "1"),
                Arguments.of("111 1000", "9"),
                Arguments.of("112 113", "0"),
                Arguments.of("112 112", "0"),
                Arguments.of("1 1000000000000000000", "162"),
                Arguments.of("10000000 1000000000000000000", "99")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
