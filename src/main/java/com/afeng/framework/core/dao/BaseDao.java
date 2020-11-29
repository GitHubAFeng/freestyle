package com.afeng.framework.core.dao;

import com.afeng.framework.core.page.CoreUtil;
import com.afeng.framework.core.page.PageInfo;
import com.afeng.framework.core.page.PageList;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 付为地
 * 底层公共Dao
 * 使用示例：(SysLog) baseDao.selectOne("com.afeng.common.util.log.mapper.SysLog.query", object);
 */
@Repository("baseDao")
public class BaseDao extends SqlSessionDaoSupport {

    /**
     * 注入SqlSessionTemplate
     */
    @Autowired
    public void setSqlSessionTemplate(@Qualifier("sqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 提供扩展的SqlSession，功能
     */
    public SqlSession getSqlSession() {
        return super.getSqlSession();
    }

    /**
     * bootstrap-table支持多列排序分页 eg:
     * 页面采用 contentType="application/json"
     * 后台采用
     * public Object getPage(@RequestBody DataInterrupt param) {
     * return responseSelectPage(dataInterruptService.selectPage(param));
     * }
     *
     * @param sqlID:执行sql的id
     * @param params:sql对应的实体类,必须继承MutiSort类,本基类提供常见分页方式
     * @return 返回分页结果集
     */
    public <T> PageList<T> selectPage(String sqlID, Object params) {
        PageList<T> pageList = new PageList<T>();
        PageInfo pageInfo = CoreUtil.createBootStrapPage(params, true);
        String orders = CoreUtil.createSort(pageInfo, true);
        PageHelper.orderBy(orders);// 插件排序
        List<T> list = this.getSqlSession().selectList(sqlID, params,
                new RowBounds(pageInfo.getCurrentNumber(), pageInfo.getPageSize()));
        // 设置总记录数
        pageList.setList(list);
        Page<T> pg = (Page<T>) list;
        pageInfo.setRecordCount(pg.getTotal());
        pageList.setPageInfo(pageInfo);
        return pageList;
    }

    /**
     * bootstrap-table支持多列排序分页 eg:
     * 页面采用 contentType="application/json"
     * 后台采用
     * public Object getPage(@RequestBody DataInterrupt param) {
     * return responseSelectPage(dataInterruptService.selectPage(param));
     * }
     *
     * @param sqlID:执行sql的id
     * @param params:sql对应的实体类,必须继承MutiSort类,本基类提供常见分页方式
     * @param isAuto:是否自动将数据库查询结果转成驼峰格式,如果设置false则不转换,默认转换
     * @return 分页查询结果集
     */
    public <T> PageList<T> selectPage(String sqlID, Object params, boolean isAuto) {
        PageList<T> pageList = new PageList<T>();
        PageInfo pageInfo = CoreUtil.createBootStrapPage(params, isAuto);
        String orders = CoreUtil.createSort(pageInfo, isAuto);
        PageHelper.orderBy(orders);// 插件排序
        List<T> list = this.getSqlSession().selectList(sqlID, params,
                new RowBounds(pageInfo.getCurrentNumber(), pageInfo.getPageSize()));
        // 设置总记录数
        pageList.setList(list);
        Page<T> pg = (Page<T>) list;
        pageInfo.setRecordCount(pg.getTotal());
        pageList.setPageInfo(pageInfo);
        return pageList;
    }

    /**
     * selectPage 默认自动将:areaName转换成area_name表字段排序
     *
     * @param <T>
     * @param sqlID
     * @param params 默认自动转换
     * @return
     * @warn(注意事项 – 可选)
     */
    public <T> PageList<T> selectPage(String sqlID, Map<String, Object> params) {
        PageList<T> pageList = new PageList<T>();
        PageInfo pageInfo = CoreUtil.buildBootStrapPage(params, true);
        String orders = CoreUtil.createSort(pageInfo, true);
        PageHelper.orderBy(orders);// 插件排序
        List<T> list = this.getSqlSession().selectList(sqlID, params,
                new RowBounds(pageInfo.getCurrentNumber(), pageInfo.getPageSize()));
        // 设置总记录数
        pageList.setList(list);
        Page<T> pg = (Page<T>) list;
        pageInfo.setRecordCount(pg.getTotal());
        pageList.setPageInfo(pageInfo);
        return pageList;
    }

    /**
     * 分页方法 是否自动将:areaName转换成area_name表字段排序
     *
     * @param <T>
     * @param sqlID
     * @param params
     * @param isAuto:true自动|false不转换(默认转换)
     * @return 分页查询结果集
     */
    public <T> PageList<T> selectPage(String sqlID, Map<String, Object> params, boolean isAuto) {
        PageList<T> pageList = new PageList<T>();
        PageInfo pageInfo = CoreUtil.buildBootStrapPage(params, isAuto);
        String orders = CoreUtil.createSort(pageInfo, isAuto);
        PageHelper.orderBy(orders);// 插件排序
        List<T> list = this.getSqlSession().selectList(sqlID, params,
                new RowBounds(pageInfo.getCurrentNumber(), pageInfo.getPageSize()));
        // 设置总记录数
        pageList.setList(list);
        Page<T> pg = (Page<T>) list;
        pageInfo.setRecordCount(pg.getTotal());
        pageList.setPageInfo(pageInfo);
        return pageList;
    }


    /**
     * 新增
     *
     * @param sqlID  执行sqlId
     * @param object 传入参数
     * @return 新增结果
     */
    public int insert(String sqlID, Object object) {
        return this.getSqlSession().insert(sqlID, object);
    }

    /**
     * 新增或更新
     *
     * @param sqlID  执行sqlId
     * @param object 传入参数
     * @return 新增或更新结果
     */
    public int merge(String sqlID, Object object) {
        return this.getSqlSession().insert(sqlID, object);
    }

    /**
     * 更新
     *
     * @param sqlID  执行sqlId
     * @param object 传入参数
     * @return 更新结果
     */
    public int update(String sqlID, Object object) {
        return this.getSqlSession().update(sqlID, object);
    }

    /**
     * 浏览单个
     *
     * @param sqlID 执行sqlId
     * @param pk    主键
     * @return 根据主键查询的对象
     */
    public Object view(String sqlID, Object pk) {
        return this.getSqlSession().selectOne(sqlID, pk);
    }

    /**
     * 删除
     *
     * @param sqlID 执行的sqlId
     * @param pk    主键
     * @return 根据主键删除结果
     */
    public int delete(String sqlID, Object pk) {
        return this.getSqlSession().delete(sqlID, pk);
    }

    /**
     * 查询列表
     *
     * @param sqlID 执行sqlId
     * @param obj   传入参数
     * @return 查询的列表
     */
    public <T> List<T> selectList(String sqlID, Object obj) {
        return this.getSqlSession().selectList(sqlID, obj);
    }

    /**
     * 查询单条
     *
     * @param SqlID 执行sqlId
     * @param obj   传入参数
     * @return 查询结果对象
     */
    public Object selectOne(String SqlID, Object obj) {
        return this.getSqlSession().selectOne(SqlID, obj);
    }

    /**
     * 查询map
     *
     * @param SqlID 执行sqlId
     * @param args  传入map的key参数
     * @return 查询结果map
     */
    public Map<String, Object> selectMap(String SqlID, String args) {
        return this.getSqlSession().selectMap(SqlID, args);
    }

    /**
     * 批量更新
     *
     * @param sqlID  执行sqlID
     * @param object 待更新集合
     * @return 批量更新结果
     */
    public <T> int batchUpdate(String sqlID, List<T> object) {
        return this.getSqlSession().update(sqlID, object);
    }

    /**
     * 批量插入
     *
     * @param sqlID  执行sqlId
     * @param object 待插入集合
     * @return 批量插入结果
     */
    public <T> int batchInsert(String sqlID, List<T> object) {
        return this.getSqlSession().insert(sqlID, object);
    }

    /**
     * 批量删除
     *
     * @param sqlID  执行sqlId
     * @param pkList 主键列表
     * @return 批量删除结果
     */
    public <T> int batchDelete(String sqlID, List<T> pkList) {
        return this.getSqlSession().delete(sqlID, pkList);
    }

}
