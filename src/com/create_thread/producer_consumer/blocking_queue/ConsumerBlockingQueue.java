package com.create_thread.producer_consumer.blocking_queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:32 AM</p>
 */
public class ConsumerBlockingQueue implements Runnable {
    private BlockingQueue<Integer> queue;
    public ConsumerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {

        try {
            while (true) {
                Integer item=queue.take();
                System.out.println("Consumed item:"+item);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
