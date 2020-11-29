package com.afeng.framework.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 */
@Configuration
//开启异步注解，可在服务层使用@Async注解标明方法为异步执行
@EnableAsync
public class ExecutorConfig implements AsyncConfigurer {

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池数量，方法: 返回可用处理器的Java虚拟机的数量。
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());// 线程池维护线程的最少数量
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 5);// 线程池维护线程的最大数量
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors() * 2);//线程池的队列容量
        executor.setThreadNamePrefix("taskExecutor-");//线程名称的前缀
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 对拒绝task的处理策略
        executor.setKeepAliveSeconds(60);// 允许的空闲时间
        executor.initialize();
        return executor;
    }

    /* @Async异步任务中获取线程 */
    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor();
    }

    /* @Async异步任务中异常处理 */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
