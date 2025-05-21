package com.create_thread.producer_consumer.synchronized_keyword;

import java.util.List;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:48 AM</p>
 */
public class Producer implements Runnable {

    private final List<Integer> buffer;
    private final int maxSize;
    private int count;

    public Producer(List<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
        this.count = 0;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {

                    while (buffer.size() == maxSize) {
                        try {
                            buffer.wait(); // wait until buffer has space
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }


                    }
                    System.out.println("Produced " + count);
                    buffer.add(count++);
                try {
                    Thread.sleep(1000);
                    buffer.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                buffer.notify();
                }


            }
        }
    }

