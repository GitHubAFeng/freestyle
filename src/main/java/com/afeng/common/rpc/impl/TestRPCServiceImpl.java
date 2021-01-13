package com.afeng.common.rpc.impl;

import com.afeng.rpc.service.TestRPCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestRPCServiceImpl implements TestRPCService {
    @Override
    public String testStr(String text) {
        log.debug("Redisson远程调度testStr被调用 = " + text);
        return "test rpc = " + text;
    }
}
