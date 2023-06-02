package thread.work3;

//класс Counter, который имеет внутреннее целочисленное поле count. Создайте два потока,
// каждый из которых увеличивает count на 1 миллион раз. Используйте синхронизированный блок,
// чтобы гарантировать корректное обновление значения count
public class Counter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}

class Task6 extends Thread {

    private final Counter counter;

    Task6(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (Counter.class) {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
            System.out.println(getName() + " " + counter.getCount());
        }
    }
}

class MainCounter {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Task6 thread1 = new Task6(counter);
        Task6 thread2 = new Task6(counter);

        thread1.start();
        thread2.start();
    }
}