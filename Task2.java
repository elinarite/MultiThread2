package thread.work3;

//Создайте 3 потока, которые вычисляют и выводят квадраты чисел от 1 до 10.
//Каждый поток должен использовать метод run() для вычисления и вывода квадратов.
public class Task2 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + Math.pow(i, 2));
        }
    }
}

class MainTask2 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Task2());
            thread.start();
        }
    }
}