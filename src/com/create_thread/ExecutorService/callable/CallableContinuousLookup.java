package com.create_thread.ExecutorService.callable;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:12:18 PM</p>
 */
public class CallableContinuousLookup {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTask.Task task = new CallableTask.Task();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> future=executorService.submit(task);


        System.out.println(future.get());
        //Some other oprations
        System.out.println("Doing hello world");

        executorService.shutdown();

    }


    public static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(4000);
            return new Random().nextInt(100) ;
        }
    }
}
