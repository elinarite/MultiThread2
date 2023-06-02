package thread.work3;

import java.util.concurrent.TimeUnit;

//Создайте 4 потока, которые выводят сообщение "Hello, World!" каждый с задержкой в 1 секунду.
//Каждый поток должен использовать метод run() для вывода сообщения.
//Задача:
public class Task4 extends Thread {
    @Override
    public void run() {
        System.out.println(getName() + " Hello, World!");
    }
}

class MainTask4 {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 4; i++) {
                Task4 tread = new Task4();
                TimeUnit.SECONDS.sleep(1);
                tread.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}