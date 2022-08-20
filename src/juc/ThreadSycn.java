package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 小Q
 */
class T1 {
    private int num = 400;

    //使用 synchronized
    //public synchronized void tickets() {
    //    if (num > 0) {
    //        System.out.println(Thread.currentThread().getName() + "卖出了第" + num + "===>" + "剩余" + (num--) + "张票");
    //    }
    //}

    //  使用可重入锁，非公平锁
    Lock lock = new ReentrantLock();

    public void tickets() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + num + "===>" + "剩余" + (num--) + "张票");
            }
        }finally {
            lock.unlock();
        }

    }


}

public class ThreadSycn {
    public static void main(String[] args) {
        T1 t = new T1();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                t.tickets();
            }
        }, "A1").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                t.tickets();
            }
        }, "A2").start();
    }
}
