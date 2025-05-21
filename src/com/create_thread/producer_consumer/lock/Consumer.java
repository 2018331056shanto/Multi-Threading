package com.create_thread.producer_consumer.lock;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:7:58 AM</p>
 */
public class Consumer<T> implements Runnable {

    private  SharedQueue<T> queue;

    public Consumer(SharedQueue<T> queue) {
        this.queue = queue;

    }

    @Override
    public void run() {
        try {
            while (true) {
                T item = queue.take();
                System.out.println("Consumed: " + item);
                // Add some delay to simulate processing time
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumer interrupted: " + e.getMessage());
        }
    }
}