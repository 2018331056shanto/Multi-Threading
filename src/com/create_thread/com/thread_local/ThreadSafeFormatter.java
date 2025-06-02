package com.create_thread.com.thread_local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:11:31 PM</p>
 */
public class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        @Override
        public SimpleDateFormat get() {
            return super.get();
        }


    };

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
       for(int i=0;i<100;i++){

           executorService.submit(()->{
               String date=new ThreadSafeFormatter().birtdate(100);
               System.out.println(Thread.currentThread().getName());
               System.out.println(date);
           });
       }
       executorService.shutdown();
    }
    private String birtdate(int userId){

        SimpleDateFormat simpleDateFormat=ThreadSafeFormatter.dateFormat.get();
        return simpleDateFormat.format(new Date());
    }
}
