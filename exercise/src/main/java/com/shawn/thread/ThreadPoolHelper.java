package com.shawn.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yansq
 * @date 2020/3/23 21:16
 */
public class ThreadPoolHelper {

    private static final String POLICY_THREAD_POOL_NAME = "policyPricePool-%d";
    private static int coreSize = Runtime.getRuntime().availableProcessors();
    private static int poolSize = coreSize << 2;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            coreSize,
            poolSize,
            1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(50),
            new ThreadFactoryBuilder().setNameFormat(POLICY_THREAD_POOL_NAME).setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static <V> Future<V> threadSubmit(Callable<V> call){
        return threadPoolExecutor.submit(call);
    }


    public static void threadExecute(Runnable run){
        threadPoolExecutor.execute(run);
    }


}
