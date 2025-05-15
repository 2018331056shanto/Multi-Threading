package com.create_thread.ExecutorService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/14/25</p>
 * <p>Time:8:19 AM</p>
 */
public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(3);

        System.out.println("Fixed thread Pool");
        for (int i=1;i<=5;i++){
            final  int task=i;
            executorService.execute(()->{
                try {
                    Thread.sleep(1000);

                System.out.println("Fixed Thread task " + task+ " executed by thread " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println("Calculating Time");

        List<Callable<String>> tasks=new ArrayList<>();

        for(int i=1;i<=5;i++){
            final  int task=i;
            tasks.add(()->{
                Thread.sleep(1000);
               return "Fixed Thread task " + task+ " executed by thread " + Thread.currentThread().getName();
            });
        }
        Instant start = Instant.now();

        List<Future<String>> results=executorService.invokeAll(tasks);

        Instant end = Instant.now();

        System.out.println("Time to complete 5 tasks "+Duration.between(start, end).toMillis());

        executorService.shutdown();
    }
}
