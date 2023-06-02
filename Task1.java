package thread.work3;

// 1 уровень сложности: Задача:
//Создайте 5 потоков, которые выводят числа от 1 до 10.
//Каждый поток должен использовать метод run() для вывода чисел.
public class Task1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " " + i);
        }
    }
}

class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Task1 thread = new Task1();
            thread.start();
        }
    }
}