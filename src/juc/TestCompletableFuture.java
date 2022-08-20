package juc;

import java.util.concurrent.*;


public class TestCompletableFuture {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "123";
            }, threadPool).whenComplete((v, e) -> {
                System.out.println("结果为" + v);
                System.out.println("运行时e" + e);
            }).exceptionally(e -> {
                System.out.println(e.getStackTrace());
                return null;
            });
            System.out.println(Thread.currentThread().getName()+"去忙了");
        } finally {
            threadPool.shutdown();
        }


    }
}
