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
 * {@link T02}
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
5
MICHAIL VLADISLAV GRIGORY
VLADISLAV MICHAIL GRIGORY
IVAN ILYA VLADIMIR
ANDREY VLADIMIR ILYA
VLADIMIR IVAN ANDREY
Вывод: 2
 */

@DisplayName("Стажировка осень 2022. Задача 2")
public class T02Test {
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
        T02.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("5\n" +
                        "MICHAIL VLADISLAV GRIGORY\n" +
                        "VLADISLAV MICHAIL GRIGORY\n" +
                        "IVAN ILYA VLADIMIR\n" +
                        "ANDREY VLADIMIR ILYA\n" +
                        "VLADIMIR IVAN ANDREY", "2"),
                Arguments.of("5\n" +
                        "MICHAIL VLADISLAV GRIGORY\n" +
                        "VLADISLAV MICHAIL GRIGORY\n" +
                        "IVAN ILYA VLADIMIR\n" +
                        "ILYA IVAN VLADIMIR\n" +
                        "VLADIMIR IVAN ILYA\n" +
                        "ANDREY VLADIMIR ILYA\n" +
                        "VLADIMIR IVAN ANDREY", "3")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
