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
 * {@link T00}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Шаблон для тестов
Пример 1
Ввод: 4\n0 0\n0 2\n2 2\n2 0
Вывод: 1.000000000
 */

@DisplayName("Вечный контекст. Задача ---")
public class T10Test {
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
        T00.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("4\n0 0\n0 2\n2 2\n2 0", "1.000000000")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
