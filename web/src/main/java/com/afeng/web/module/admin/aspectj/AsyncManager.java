package com.afeng.web.module.admin.aspectj;

import com.afeng.web.common.util.SpringUtils;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.FutureTask;

/**
 * 异步任务管理器
 *
 * @author liuhulu
 */
public class AsyncManager {

    /**
     * 异步操作任务调度线程池
     */
    private TaskExecutor executor = SpringUtils.getBean("taskExecutor");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(FutureTask<Object> task) {
        executor.execute(task);
    }


}
