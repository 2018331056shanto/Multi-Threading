package com.create_thread;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/4/2025<br>
 * Time:4:10 PM
 */
public class CreateThreadUsingThreadClass {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread thread=new Thread(()->{
            System.out.println("Running thread: "+Thread.currentThread().getName());
        });
        thread.start();
        Thread thread1=new Thread(()->{
            try {
                for(int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + Thread.currentThread().getName() + ", " + i);
                    // Let the thread sleep for a while.
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread " +  Thread.currentThread().getName() + " interrupted.");
            }
        });
        thread1.start();

        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();

        myThread2.start();

        myThread3.start();
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running Thread: " + Thread.currentThread().getName() + ", " + i);
        }
    }
}
