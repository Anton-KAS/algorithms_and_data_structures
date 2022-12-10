package komrachkov.anton.data_structure.linear;

/**
 * Очередь. FIFO - первый пришел, первый ушел
 * <br>Добавляем только в конец очереди
 * <br>Забираем только из начала очереди
 * <br>Можно реализовать с помощью двух стеков
 * @author Anton Komrachkov
 * @since (10.12.2022)
 */

public class Queue {
    public static void main(String[] args) {
        QueueInt queueInt = new QueueInt();
        queueInt.push(6);
        queueInt.print();
        queueInt.push(5);
        queueInt.print();
        queueInt.push(1);
        queueInt.print();
        queueInt.pop();
        queueInt.print();
        queueInt.push(9);
        queueInt.print();
        queueInt.push(10);
        queueInt.print();
    }

    public static class QueueInt {
        private Stack.StackInt stackIn = new Stack.StackInt();
        private Stack.StackInt stackOut = new Stack.StackInt();

        public void push(int i) {
            stackIn.push(i);
        }

        public void pop() {
            if (stackOut.size() == 0) {
                while (stackIn.size() > 0) {
                    stackOut.push(stackIn.pop());
                }
            }
        }

        public void print() {
            System.out.println("OUT <- " + stackOut.toStringRevers() + " | " + stackIn + " <- IN");
        }
    }
}
