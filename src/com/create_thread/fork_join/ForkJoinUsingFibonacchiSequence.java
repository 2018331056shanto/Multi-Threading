package com.create_thread.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * @author: Ashraful Islam Shanto
 * <p>Date:6/1/25</p>
 * <p>Time:4:54 PM</p>
 */
public class ForkJoinUsingFibonacchiSequence extends RecursiveTask<Integer> {
    final int n;
    ForkJoinUsingFibonacchiSequence(int n) {
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if(n <= 1)
            return n;
        System.out.println(Thread.currentThread().getName());
        ForkJoinUsingFibonacchiSequence f1=new ForkJoinUsingFibonacchiSequence(n-1);
        f1.fork();
        ForkJoinUsingFibonacchiSequence f2=new ForkJoinUsingFibonacchiSequence(n-2);
        f2.fork();
        return f1.join()+f2.join();

    }
    public static void main(String[] args) {
        ForkJoinUsingFibonacchiSequence f1=new ForkJoinUsingFibonacchiSequence(10);
        System.out.println(f1.compute());
    }
}
