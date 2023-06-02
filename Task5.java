package thread.work3;
//Создайте 3 потока, каждый из которых выводит свое имя 5 раз.
// Каждый поток должен использовать метод run() для вывода имени.

public class Task5 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }

    }
}

class MainTask5 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread tread = new Thread(new Task5());
            tread.start();
        }
    }
}