package com.beau.graduation.service;

import com.beau.graduation.common.Page;
import com.beau.graduation.model.Advertise;

import java.util.List;

/**
 * 业务层
 * AdvertiseService
 * @author beau
 * @date 2020/03/28
 */
public interface AdvertiseService {

    /**
     * [新增]
     **/
    int insert(Advertise advertise);

    /**
     * [批量新增]
     **/
    int batchInsert(List<Advertise> list);

    /**
     * [更新]
     **/
    int update(Advertise advertise);

    /**
     * [删除]
     **/
    int delete(Advertise advertise);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Advertise> list);

    /**
     * [主键查询]
     **/
    Advertise selectByObj(Advertise advertise);

    /**
     * [条件查询]
     **/
    List<Advertise> selectList (Advertise advertise);

    /**
     * [分页条件查询]
     **/
    Page<Advertise> selectPage (Advertise advertise, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(Advertise advertise);
}
