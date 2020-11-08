package com.val.riazanski;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
       public static void main(String[] args) throws ExecutionException, InterruptedException {
        int wordValue = 30;
        int tr = wordValue / 3;
        Book book;
        book = new Book(wordValue);
        Runnable t1 = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < tr; i++) {
                    System.out.println(ConsoleColors.PURPLE + threadName + book.getList().toString() + ConsoleColors.RESET);
                    String str = book.createUpperWord(3);
                    System.out.println("task_1  i= " + i);
                try {
                    book.set(i, str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ConsoleColors.BLUE + threadName + book.getList().toString() + ConsoleColors.RESET);
                    //Thread.sleep((int) (10 * Math.random()));

            }
        };
        Runnable t2 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = tr; i < 2 * tr; i++) {
                    System.out.println(ConsoleColors.YELLOW + threadName + book.getList().toString() + ConsoleColors.RESET);
                    String str = book.createUpperWord(3);
                    System.out.println("task_2  i= " + i);
                    book.set(i, str);
                    book.setFlag();
                    System.out.println(ConsoleColors.RED + threadName + book.getList().toString() + ConsoleColors.RESET);

                    //Thread.sleep((int) (10 * Math.random()));
                }
            } catch (InterruptedException e) {
                {
                }
            }
        };
        Runnable t3 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                for (int i = 2 * tr; i < wordValue; i++) {
                    System.out.println(ConsoleColors.CYAN + threadName + book.getList().toString() + ConsoleColors.RESET);
                    String str = book.createUpperWord(3);
                    System.out.println("task_3  i= " + i);
                    book.set(i, str);
                    System.out.println(ConsoleColors.GREEN + threadName + book.getList().toString() + ConsoleColors.RESET);

                    //Thread.sleep((int) (10 * Math.random()));
                }
            } catch (InterruptedException e) {
                {
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(3);
        //for (Runnable task : t) service.submit(task);
        service.submit(t1);
        service.submit(t2);
        service.submit(t3);
        service.shutdown();
       }
}
