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
 * {@link T07}
 * @author Anton Komrachkov
 * @since (11.12.2022)
 */

/*
Пример 1
Ввод: 3\n1 2 3
Вывод: -1 -1

Пример 2
Ввод: 3\n1 3 1
Вывод: 1 2
 */

@DisplayName("Вечный контекст. Задача 7")
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
                Arguments.of("3\n1 2 3", "-1 -1"),
                Arguments.of("3\n1 3 1", "1 2"),
                Arguments.of("2\n2 1", "-1 -1"),
                Arguments.of("2\n2 2", "2 1"),
                Arguments.of("2\n1 2", "-1 -1"),
                Arguments.of("2\n1 1", "1 2"),
                Arguments.of("4\n2 3 4 1", "-1 -1"),
                Arguments.of("5\n3 4 2 5 1", "-1 -1"),
                Arguments.of("5\n3 4 4 5 1", "3 2"),
                Arguments.of("5\n2 1 4 5 3", "-1 -1"),
                Arguments.of("10\n1 2 3 4 5 6 7 8 9 10", "-1 -1"),
                Arguments.of("10\n1 1 2 3 4 5 6 7 8 9", "1 10"),
                Arguments.of("10\n2 1 2 3 4 5 6 7 8 9", "1 10"),
                Arguments.of("4\n4 1 1 2", "2 3"),
                Arguments.of("4\n4 1 2 1", "4 3"),
                Arguments.of("4\n4 2 1 1", "-1 -1"),
                Arguments.of("4\n2 2 4 1", "2 3"),
                Arguments.of("4\n2 3 4 4", "4 1"),
                Arguments.of("5\n2 3 4 5 1", "-1 -1"),
                Arguments.of("5\n2 3 4 3 1", "4 5"),
                Arguments.of("100\n1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100", "-1 -1"),
                Arguments.of("100\n2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 1", "-1 -1"),
                Arguments.of("100\n2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 100", "100 1")
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setIn(stdin);
    }
}
