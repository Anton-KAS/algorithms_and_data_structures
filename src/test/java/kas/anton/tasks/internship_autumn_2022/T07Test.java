package kas.anton.tasks.internship_autumn_2022;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.print.DocFlavor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

/**
 * {@link T07}
 *
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
hello
3
1 5
1 2
2 5
Вывод:
9
2
3
 */

@DisplayName("Стажировка осень 2022. Задача 7")
public class T07Test {
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
        T07.main(null);

        //then
        Assertions.assertEquals(expected + "\n", outputStreamCaptor.toString());
    }

    protected static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("hello\n" +
                                "3\n" +
                                "1 5\n" +
                                "1 2\n" +
                                "2 5",
                        "9\n" +
                                "2\n" +
                                "3"),
                Arguments.of(getBigLine() + "\n" +
                                "18\n" +
                                "1 5\n" +
                                "1 200\n" +
                                "10 200\n" +
                                "100 200\n" +
                                "1 2000\n" +
                                "10 2000\n" +
                                "100 2000\n" +
                                "1000 2000\n" +
                                "1 10000\n" +
                                "10 10000\n" +
                                "100 10000\n" +
                                "1000 10000\n" +
                                "1 100000\n" +
                                "10 100000\n" +
                                "100 100000\n" +
                                "1000 100000\n" +
                                "10000 100000\n" +
                                "2 5",
                        "11\n" +
                                "271\n" +
                                "279\n" +
                                "278\n" +
                                "279\n" +
                                "270\n" +
                                "284\n" +
                                "294\n" +
                                "279\n" +
                                "270\n" +
                                "284\n" +
                                "294\n" +
                                "279\n" +
                                "270\n" +
                                "284\n" +
                                "294\n" +
                                "290\n" +
                                "4")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }

    private static String getBigLine() {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < 100000) {
            stringBuilder.append("qwertyuiopasdfghjklzxcvbnm");
        }
        return stringBuilder.toString();
    }
}
