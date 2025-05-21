package com.create_thread.producer_consumer.synchronized_keyword;

import java.util.List;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:52 AM</p>
 */
public class Consumer implements Runnable {

    private final List<Integer> buffer;
    private final int maxSize;

    public Consumer(List<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait(); // wait until buffer has data
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                try {
                    Thread.sleep(1); // simulate processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Consumed " + buffer.remove(0));
                buffer.notifyAll(); // notify producer
            }
        }
    }
}