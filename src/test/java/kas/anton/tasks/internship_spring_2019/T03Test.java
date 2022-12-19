package kas.anton.tasks.internship_spring_2019;

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
 * {@link T03}
 * @author Anton Komrachkov
 * @since (19.12.2022)
 */

/*
Пример 1
Ввод:
7 23 59
4
1 0 0
7 23 00
1 1 0
1 1 1
Вывод: 1 0 0
 */

@DisplayName("Стажировка весна 2019. Задача 3")
public class T03Test {
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
        T03.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("7 23 59\n" +
                        "4\n" +
                        "1 0 0\n" +
                        "7 23 00\n" +
                        "1 1 0\n" +
                        "1 1 1", "1 0 0"),
                Arguments.of("7 23 59\n" +
                        "4\n" +
                        "7 23 00\n" +
                        "1 1 0\n" +
                        "1 0 0\n" +
                        "1 1 1", "1 0 0"),
                Arguments.of("7 23 59\n" +
                        "4\n" +
                        "7 23 00\n" +
                        "7 23 59\n" +
                        "1 0 0\n" +
                        "1 1 1", "7 23 59"),
                Arguments.of("5 23 59\n" +
                        "4\n" +
                        "7 23 00\n" +
                        "0 23 58\n" +
                        "1 0 0\n" +
                        "1 1 1", "6 23 58")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
