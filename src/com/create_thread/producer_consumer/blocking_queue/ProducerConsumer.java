package com.create_thread.producer_consumer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:28 AM</p>
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);

        ProducerBlockingQueue producer = new ProducerBlockingQueue(queue);
        ConsumerBlockingQueue consumer = new ConsumerBlockingQueue(queue);

        new Thread(producer).start();
        new Thread(consumer).start();



    }
}
