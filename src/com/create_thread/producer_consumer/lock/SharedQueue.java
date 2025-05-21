package com.create_thread.producer_consumer.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:5/21/25</p>
 * <p>Time:8:00 AM</p>
 */
public class SharedQueue <T>{

    private Queue<T> queue;

    private int maxSize;
    private ReentrantLock lock=new ReentrantLock(true);

    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();
    private boolean isProducerTurn = true;

    public SharedQueue(int maxSize) {
        queue = new LinkedList<T>();
        this.maxSize = maxSize;
    }


    public void put(T t) throws InterruptedException {
        //acquire lock first
        lock.lock();
        try {
            while (queue.size()==maxSize){
                //wait for condition to be triggered when someone say this queue is not full
                // anymore so it can proceed and add it's item. Java concurrency
                // as soon as you do the condition.await() if the condition is
                // not satisfied then the thread automatically releases the lock and goes into wait state

                notFull.await();

            }
            queue.add(t);
            isProducerTurn = false;

            notEmpty.signal();

        }finally {
            //release lock
            lock.unlock();
        }

    }

    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()){
                //wait for condition to be triggered when someone say this queue is not empty
                // anymore so it can proceed and remove it's item. Java concurrency
                // 'as soon as you do the condition.await() if the condition is
                // not satisfied then the thread automatically releases the lock and goes into wait state

                notEmpty.await();
            }
            T t=queue.poll();
            isProducerTurn = true;

            notFull.signal();
            return t;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}

//consumer thread which is waiting for the condition of notEmpty
// as soon as the producer put in queue it knows that there is something
// in the queue now. SO producer will signal to the consumer thread that it has added
// something in queue. So the queue is not empty so the notEmpty condition is
// satisfied now and it can proceed now

