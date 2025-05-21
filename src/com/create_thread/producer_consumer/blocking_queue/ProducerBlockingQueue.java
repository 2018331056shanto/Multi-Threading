package com.create_thread.producer_consumer.blocking_queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:6:30 AM</p>
 */
public class ProducerBlockingQueue implements Runnable {

    private BlockingQueue<Integer> queue;
    public ProducerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
     try{
         for(int i=0;i<20;i++){
             Thread.sleep(1000);
             queue.put(i);
             System.out.println("Produced "+i);
         }
     } catch (InterruptedException e) {
         throw new RuntimeException(e);
     }
    }
}
