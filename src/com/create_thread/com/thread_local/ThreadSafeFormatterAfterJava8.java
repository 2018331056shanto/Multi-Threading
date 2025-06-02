package com.create_thread.com.thread_local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/2/25</p>
 * <p>Time:10:22 AM</p>
 */
public class ThreadSafeFormatterAfterJava8 {

    ThreadLocal<SimpleDateFormat> formatThreadLocal=ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=0;i<1000;i++){

            executorService.submit(()->{
                String date=new ThreadSafeFormatterAfterJava8().getBrithDate(199);

                System.out.println(Thread.currentThread().getName());
                System.out.println(date);
            });
        }
    }


    public String getBrithDate(int userId){

        return  formatThreadLocal.get().format(new Date());
    }
}
