package com.afeng.web.common.rpc.impl;

import com.afeng.rpc.service.TestRPCService;
import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.module.admin.model.Dept;
import com.afeng.web.module.admin.service.IDeptService;
import com.afeng.web.module.common.dao.CommonDao;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TestRPCServiceImpl implements TestRPCService {

    @Autowired
    private CommonDao commonDao;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String testStr(String text) {
        log.debug("Redisson远程调度testStr被调用 = " + text);
        Map<String, Object> where = new HashMap<>();
        where.put("oper_id", 1);
        Map<String, Object> Param = new HashMap<>();
        Param.put("oper_location", 1);
        Param.put("title", "title as logTitle");
        Param.put("dept_name", "isnull(dept_name,'测试') as deptName");
        Map<String, Object> sysOperLog = commonDao.selectOneByParam("sys_oper_log", Param, where);
        List<Dept> deptList = deptService.selectDeptList(new Dept());
        jedisUtil.set("test", "aaaaaaaaaaaaaa", 60 * 5);

        return "test rpc = " + text + " &&all= " + Joiner.on(",").withKeyValueSeparator("=").join(sysOperLog)
                + " &&deptList= " + Joiner.on(",").join(deptList)
                + " &&jedisUtil= " + jedisUtil.get("test");
    }

    @Override
    public String testStr(String text, String text2) {
        log.debug("Redisson远程调度testStr被调用 = " + text);
        return "test rpc = " + text + " &&text2= " + text2;
    }
}
