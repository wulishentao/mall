package com.beau.graduation.dao;

import com.beau.graduation.model.Advertise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * AdvertiseDao
 * @author beau
 * @date 2020/03/28
 */
public interface AdvertiseDao {

    /**
     * [新增]
     **/
    int insert(@Param("advertise") Advertise advertise);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<Advertise> list);

    /**
     * [更新]
     **/
    int update(@Param("advertise") Advertise advertise);

    /**
     * [删除]
     **/
    int delete(@Param("advertise") Advertise advertise);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<Advertise> list);

    /**
     * [主键查询]
     **/
    Advertise selectByObj(@Param("advertise") Advertise advertise);

    /**
     * [条件查询]
     **/
    List<Advertise> selectList(@Param("advertise") Advertise advertise);

    /**
     * [分页条件查询]
     **/
    List<Advertise> selectPage(@Param("advertise") Advertise advertise, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("advertise") Advertise advertise);
}
