package com.create_thread.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/19/25</p>
 * <p>Time:5:40 AM</p>
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        for(int i=1;i<=5;i++){
            final int finalI = i;

            scheduledExecutorService.schedule(()->{
                System.out.println("Scheduled task number "+finalI +" executed by thread "+Thread.currentThread().getName());
            },5, TimeUnit.SECONDS);
        }


        scheduledExecutorService.shutdown();
    }
}
