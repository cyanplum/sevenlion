package cn.sevenlion.message.handler.config;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 * 线程池配置
 */
public class ThreadPoolConfig {

    /**
     *
     * @param coreSize 核心线程数
     * @param maxSize 线程池大小
     * @param queueSize 队列大小
     * @return
     */
    public static ExecutorService getThreadPool(Integer coreSize, Integer maxSize, Integer queueSize) {
        ThreadPoolExecutor threadPoolExecutor = ExecutorBuilder.create()
                .setCorePoolSize(coreSize)
                .setMaxPoolSize(maxSize)
                .setKeepAliveTime(60, TimeUnit.SECONDS)
                .setWorkQueue(new LinkedBlockingQueue<>(queueSize))
                .setHandler(new ThreadPoolExecutor.CallerRunsPolicy())
                .build();
        return threadPoolExecutor;
    }
}
