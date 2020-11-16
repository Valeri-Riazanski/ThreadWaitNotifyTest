package com.val.riazanski;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
       public static void main(String[] args) throws ExecutionException, InterruptedException {
        int wordValue = 30;
        int tr = wordValue / 2;
        Book book;
        book = new Book(wordValue);
        Runnable t0 = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < tr; i++) {
                    System.out.println(ConsoleColors.PURPLE + threadName + book.getList().toString() + ConsoleColors.RESET);
//                    System.out.println(Thread.currentThread().getState());
                    String str = book.createUpperWord(3);
                    System.out.println("task_0  i= " + i);
                try {
                    book.setUp(i, str);
                    book.setFlag();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(ConsoleColors.BLUE + threadName + book.getList().toString() + ConsoleColors.RESET);

            }
        };
        Runnable t1 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = tr; i < 2 * tr; i++) {
                    System.out.println(ConsoleColors.YELLOW + threadName + book.getList().toString() + ConsoleColors.RESET);
//                    System.out.println(Thread.currentThread().getState());
                    String str = book.createWord(5);
                    System.out.println("task_1  i= " + i);
                    book.setDown(i, str);
                    book.setFlag();
//                    System.out.println(ConsoleColors.RED + threadName + book.getList().toString() + ConsoleColors.RESET);

                }
            } catch (InterruptedException e) {
                {
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(t0);
        service.submit(t1);
        service.shutdown();
       }
}
