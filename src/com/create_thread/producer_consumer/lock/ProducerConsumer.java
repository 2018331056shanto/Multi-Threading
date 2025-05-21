package com.create_thread.producer_consumer.lock;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:7:58 AM</p>
 */
public class ProducerConsumer {


    public static void main(String[] args) {
        SharedQueue sharedQueue=new SharedQueue<Integer>(5);
        Producer producer=new Producer<Integer>(sharedQueue);
        Consumer consumer=new Consumer<Integer>(sharedQueue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
