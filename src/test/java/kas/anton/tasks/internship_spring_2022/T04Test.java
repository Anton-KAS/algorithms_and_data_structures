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
 * {@link T04}
 *
 * @author Anton Komrachkov
 * @since (17.12.2022)
 */

/*
Пример 1
Ввод: 4 4
Вывод: 2
 */

@DisplayName("Стажировка весна 2022. Задача 4")
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
    public void testMain(String givenData, String expected) {
        //when
        System.setIn(new ByteArrayInputStream(givenData.getBytes()));
        T04.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("4 4", "2"),
                Arguments.of("3 3", "0"),
                Arguments.of("5 5", "0"),
                Arguments.of("8 4", "0"),
                Arguments.of("9 4", "0"),
                Arguments.of("7 4", "1"),
                Arguments.of("4 7", "1"),
                Arguments.of("49 49", "601080390"),
                Arguments.of("50 50", "0")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
