package thread.work3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Создайте класс Task, у которого есть метод execute(), который выполняет задачу в течение некоторого времени.
// Создайте пул из пяти потоков и очередь задач. Каждый поток должен брать задачу из очереди и выполнять ее.
// Используйте синхронизированный блок и методы wait() и notify() для синхронизации доступа к очереди задач.
public class Task {
    synchronized
    public void execute() {
        System.out.println("Task started." + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task completed." + Thread.currentThread().getName());
    }
}

class PoolWorker extends Thread {
    private final Queue<Task> taskQueue;

    public PoolWorker(Queue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Task.class) {
                while (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = taskQueue.poll();
                task.execute();
                taskQueue.notifyAll();
            }
        }
    }
}

class MainTask {
    public static void main(String[] args) {
        Queue<Task> taskQueue = new ArrayDeque<>();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            taskQueue.add(new Task());
        }
        for (int i = 0; i < 5; i++) {
            service.submit(new PoolWorker(taskQueue));
        }
        service.shutdown();
    }
}