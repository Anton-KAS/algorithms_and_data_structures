package kas.anton.tasks;

import kas.anton.data_structure.linear.Stack;

import java.util.HashMap;
import java.util.Map;

/**
 * Правильная скобочная последовательность
 * авторское решение на python:
 * pairs = {’)’: ’(’, ’]’: ’[’, ’}’: ’{’}
 * s = input ()
 * stack = []
 * for c in s:
 *      stack . append (c)
 *      if len ( stack ) > 1 and c in pairs . keys () and stack [ -2] == pairs [ c ]:
 *          stack . pop ()
 *          stack . pop ()
 * if len ( stack ) == 0:
 *      print (’yes ’)
 * else :
 *      print (’no ’)
 * @author Anton Komrachkov
 * @since (10.12.2022)
 */

public class t001_bracket_sequence {
    public static Map<String, String> pairs = new HashMap<>();

    static {
        pairs.put("(", ")");
        pairs.put("{", "}");
        pairs.put("[", "]");
    }

    public static void main(String[] args) {
        String bracketSequence1 = "([{}[]{}({}{})])"; // Правильная
        String bracketSequence2 = "({[(){}{}[]})"; // Неправильная

        checkBracketSequence(bracketSequence1);
        checkBracketSequence(bracketSequence2);
    }

    public static void checkBracketSequence(String bracketSequence) {
        System.out.println("Имеем последовательность: " + bracketSequence);
        Stack.StackString stack = new Stack.StackString(); // Используем собственную реализацию стека
        System.out.println("Создаем стек: " + stack);
        for (String s : bracketSequence.split("")) {
            if (pairs.containsKey(s)) {
                stack.push(s);
                System.out.println(stack + "\t<- add " + s + " to stack");
            } else if (pairs.containsValue(s)) {
                if (pairs.get(stack.back()).equals(s)) {
                    stack.pop();
                    System.out.println(stack + "\t-> pop " + s + " from stack");
                }
            }
        }
        if (stack.size() == 0) System.out.println("Скобочная последовательность: правильная, так как стек пуст\n");
        else
            System.out.println("Скобочная последовательность: неправильная, так как в стеке остались: " + stack + "\n");
    }
}
