package thread.work3;

import java.util.Scanner;

//Создайте класс SharedResource, у которого есть целочисленное поле value. Создайте три потока,
// один из которых устанавливает значение value, а два других потока пытаются получить это значение.
// Используйте синхронизированный блок и метод wait() и notify() для синхронизации потоков.
public class SharedResource {
    private int value;

    public SharedResource(int value) {
        this.value = value;
    }

    public SharedResource() {
    }

    public synchronized int getValue() {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
        notifyAll();
    }
}

class MainSharedResource {

    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        Scanner scanner = new Scanner(System.in);
        Thread threadSetter = new Thread(() -> {
            System.out.println("please input value");
            sharedResource.setValue(scanner.nextInt());
        });
        Thread threadGetter1 = new Thread(() -> {
            System.out.println("getValue is " + sharedResource.getValue());
        });
        Thread threadGetter2 = new Thread(() -> {
            System.out.println("getValue is " + sharedResource.getValue());
        });
        threadSetter.start();
        threadGetter1.start();
        threadGetter2.start();
    }
}