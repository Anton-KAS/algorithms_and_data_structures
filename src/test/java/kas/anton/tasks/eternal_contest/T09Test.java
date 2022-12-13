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
 * {@link T09}
 *
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 5\n35\n40\n101\n59\n63
Вывод: 235
 */

@DisplayName("Вечный контекст. Задача 9")
public class T09Test {
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
        T09.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("5\n35\n40\n101\n59\n63", "235"),
                Arguments.of("5\n35\n40\n101\n69\n63", "239"),
                Arguments.of("5\n35\n40\n100\n69\n63", "307"),
                Arguments.of("5\n35\n40\n101\n299\n63", "239"),
                Arguments.of("5\n200\n150\n300\n299\n63", "413"),
                Arguments.of("0\n", "0"),
                Arguments.of("1\n200\n", "200"),
                Arguments.of("2\n200\n100\n", "200"),
                Arguments.of("3\n200\n100\n200\n", "300"),
                Arguments.of("3\n200\n0\n200\n", "200"),
                Arguments.of("3\n100\n0\n200\n", "300"),
                Arguments.of("3\n200\n0\n0\n", "200"),
                Arguments.of("3\n100\n100\n200\n", "400"),
                Arguments.of("100\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n" +
                        "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n", "100"),
                Arguments.of("10\n300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n", "1500"),
                Arguments.of("20\n300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n", "3000"),
                Arguments.of("30\n300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n", "4500"),
                Arguments.of("40\n300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                        "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n", "6000"),
                Arguments.of("100\n300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n" +
                                "300\n300\n300\n300\n300\n300\n300\n300\n300\n300\n"
                        , "15000"),
                Arguments.of("100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n" +
                                "100\n1\n300\n1\n300\n1\n300\n1\n300\n1\n300\n"
                        , "15000")
                );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
