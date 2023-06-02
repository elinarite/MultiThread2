package thread.work3;
//Создайте класс Printer, у которого есть метод print(String message), который должен выводить
// сообщение посимвольно с задержкой в 100 миллисекунд.
// Создайте два потока, каждый из которых вызывает метод print() с разными сообщениями.
// Используйте синхронизированный блок внутри метода print(),
// чтобы гарантировать последовательную печать символов сообщений.
public class Printer {

    public void print(String message){
        synchronized (this){
            char[] arr  = message.toCharArray();
            try{
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class MainPrinter{
    public static void main(String[] args) {
        String message1 = "11111";
        String message2 = "222222";
        Printer printer = new Printer();
        Thread thread1 = new Thread(()->printer.print(message1));
        Thread thread2 = new Thread(()->printer.print(message2));
        thread1.start();
        thread2.start();
    }
}
