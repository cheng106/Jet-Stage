package com.cheng.core.manager;

import com.cheng.common.utils.SpringUtils;
import com.cheng.common.utils.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncManager {

    /**
     * 非同步使用任務呼叫執行緒池
     */
    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService", ScheduledExecutorService.class);

    private static final AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 執行任務
     *
     * @param timerTask 任務
     */
    public void execute(TimerTask timerTask) {
        // 延遲10毫秒後動作
        int OPERATE_DELAY_TIME = 10;
        executor.schedule(timerTask, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 將任務執行緒池停止
     */
    public void shutdown() {
        ThreadUtils.shutdownAndAwaitTermination(executor);
    }
}
