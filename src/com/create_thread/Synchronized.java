package com.create_thread;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/5/2025<br>
 * Time:2:53 PM
 */
class SharedCounter1 {
    private int sharedCounter=0;
    public synchronized void increment() {
        sharedCounter++;
    }
    public synchronized int getSharedCounter() {
        return sharedCounter;
    }

}
public class Synchronized {
    public static void main(String[] args) throws InterruptedException {

        SharedCounter1 sharedCounter = new SharedCounter1();

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread-1 started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread-1 finished");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread-2 started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println("Thread-2 finished");

        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(sharedCounter.getSharedCounter());


    }
}
