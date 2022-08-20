package juc;

import java.util.concurrent.*;

public class TestFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            try {
                TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return "123";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(()->{
            try {
                TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return "345";
        });
        threadPool.submit(futureTask2);

        FutureTask<String> futureTask3 = new FutureTask<String>(()->{
            try {
                TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return "567";
        });
        threadPool.submit(futureTask3);

        long stop = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace();
        }

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
        System.out.println("三个线程总耗时为："+(stop-start)+"毫秒");

        threadPool.shutdown();

    }
}
