package com.create_thread;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/4/2025<br>
 * Time:4:22 PM
 */

class MyThreadRunnable implements Runnable {
    private Thread thread;
    private String threadName;

    public MyThreadRunnable(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        System.out.println("Running : " + threadName);
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread: " + threadName + ", " + i);

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}

public class CreateThreadUsingRunnableInterface {
    public static void main(String[] args) {
        MyThreadRunnable myThreadRunnable = new MyThreadRunnable("Thread-1");
        MyThreadRunnable myThreadRunnable1 = new MyThreadRunnable("Thread-2");


        myThreadRunnable.start();
        myThreadRunnable1.start();

        Runnable runnable = () -> {
            try {


                for (int i = 0; i < 10; i++) {
                    Thread.sleep(199);
                    System.out.println("Thread: " + Thread.currentThread().getName() + ", " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runnable Thread-01 Completed");
        };

        Thread thread = new Thread(runnable, "Runnable Thread-01");
        //making  thread as daemon

        thread.setDaemon(true);
        thread.start();

        System.out.println("Main Thread ended");


    }

}
