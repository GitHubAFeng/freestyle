package com.afeng.web.module.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用Dao
 * https://blog.csdn.net/qq_33434415/article/details/100560990
 */
@Mapper
public interface CommonDao {
    /**
     * 查询列表
     **/
    List<Map<String, Object>> selectList(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);

    /**
     * 查询列表并排序
     **/
    List<Map<String, Object>> selectListAndOrderBy(@Param("tableName") String tableName, @Param("params") Map<String, Object> params, @Param("order") String order);

    /**
     * 查询列表某些列,selectParams的value长度大于2时会原样输出value，否则输出key
     **/
    List<Map<String, Object>> selectListByParamAndOrder(@Param("tableName") String tableName, @Param("selectParams") Map<String, Object> selectParams
            , @Param("whereParams") Map<String, Object> whereParams
            , @Param("order") String order);

    /**
     * 查询列表并排序,selectParams的value长度大于2时会原样输出value，否则输出key
     **/
    List<Map<String, Object>> selectListByParam(@Param("tableName") String tableName, @Param("selectParams") Map<String, Object> selectParams, @Param("whereParams") Map<String, Object> whereParams);

    /**
     * 查询单行
     **/
    Map<String, Object> selectOne(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);


    /**
     * 查询单行某些列,selectParams的value长度大于2时会原样输出value，否则输出key
     **/
    Map<String, Object> selectOneByParam(@Param("tableName") String tableName, @Param("selectParams") Map<String, Object> selectParams, @Param("whereParams") Map<String, Object> whereParams);


    /**
     * @Description TODO 通用修改
     * @Date 2019/4/26 10:56
     * @Param [tableName 表名, params 参数]
     * @Return java.lang.Integer
     **/
    Integer update(@Param("tableName") String tableName, @Param("params") Map<String, Object> params, @Param("whereParams") Map<String, Object> whereParams);

    /**
     * 加值更新
     **/
    Integer updateAdd(@Param("tableName") String tableName, @Param("params") Map<String, Object> params, @Param("whereParams") Map<String, Object> whereParams);

    /**
     * 部分加值更新，部份赋值更新
     **/
    Integer updateAndAdd(@Param("tableName") String tableName,
                         @Param("addParams") Map<String, Object> addParams,
                         @Param("params") Map<String, Object> Params,
                         @Param("whereParams") Map<String, Object> whereParams);


    /**
     * @Description TODO 通用删除
     * @Date 2019/4/26 10:56
     * @Param [tableName 表名, params 参数]
     * @Return java.lang.Integer
     **/
    Integer delete(@Param("tableName") String tableName, @Param("whereParams") Map<String, Object> whereParams);

    /**
     * @Description TODO 通用添加
     * @Date 2019/4/26 10:56
     * @Param [tableName 表名, params 参数]
     * @Return java.lang.Integer
     **/
    Integer insert(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);


    /**
     * 插表，如果主键或唯一键重复则不进行操作
     *
     * @author AFeng
     * @createDate 2020/3/9 17:25
     **/
    Integer insertIgnore(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);

    /**
     * 插入并返回主键
     **/
    Integer insertRetId(@Param("tableName") String tableName, @Param("params") Map<String, Object> params);

    /**
     * 自定义SQL删除
     **/
    Integer deleteBySql(@Param("sql") String sql);

    /**
     * 自定义SQL更新
     **/
    Integer updateBySql(@Param("sql") String sql);

    /**
     * 自定义SQL插入
     **/
    Integer insertBySql(@Param("sql") String sql);

    /**
     * 自定义SQL查询
     **/
    List<Map<String, Object>> selectBySql(@Param("sql") String sql);

    /**
     * 查询数量
     **/
    Integer count(@Param("tableName") String tableName, @Param("whereParams") Map<String, Object> whereParams);

    /**
     * 简单流式分页
     *
     * @param tableName   表名
     * @param whereParams 条件
     * @param order       排序：createTime desc
     * @param pageNum     当前页码
     * @param pageSize    每页数量
     **/
    List<Map<String, Object>> selectByPage(@Param("tableName") String tableName,
                                           @Param("whereParams") Map<String, Object> whereParams,
                                           @Param("order") String order,
                                           @Param("pageNum") Integer pageNum,
                                           @Param("pageSize") Integer pageSize);

    /**
     * 简单流式分页
     *
     * @param tableName   表名
     * @param whereParams 条件
     * @param order       排序：createTime desc
     * @param pageSize    每页条数
     * @param pageNum     当前页
     **/
    List<Map<String, Object>> selectListByPage(@Param("tableName") String tableName,
                                               @Param("whereParams") Map<String, Object> whereParams,
                                               @Param("order") String order,
                                               @Param("pageSize") Integer pageSize,
                                               @Param("pageNum") Integer pageNum);


    /**
     * 判断是否存在
     *
     * @param
     * @return 不存在则返回null ，否则返回 1
     * @author afeng
     * @date 2020/5/1 13:15
     */
    String isExist(@Param("tableName") String tableName, @Param("whereParams") Map<String, Object> whereParams);


}
