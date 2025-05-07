package com.create_thread;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/5/2025<br>
 * Time:11:33 AM
 */

class SharedResource {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class SharedResource1 {
    private boolean flag = false;
    public synchronized boolean isFlag() {
        return flag;
    }
    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }
}

public class SolveVisibilityProblemInMultiThreading {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        SharedResource1 sharedResource1 = new SharedResource1();

        new Thread(() -> {

            System.out.println("Thread-1 started");

            try {
                System.out.println("Thread-1 logic started");
                Thread.sleep(1000);
                System.out.println("Thread-1 logic finished");

                sharedResource.setFlag(true);
                System.out.println("flag is set to true");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println("Thread-2 started");
            while (!sharedResource.isFlag()) {
            }
            System.out.println("Thread-2 finished");
        }).start();



        new Thread(() -> {

            System.out.println("Thread-3 started");

            try {
                System.out.println("Thread-3 logic started");
                Thread.sleep(1000);
                System.out.println("Thread-3 logic finished");

                sharedResource1.setFlag(true);
                System.out.println("flag is set to true");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println("Thread-4 started");
            while (!sharedResource1.isFlag()) {
            }
            System.out.println("Thread-4 finished");
        }).start();




    }
}
