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
 * {@link T03}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод:
5 5\n1 4 9 16 25\n2
Вывод: 24

Пример 2
Ввод:
6 4\n1 2 3 6 8 25\n5
Вывод: 31
 */

@DisplayName("Вечный контекст. Задача 3")
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
    public void test1(String givenData, String expected) {
        //when
        System.setIn(new ByteArrayInputStream(givenData.getBytes()));
        T03.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("5 5\n1 4 9 16 25\n2", "24"),
                Arguments.of("6 4\n1 2 3 6 8 25\n5", "31")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }

}
