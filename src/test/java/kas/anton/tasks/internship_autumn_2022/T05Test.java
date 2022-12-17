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
 * {@link T05}
 *
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
5 3
ad
a
abc
aboba
b
3 a
2 ab
1 b
Вывод:
4
4
5
 */

@DisplayName("Стажировка осень 2022. Задача 5")
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
                Arguments.of("5 3\n" +
                                "ad\n" +
                                "a\n" +
                                "abc\n" +
                                "aboba\n" +
                                "b\n" +
                                "3 a\n" +
                                "2 ab\n" +
                                "1 b",
                        "4\n" +
                                "4\n" +
                                "5"),
                Arguments.of("5 3\n" +
                                "ad\n" +
                                "a\n" +
                                "abc\n" +
                                "aboba\n" +
                                "b\n" +
                                "3 c\n" +
                                "2 ab\n" +
                                "1 b",
                        "-1\n" +
                                "4\n" +
                                "5")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
