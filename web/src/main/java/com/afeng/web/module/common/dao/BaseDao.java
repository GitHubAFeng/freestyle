package com.afeng.web.module.common.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通用Dao，可以不用通过mapper而直接调用xml中sql
 **/
@Slf4j
@Repository
public class BaseDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 添加
     */
    public <T> int insert(T t) throws Exception {
        try {
            return sqlSessionTemplate.insert(t.getClass().getSimpleName() + "_insert", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_insert" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 批量添加记录
     */
    public <T> int insertList(List<T> t) throws Exception {
        try {
            if (t != null && t.size() > 0) {
                return sqlSessionTemplate.insert(t.get(0).getClass().getSimpleName() + "list_insert", t);
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "list_insert" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 自定义添加记录
     */
    public <T> int insert(String myStrId, T t) throws Exception {
        try {
            return sqlSessionTemplate.insert(myStrId, t);
        } catch (Exception e) {
            log.error(myStrId + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 修改记录
     */
    public <T> int update(T t) throws Exception {
        try {
            return sqlSessionTemplate.update(t.getClass().getSimpleName() + "_update", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_update" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 批量修改记录
     */
    public <T> int updateList(List<T> t) throws Exception {
        try {
            if (t != null && t.size() > 0) {
                return sqlSessionTemplate.update(t.get(0).getClass().getSimpleName() + "list_update", t);
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "list_update" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 自定义修改记录
     */
    public <T> int update(String myStrId, T t) throws Exception {
        try {
            return sqlSessionTemplate.update(myStrId, t);
        } catch (Exception e) {
            log.error(myStrId + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 删除记录
     */
    public <T> int delete(T t) throws Exception {
        try {
            return sqlSessionTemplate.delete(t.getClass().getSimpleName() + "_delete", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_delete" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 删除记录
     */
    public <T> int deleteList(List<T> t) throws Exception {
        try {
            if (t != null && t.size() > 0) {
                return sqlSessionTemplate.delete(t.get(0).getClass().getSimpleName() + "list_delete", t);
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "list_delete" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 自定义删除记录
     */
    public <T> int delete(String myStrId, T t) throws Exception {
        try {
            return sqlSessionTemplate.delete(myStrId, t);
        } catch (Exception e) {
            log.error(myStrId + " failed :{}", e);
            throw e;
        }
    }


    /**
     * 查询
     */
    public <T> T selectById(T t) throws Exception {
        try {
            return sqlSessionTemplate.selectOne(t.getClass().getSimpleName() + "_detailSelect", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_detailSelect" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 查询列表
     */
    public <T> List<T> selectList(T t) throws Exception {
        try {
            return sqlSessionTemplate.selectList(t.getClass().getSimpleName() + "_select", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_select" + " failed :{}", e);
            throw e;
        }
    }


    /**
     * 查询app分页列表
     */
    public <T> List<T> selectAppList(T t) throws Exception {
        try {
            return sqlSessionTemplate.selectList(t.getClass().getSimpleName() + "_appList", t);
        } catch (Exception e) {
            log.error(t.getClass().getSimpleName() + "_appList" + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 自定义参数查询
     */
    public <T> List<T> selectList(String myStrId, T t) throws Exception {
        try {
            return sqlSessionTemplate.selectList(myStrId, t);
        } catch (Exception e) {
            log.error(myStrId + " failed :{}", e);
            throw e;
        }
    }

    /**
     * 自定义参数查询
     */
    public <T> T selectOne(String myStrId, T t) throws Exception {
        try {
            return sqlSessionTemplate.selectOne(myStrId, t);
        } catch (Exception e) {
            log.error(myStrId + " failed :{}", e);
            throw e;
        }
    }
}
