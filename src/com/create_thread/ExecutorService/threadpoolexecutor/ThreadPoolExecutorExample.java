package com.create_thread.ExecutorService.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/19/25</p>
 * <p>Time:6:22 AM</p>
 */
public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        // 1. Define a custom ThreadFactory
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("CustomThread-" + count.getAndIncrement());
                t.setDaemon(false);
                return t;
            }
        };

        // 2. Define a custom RejectedExecutionHandler
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        // 3. Define a BlockingQueue
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2); // small size for testing rejection

        // 4. Create ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                      // corePoolSize
                4,                      // maximumPoolSize
                10, TimeUnit.SECONDS,   // keepAliveTime
                workQueue,              // workQueue
                customThreadFactory,    // threadFactory
                handler                 // rejectedExecutionHandler
        );

        // 5. Allow core threads to time out
        executor.allowCoreThreadTimeOut(true);

        // 6. Submit tasks
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            });
        }

        // 7. Use prestartCoreThread and prestartAllCoreThreads
        executor.prestartCoreThread();           // Start one core thread early
        executor.prestartAllCoreThreads();       // Start all core threads

        // 8. Change pool sizes dynamically
        executor.setCorePoolSize(3);
        executor.setMaximumPoolSize(5);
        executor.setKeepAliveTime(5, TimeUnit.SECONDS);

        // 9. Remove a task from queue (not running yet)
        Runnable futureTask = () -> System.out.println("This task might be removed");
        executor.execute(futureTask);
        executor.remove(futureTask); // Try removing

        // 10. Show statistics
        System.out.println("Active Threads: " + executor.getActiveCount());
        System.out.println("Task Count: " + executor.getTaskCount());
        System.out.println("Completed Tasks: " + executor.getCompletedTaskCount());

        // 11. Access queue for debugging
        System.out.println("Queue size: " + executor.getQueue().size());

        // 12. Graceful shutdown
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("Executor terminated: " + executor.isTerminated());
    }
}
