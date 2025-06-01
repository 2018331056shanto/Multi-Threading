package com.create_thread.ExecutorService.callable;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:12:29 PM</p>
 */
public class CallableWithMultipleTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
        for(int i=0;i<100;i++){
            Future<Integer> future = executorService.submit(new Task());
            futures.add(future);
        }

        System.out.println("Hello from Bangladesh");
        Instant start = Instant.now();
        for(Future<Integer> future : futures){
            System.out.println(future.get());
        }
        Instant end = Instant.now();

        System.out.println(String.format("Total time: %d ",Duration.between(start, end).toMillis()));
        System.out.println("Hello from Afganisthan");

    }

    public static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(100);
            return new Random().nextInt(100) ;
        }
    }
}
