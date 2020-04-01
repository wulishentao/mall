package com.beau.graduation.dao;

import com.beau.graduation.model.BookRelationType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * BookRelationTypeDao
 * @author beau
 * @date 2020/03/28
 */
public interface BookRelationTypeDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookRelationType") BookRelationType bookRelationType);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookRelationType> list);

    /**
     * [更新]
     **/
    int update(@Param("bookRelationType") BookRelationType bookRelationType);

    /**
     * [删除]
     **/
    int delete(@Param("bookRelationType") BookRelationType bookRelationType);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<BookRelationType> list);

    /**
     * [主键查询]
     **/
    BookRelationType selectByObj(@Param("bookRelationType") BookRelationType bookRelationType);

    /**
     * [条件查询]
     **/
    List<BookRelationType> selectList (@Param("bookRelationType") BookRelationType bookRelationType);

    /**
     * [分页条件查询]
     **/
    List<BookRelationType> selectPage (@Param("bookRelationType") BookRelationType bookRelationType, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("bookRelationType") BookRelationType bookRelationType);
}
