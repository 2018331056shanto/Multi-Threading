package com.create_thread.ExecutorService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/14/25</p>
 * <p>Time:8:09 AM</p>
 */
public class SingleThreadExecutor {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService singleThreadExecutor= Executors.newSingleThreadExecutor();

        System.out.println("Single Thread Executor");

        for (int i = 1; i <= 5; i++) {
            final int task=i;

            singleThreadExecutor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Single thread task " + task + " executed by thread " + Thread.currentThread().getName());
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        List<Callable<String>> tasks=new ArrayList<>();

        for(int i=1;i<=5;i++){
            final  int task=i;
            tasks.add(()->{
                Thread.sleep(1000);
                return "Single Thread task " + task+ " executed by thread " + Thread.currentThread().getName();
            });
        }
        Instant start = Instant.now();

        List<Future<String>> results=singleThreadExecutor.invokeAll(tasks);

        Instant end = Instant.now();

        System.out.println("Time to complete 5 tasks "+ Duration.between(start, end).toMillis());

        singleThreadExecutor.shutdown();
    }
}
