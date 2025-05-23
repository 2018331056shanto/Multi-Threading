package com.create_thread.virtual_thread;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/23/25</p>
 * <p>Time:10:25 AM</p>
 */
public class PlatformThread {

    private static HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) throws InterruptedException {
        final int numberOfThreads = 20_000;
        List<Thread> threads = new ArrayList<Thread>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Fetching data from API...");
                try {
                    Thread.sleep(10000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Fetch complete.");
            }
        };
        for (int i = 0; i < numberOfThreads; i++) {
            //Platform Thread whenever we create this it will be tied with my operating system
//            level
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("Thread " + i);
            thread.start();
            String str=String.format("Thread %d", i);
            System.out.println(str);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
            System.out.println(thread.getName()+" Completed");
        }
    }
}


// Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
//  	at java.base/java.lang.Thread.start0(Native Method)
// 	at java.base/java.lang.Thread.start(Thread.java:809) * 	at com.create_thread.virtual_thread.PlatformThread.main(PlatformThread.java:38)
//
