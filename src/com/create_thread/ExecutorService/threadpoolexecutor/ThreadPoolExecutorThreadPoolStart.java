package com.create_thread.ExecutorService.threadpoolexecutor;

import java.util.concurrent.*;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/19/25</p>
 * <p>Time:6:33 AM</p>
 */
public class ThreadPoolExecutorThreadPoolStart {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                1,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2)
        );
        for (int i=1;i<=5;i++){
            final int finalI = i;
            threadPoolExecutor.execute(()->{

                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Thread pool executo "+finalI+" task Executed by "+Thread.currentThread().getName());
            });
        }
        threadPoolExecutor.shutdown();
    }
}
