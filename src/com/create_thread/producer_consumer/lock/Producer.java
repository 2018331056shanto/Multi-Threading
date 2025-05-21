package com.create_thread.producer_consumer.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:7:58 AM</p>
 */
public class Producer<T> implements Runnable {

    private final SharedQueue queue;
    private Integer  item=0;
    ReentrantLock lock=new ReentrantLock();

    public Producer(SharedQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
           while (true) {
                System.out.println("Produced: " + item);
                queue.put(item);
                ++item;

                // Add some delay between productions
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}