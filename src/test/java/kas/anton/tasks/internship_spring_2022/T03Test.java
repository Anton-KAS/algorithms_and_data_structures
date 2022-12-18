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
 * {@link T03}
 *
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Пример 1
Ввод:
2
3 4
Вывод: 3

Пример 2
Ввод:
5
1 1 1 1 1
Вывод: 2
 */

@DisplayName("Стажировка весна 2022. Задача 3")
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
                Arguments.of("2\n3 4", "3"),
                Arguments.of("5\n1 1 1 1 1", "2"),
                Arguments.of("5\n1 1000000000000000000 1 1 1", "1000000010"),
                Arguments.of("5\n1 1000000000000000000 900000000000000000 800000000000000000 1", "1000000005")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
