package com.create_thread.com.thread_local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:10:33 PM</p>
 */
public class UserServiceWithExecutorServiceExampleThreadLocal {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            executorService.submit(()->{
                String birthDate=new UserServiceWithExecutorServiceExampleThreadLocal().birthDate(100);
                System.out.println(Thread.currentThread().getName());
                System.out.println(birthDate);
            });
        }
        executorService.shutdown();



    }

    public String birthDate(int userId){

        Date date = getBirthDateById(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private Date getBirthDateById(int userId) {
        //DB operations

        return new Date();
    }
}
