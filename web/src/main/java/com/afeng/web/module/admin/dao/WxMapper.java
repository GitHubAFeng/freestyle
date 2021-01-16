package com.afeng.web.module.admin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 微信 数据层
 */
@Mapper
public interface WxMapper {

    List<Map<String, Object>> findFissionLevelList();

}
