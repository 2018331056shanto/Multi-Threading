package com.create_thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/5/2025<br>
 * Time:3:25 PM
 */


// ---------------------------
// 1️⃣ Synchronized Instance Method
// ---------------------------

/**
 * Synchronized Instance Method<br>
 * Lock is on {@code this  }  object (the current instance of the class)
 * <br>
 * Only one thread at a time per instance can execute any synchronized method of that object.
 * <br>
 * Equivalent to <pre>{@code  public void increment(){
 *         synchronized(this){
 *             value++;
 *         }
 *     }}</pre>
 */
class Counter1 {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getValue() {
        return counter;
    }
}

/**
 * Static {@code synchronized} methods lock on the Class object (ClassName.class)<br>
 * Only one Class object exists per class in the JVM (shared by all instances)
 * Therefore, only one thread can execute any static synchronized method per class at a time
 * This is independent of the number of instances of the class — the lock is shared across all instances
 */


// ---------------------------
// 2️⃣ Synchronized Static Method
// ---------------------------
class Counter2 {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized int getValue() {
        return counter;
    }
}

/**
 * The lock behind the synchronized methods and blocks is a reentrant.<br>
 * This means the current thread can acquire the same {@code synchronized} lock over and over again while holding it:
 */
// ---------------------------
// 3️⃣ Synchronized Block inside Instance Method
// ---------------------------
class Counter3 {
    private int counter = 0;
    Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            counter++;
        }
    }

    public int getValue() {
        synchronized (lock) {
            return counter;
        }
    }
}


/**
 * In synchronized {@code this}, this is the monitor object (the current instance)<br>
 * → Only one thread per instance can run inside that block at a time.
 * <br>
 * In synchronized {@code ClassName.class} (for static methods/blocks),
 * the Class object is the monitor<br>
 * → Only one thread per class can run inside that block at a time (shared across all instances).<br>
 * Lock is on Class object (ClassName.class)<br>
 * <p>
 * Same idea as static synchronized method — but you can synchronize only part of the method, not all.
 */
// ---------------------------
// 4️⃣ Synchronized Block inside Static Method
// ---------------------------
class Counter4 {
    private static int counter = 0;

    public static void increment() {
        synchronized (Counter4.class) {
            counter++;
        }
    }

    public static int getValue() {
        synchronized (Counter4.class) {
            return counter;
        }
    }
}

// ---------------------------
// Main Class to test all 4
// ---------------------------
public class SynchronizedUsingExecutorService {
    public static void main(String[] args) throws InterruptedException {
        testCounter1();
        testCounter2();
        testCounter3();
        testCounter4();

    }

    // 1️⃣ Test for synchronized instance method
    public static void testCounter1() throws InterruptedException {
        System.out.println("\n===== Test Counter1 (synchronized instance method) =====");
        Counter1 sharedCounter = new Counter1();
        ExecutorService service = Executors.newFixedThreadPool(2);


        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println(Thread.currentThread().getName() + " Finished");
        };

        service.submit(task);
        service.submit(task);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Final result: " + sharedCounter.getValue());
    }

    // 2️⃣ Test for synchronized static method
    public static void testCounter2() throws InterruptedException {
        System.out.println("\n===== Test Counter2 (synchronized static method) =====");
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Started");
            for (int i = 0; i < 50000; i++) {
                Counter2.increment();
            }
            System.out.println(Thread.currentThread().getName() + " Finished");
        };

        service.submit(task);
        service.submit(task);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Final result: " + Counter2.getValue());
    }

    // 3️⃣ Test for synchronized block in instance method
    public static void testCounter3() throws InterruptedException {
        System.out.println("\n===== Test Counter3 (synchronized block in instance method) =====");
        Counter3 sharedCounter = new Counter3();
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Started");
            for (int i = 0; i < 50000; i++) {
                sharedCounter.increment();
            }
            System.out.println(Thread.currentThread().getName() + " Finished");
        };

        service.submit(task);
        service.submit(task);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Final result: " + sharedCounter.getValue());
    }

    // 4️⃣ Test for synchronized block in static method
    public static void testCounter4() throws InterruptedException {
        System.out.println("\n===== Test Counter4 (synchronized block in static method) =====");
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Started");
            for (int i = 0; i < 50000; i++) {
                Counter4.increment();
            }
            System.out.println(Thread.currentThread().getName() + " Finished");
        };

        service.submit(task);
        service.submit(task);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Final result: " + Counter4.getValue());
    }
}
