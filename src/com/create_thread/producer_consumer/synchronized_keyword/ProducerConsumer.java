package com.create_thread.producer_consumer.synchronized_keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:46 AM</p>
 */
public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> buffer = new ArrayList<Integer>(5);
        int maxSize = 5;

        Producer producer = new Producer(buffer, maxSize);
        Consumer consumer = new Consumer(buffer, maxSize);

        Thread t1= new Thread(producer);
        Thread t2 =new Thread(consumer);

        t1.start();
        t2.start();

    }
}
