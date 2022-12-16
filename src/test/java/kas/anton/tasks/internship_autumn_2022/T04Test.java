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
 * {@link T04}
 *
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Пример 1
Ввод:
thats=zero
a=10
ten=a
aboba=ten
ten=-10
{
b=a
a=1337
c=a
{
d=a
e=aboba
}
}
lol=a
Вывод:
0
10
10
10
1337
1337
10
10
 */

@DisplayName("Стажировка осень 2022. Задача 4")
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
                Arguments.of("thats=zero\n" +
                                "a=10\n" +
                                "ten=a\n" +
                                "aboba=ten\n" +
                                "ten=-10\n" +
                                "{\n" +
                                "b=a\n" +
                                "a=1337\n" +
                                "c=a\n" +
                                "{\n" +
                                "d=a\n" +
                                "e=aboba\n" +
                                "}\n" +
                                "}\n" +
                                "lol=a",
                        "0\n" +
                                "10\n" +
                                "10\n" +
                                "10\n" +
                                "1337\n" +
                                "1337\n" +
                                "10\n" +
                                "10")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
