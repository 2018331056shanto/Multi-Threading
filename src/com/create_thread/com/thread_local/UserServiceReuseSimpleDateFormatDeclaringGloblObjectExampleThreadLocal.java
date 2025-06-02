package com.create_thread.com.thread_local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:11:05 PM</p>
 */
public class UserServiceReuseSimpleDateFormatDeclaringGloblObjectExampleThreadLocal {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for(int i=0;i<1000;i++){
            executorService.submit(()->{
                String birthDate=new UserServiceReuseSimpleDateFormatDeclaringGloblObjectExampleThreadLocal().birthDate(1);
                System.out.println(Thread.currentThread().getName());
                System.out.println(birthDate);
            });
        }

        executorService.shutdown();
    }
    private String birthDate(int userId){
        return dateFormat.format(new Date());
    }
}
