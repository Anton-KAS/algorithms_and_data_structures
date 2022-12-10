package komrachkov.anton.data_structure.linear;

import java.util.ArrayList;
import java.util.List;

/**
 * Линейная структура данных Стек
 * <br> Добавляем только в конец списка
 * <br> Можем посмотреть только последний элемент в стеке
 * <br> Можем получить и удалить последний элемент из стека
 * @author Anton Komrachkov
 * @since (10.12.2022)
 */

public class Stack {
    public static void main(String[] args) {
        StackInt stackInt = new StackInt();
        stackInt.push(5);
        stackInt.push(6);
        stackInt.push(2);
        stackInt.push(3);
        stackInt.push(9);
        stackInt.back();
        stackInt.pop();
        stackInt.pop();
        stackInt.pop();
        stackInt.pop();
        stackInt.pop();
        stackInt.pop();
    }

    public static void printStack(StackInt stackInt) {
        System.out.println("| " + stackInt);
    }

}

class StackInt {
    private List<Integer> instance;

    public StackInt() {
        this.instance = new ArrayList<>();
        System.out.println("| " + instance + "\t - Создали пустой стек");
    }

    public void push(int i) {
        instance.add(i);
        System.out.println("| " + instance + "\t <- Положили " + i + " в конец стека");
    }

    public Integer back() {
        if (instance.size() < 1) {
            System.out.println("| " + instance + "\t - Посмотрели значение последнего элемента в стеке: Стек пуст");
            return null;
        }
        int i = instance.get(instance.size() - 1);
        System.out.println("| " + instance + "\t - Получили значение последнего элемента в стеке:" + i);
        return i;
    }

    public Integer pop() {
        if (instance.size() < 1) {
            System.out.println("| " + instance + "\t - Невозможно получить элемент из стека: Стек пуст");
            return null;
        }
        int i = instance.get(instance.size() - 1);
        instance.remove(instance.size() - 1);
        System.out.println("| " + instance + "\t -> Забрали " + i + " из конца стека");
        return i;
    }

    public int size() {
        return instance.size();
    }

    @Override
    public String toString() {
        return instance.toString();
    }
}
