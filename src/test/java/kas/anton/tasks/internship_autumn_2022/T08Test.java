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
 * {@link T08}
 *
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
2 3
ATSR
ASR
S R
AT R
A R
Вывод:
0
1
2
 */

@DisplayName("Стажировка осень 2022. Задача 8")
public class T08Test {
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
        T08.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("2 3\n" +
                                "ATSR\n" +
                                "ASR\n" +
                                "S R\n" +
                                "AT R\n" +
                                "A R",
                        "0\n" +
                                "1\n" +
                                "2"),
                Arguments.of("2 3\n" +
                                "ATSR\n" +
                                "ASR\n" +
                                "S R\n" +
                                "AT R\n" +
                                "ATS R",
                        "0\n" +
                                "1\n" +
                                "1")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
