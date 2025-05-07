package com.create_thread;

/**
 * Author: Ashraful Islam Shanto<br>
 * Date:5/4/2025<br>
 * Time:5:45 PM
 */
public class IssuesForSharedObjectInMultiThreading {
    public static Object sharedObject = new Object();
    public static void main(String[] args) {

        System.out.println("Main thread Started");
        Runnable runnable1=()->{

            System.out.println("Thread-1 started");
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println(sharedObject.hashCode());

            try{
                for(int i=0;i<10;i++){
                    Thread.sleep(2000);

                    System.out.println(Thread.currentThread().getName()+" : " +i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("Thread 1 completed");

        };

        Runnable runnable2=()->{
            System.out.println("Thread-2 started");
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println(sharedObject.hashCode());

            try{

                for(int i=0;i<10;i++){
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+" : " +i);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Thread 2 completed");
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }


}
