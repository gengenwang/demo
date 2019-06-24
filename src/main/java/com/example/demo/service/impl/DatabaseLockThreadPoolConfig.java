package com.example.demo.service.impl;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: DatabaseLockThreadPoolConfig.java, V 0.1 2019/6/21 下午4:07 wanggengen Exp $$
 **/
@Component
public class DatabaseLockThreadPoolConfig {

    private static final int MAX_SIZE = 10;
    private static final int CORE_SIZE = 5;
    private static final int SECOND = 1000;

    private ThreadPoolExecutor executor;

    public DatabaseLockThreadPoolConfig() {
        executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, SECOND, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public void submit(Thread thread) {
        executor.execute(thread);
    }

}
