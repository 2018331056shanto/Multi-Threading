package com.create_thread.virtual_thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/23/25</p>
 * <p>Time:10:35 AM</p>
 */
public class VirtualThread {

    public static void main(String[] args) throws InterruptedException {

        final int numberOfThreads=1000000;

        List<Thread> threads = new ArrayList<Thread>();

        Runnable runnable = (()->{
                System.out.println("Fetching data from API...");
                try {
                    Thread.sleep(10000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Fetch complete.");

        });


        for (int i = 0; i < numberOfThreads; i++) {
            Thread virtualThread=Thread.ofVirtual().unstarted(runnable);
//Virtual Thread are by default daemon means they work in background
            virtualThread.setName("VirtualThread-"+i);
            virtualThread.start();
            String str=String.format("VirtualThread %d", i);
            System.out.println(str);
            threads.add(virtualThread);
        }

        for (Thread thread : threads) {

            thread.join();
            System.out.println(thread.getName()+" Completed");


        }

        Runnable runnable1=(Runnable)()->{
            System.out.println(Thread.currentThread().getName());
        };

      Thread.ofVirtual().start(runnable).join();
    }
}
