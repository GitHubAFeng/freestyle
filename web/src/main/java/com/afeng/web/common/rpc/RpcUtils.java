package com.afeng.web.common.rpc;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RpcUtils {

    @Autowired
    private RedissonClient redissonClient;

    private static RpcUtils instance;

    @PostConstruct
    private void init() {
        instance = this;
        instance.redissonClient = this.redissonClient;
    }

    public static RpcUtils getInstance() {
        return instance;
    }


    @SuppressWarnings("all")
    private Object invokeServiceMethod(String serviceName, String methodName, Object... args) throws Exception {
        //获取Redisson远程服务
        RRemoteService remoteService = redissonClient.getRemoteService();
        //应答回执超时1秒钟，远程执行超时30秒钟
        RemoteInvocationOptions options = RemoteInvocationOptions.defaults();
        String className = "com.afeng.rpc.service." + serviceName + "Service";
        Class cls = Class.forName(className);
        Method method;
        if (args.length == 0) {
            method = cls.getDeclaredMethod(methodName);
        } else {
            Class<?>[] parameterTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
            method = cls.getDeclaredMethod(methodName, parameterTypes);
        }
        //拿到生产端的接口
        Object service = remoteService.get(cls, options);
        //调用
        return method.invoke(service, args);
    }

    @SuppressWarnings("all")
    private <T> Object invokeServiceMethod(Class<T> serviceClass, String methodName, Object... args) throws Exception {
        //获取Redisson远程服务
        RRemoteService remoteService = redissonClient.getRemoteService();
        //应答回执超时1秒钟，远程执行超时30秒钟
        RemoteInvocationOptions options = RemoteInvocationOptions.defaults();
        //拿到生产端的接口
        T service = remoteService.get(serviceClass, options);
        Method method;
        if (args.length == 0) {
            method = BeanUtils.findMethod(service.getClass(), methodName);
        } else {
            Class<?>[] parameterTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
            method = BeanUtils.findMethod(service.getClass(), methodName, parameterTypes);
        }
        //调用
        return method.invoke(service, args);
    }

	//TODO 测试发现通过反射调用会导致bean没法自动注入，不过手动获取bean
    public Object invoke(final String serviceName, final String methodName, final Object... args) throws Exception {
        //定义重试机制
        Retryer<Object> retryer = RetryerBuilder.newBuilder()
                //retryIf 重试条件
                .retryIfExceptionOfType(org.redisson.client.RedisException.class)
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止策略 : 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        //定义请求实现
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                log.debug("调用RPC: serviceName=" + serviceName + "&methodName=" + methodName);
                //调用
                return RpcUtils.getInstance().invokeServiceMethod(serviceName, methodName, args);
            }
        };

        //利用重试器调用请求
        return retryer.call(callable);
    }

    public <T> Object invoke(final Class<T> serviceClass, final String methodName, final Object... args) throws Exception {
        //定义重试机制
        Retryer<Object> retryer = RetryerBuilder.newBuilder()
                //retryIf 重试条件
                .retryIfExceptionOfType(org.redisson.client.RedisException.class)
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止策略 : 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        //定义请求实现
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                log.debug("调用RPC: serviceName=" + serviceClass.getSimpleName() + "&methodName=" + methodName);
                //调用
                return RpcUtils.getInstance().invokeServiceMethod(serviceClass, methodName, args);
            }
        };

        //利用重试器调用请求
        return retryer.call(callable);
    }

}
