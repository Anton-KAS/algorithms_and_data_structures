package kas.anton.tasks.internship_spring_2022;

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
 * {@link T01}
 *
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Пример 1
Ввод: 3 5 1
Вывод: NO

Пример 2
Ввод: 5 3 1
Вывод: YES
 */

@DisplayName("Стажировка весна 2022. Задача 1")
public class T01Test {
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
        T01.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("3 5 1", "NO"),
                Arguments.of("5 3 1", "YES"),
                Arguments.of("5 3 2", "NO"),
                Arguments.of("6 2 2", "YES"),
                Arguments.of("1000000000 1 10", "NO"),
                Arguments.of("999999999 1 10", "YES"),
                Arguments.of("1000000000 2 10", "YES")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
