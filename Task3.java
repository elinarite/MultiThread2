package thread.work3;

//Создайте 2 потока, которые выводят все четные числа от 1 до 20 и все нечетные числа
//        от 1 до 20 соответственно. Каждый поток должен использовать метод run() для вывода чисел.
public class Task3 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
class MainTask3 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task3());
        thread1.start();
        Thread thread2 = new Thread(new Task3());
        thread2.start();
    }
}