package com.cheng.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadUtils {

    /**
     * 讓執行緒睡眠，單位毫秒
     *
     * @param milliseconds 毫秒數
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Thread utils ERROR:{}", e.getMessage());
        }
    }

    /**
     * 停止執行緒池<br>
     * 先使用shutdown，停止接收新的任務並嘗試完成所有已存在的任務，
     * 如果逾時，則使用shutdownNow，取消workQueue中Pending的任務，並中斷所有未完成的函數，
     * 如果仍然逾時，則強制退出，另外對在shutdown時的執行緒本身被呼叫中斷做處理
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate!!!");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 印出執行緒異常訊息
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException | ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }
}
