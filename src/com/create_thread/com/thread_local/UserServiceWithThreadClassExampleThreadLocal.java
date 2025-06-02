package com.create_thread.com.thread_local;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:10:33 PM</p>
 */
public class UserServiceWithThreadClassExampleThreadLocal {

    public static void main(String[] args) {

        for(int i=0;i<1000;i++){
            new Thread(()->{
                String birthDate=new UserServiceWithThreadClassExampleThreadLocal().birthDate(100);
                System.out.println(Thread.currentThread().getName());
                System.out.println(birthDate);
            }).start();
        }


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
