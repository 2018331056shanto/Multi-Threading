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
 * <p>Time:8:44 AM</p>
 */
public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        System.out.println("Started CachedThreadPool");


        for(int i=1;i<=5;i++){

            final int task = i;
            cachedThreadPool.execute(()->{
            try{
                Thread.sleep(1000);
                System.out.println("Cached Thread Task "+task+" executed by "+Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }});
        }
        List<Callable<String>> tasks=new ArrayList<>();

        for(int i=1;i<=5;i++){
            final  int task=i;
            tasks.add(()->{
                Thread.sleep(1000);
                return "Fixed Thread task " + task+ " executed by thread " + Thread.currentThread().getName();
            });
        }
        Instant start = Instant.now();

        List<Future<String>> results=cachedThreadPool.invokeAll(tasks);

        Instant end = Instant.now();

        System.out.println("Time to complete 5 tasks "+ Duration.between(start, end).toMillis());

        cachedThreadPool.shutdown();
    }
}
