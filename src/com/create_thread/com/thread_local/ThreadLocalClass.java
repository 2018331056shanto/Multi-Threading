package com.create_thread.com.thread_local;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/5/2025<br>
 * Time:5:28 PM
 */
public class ThreadLocalClass      {
    public static void main(String[] args) {
        ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();
        //simulate a user landing on the webpage
        Long userId=1234L;
        Long userId1=5678L;

        //handle the request in new thread
        Thread requestThread=new Thread(() -> {
            System.out.println("Strated Thread for "+userId);
            userIdThreadLocal.set(userId);
            //process my logic
            //database call
            System.out.println("Completed logic for "+userIdThreadLocal.get());
            //Good practice to remove threadLocal object;

            userIdThreadLocal.remove();

            System.out.println("Removed  "+userIdThreadLocal.get());
        });
        Thread requestThread1=new Thread(() -> {
            System.out.println("Strated Thread for "+userId1);
            userIdThreadLocal.set(userId1);
            System.out.println("Completed logic for "+userIdThreadLocal.get());
            userIdThreadLocal.remove();
            System.out.println("Removed  "+userIdThreadLocal.get());
        });

        requestThread.start();
        requestThread1.start();

        InheritableThreadLocal<String> inheritableThreadLocal=new InheritableThreadLocal<>();

        Thread inheritableThread=new Thread(() -> {
            inheritableThreadLocal.set("Instagram");
            userIdThreadLocal.set(1323232L);
            System.out.println("Parent ThreadLocal "+userIdThreadLocal.get());
            Thread childThread=new Thread(() -> {
                System.out.println("Child Thread for "+inheritableThreadLocal.get());
                System.out.println("Child ThreadLocal "+userIdThreadLocal.get());

                inheritableThreadLocal.remove();
            });
            childThread.start();
        });
        inheritableThread.start();

    }
}
