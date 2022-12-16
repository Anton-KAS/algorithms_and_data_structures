package kas.anton.tasks.internship_autumn_2022;

import java.util.*;

/**
 * @author Anton Komrachkov
 * @since (16.12.2022)
 */

/*
Полное условие задачи в doc/T04.png
Вы прошли на стажировку и получили свое первое задание.
Ваш куратор попросил прочитать и обработать файл конфигурации, состоящий из строк трех типов:
• { — начало блока;
• } — конец блока;
• variable =< value > — присваивание в переменную с именем variable значения value.
value может быть двух типов:
• число, по модулю не превосходящее 109;
• название переменной, чье значение нужно присвоить.
В файле нет пробелов, и каждая операция записана в отдельной строке.
Имена всех переменных состоят из не более чем 10 строчных латинских букв (a, . . . , z).
Изначально все переменные имеют значение 0. Как только встречается выражение присваивания,
то необходимо сразу же его выполнить. Это значение сохраняется до конца блока (если, конечно, не будет перезаписано),
и после конца блока возвращается старое значение переменной.
Куратор хочет проверить, насколько хорошо вы анализируете код,
поэтому попросил вас вывести значение переменной variable1 после каждого присваивания вида variable1 = variable2.
 */

public class T04 {
    public static void main(String[] args) {
        String line; // 1 ≤ i ≤ n, 1 ≤ ai ≤ 10^3
        Map<String, Deque<Integer>> vars = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (line.equals("{")) addBlock(vars);
                if (line.equals("}")) removeBlock(vars);
                if (line.contains("=")) assignment(vars, line);
            }
        }

    }

    private static void assignment(Map<String, Deque<Integer>> vars, String line) {
        String[] splitLine = line.split("=");
        int value;
        if (splitLine[1].matches("-?\\d+")) value = Integer.parseInt(splitLine[1]);
        else {
            String varName = splitLine[1];
            value = getLastValue(vars, varName);
            System.out.println(value);
        }
        String varName = splitLine[0];
        if (vars.containsKey(varName)) updateValue(vars, varName, value);
        else addNewVar(vars, varName, value);
    }

    private static int getLastValue(Map<String, Deque<Integer>> vars, String name) {
        int value;
        Deque<Integer> values = vars.get(name);
        if (values != null && values.size() > 0) value = values.peekLast();
        else value = addNewVar(vars, name, 0);
        return value;
    }

    private static void addBlock(Map<String, Deque<Integer>> vars) {
        for (Deque<Integer> value : vars.values()) {
            if (value.size() > 0) {
                value.addLast(value.peekLast());
            }
        }
    }

    private static void removeBlock(Map<String, Deque<Integer>> vars) {
        for (Deque<Integer> value : vars.values()) {
            if (value.size() > 0) value.removeLast();
        }
    }

    private static int addNewVar(Map<String, Deque<Integer>> vars, String name, int var) {
        Deque<Integer> varList = new ArrayDeque<>();
        varList.addLast(var);
        vars.put(name, varList);
        return var;
    }

    private static void updateValue(Map<String, Deque<Integer>> vars, String name, int var) {
        vars.get(name).removeLast();
        vars.get(name).addLast(var);
    }

}
