package com.afeng.web.common.rpc;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@Component
public class RpcUtils {

    @Autowired
    private RedissonClient redissonClient;

    @SuppressWarnings("unchecked")
    public Object invokeServiceMethod(String serviceName, String methodName, Object... args) {
        try {
            //获取Redisson远程服务
            RRemoteService remoteService = redissonClient.getRemoteService();
            //应答回执超时1秒钟，远程执行超时30秒钟
            RemoteInvocationOptions options = RemoteInvocationOptions.defaults();
            Class<?> cls = Class.forName(serviceName);
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
            Object service = remoteService.get(cls.getClass(), options);
            //调用
            return method.invoke(service, args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
