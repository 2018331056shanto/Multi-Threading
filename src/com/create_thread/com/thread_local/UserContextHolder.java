package com.create_thread.com.thread_local;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/2/25</p>
 * <p>Time:10:32 AM</p>
 */
public class UserContextHolder {

    public static ThreadLocal<User> userContext = new ThreadLocal<>();


    public record User(int id, String name, String address) {
    }

    class Service1 {

        public void process() {
            User user = new User(1, "SHANTO", "Sherpur");

            UserContextHolder.userContext.set(user);
        }
    }

    class Service2 {
        public void process() {
            User user = UserContextHolder.userContext.get();

        }
    }

    class Service3 {
        public void process() {
            User user = UserContextHolder.userContext.get();

            UserContextHolder.userContext.remove();
        }
    }


}
