package com.afeng.web.common.rpc.config;

import com.afeng.web.common.rpc.impl.TestRPCServiceImpl;
import com.afeng.rpc.service.TestRPCService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 注册远程服务
 * 参考 https://www.bookstack.cn/read/redisson-wiki-zh/spilt.1.9.-%E5%88%86%E5%B8%83%E5%BC%8F%E6%9C%8D%E5%8A%A1.md
 * 参考 https://blog.csdn.net/yanluandai1985/article/details/105366851/
 * 参考 https://yq.aliyun.com/articles/551647
 *
 * @author AFeng
 * @createDate 2020/12/30 16:16
 **/
@Slf4j
@Service
public class RedissonRpcConfig {

    @Autowired
    private RedissonClient redisson;

    @PostConstruct
    public void init() throws Exception {
        log.debug("初始化Redisson远程调度RPC");
        RRemoteService remoteService = redisson.getRemoteService();
        //初始化5个并发实例
        remoteService.register(TestRPCService.class, new TestRPCServiceImpl(), 5);
    }

}
