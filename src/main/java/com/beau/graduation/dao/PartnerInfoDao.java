package com.beau.graduation.dao;

import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.model.PartnerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * PartnerInfoDao
 * @author beau
 * @date 2020/03/28
 */
public interface PartnerInfoDao {

    /**
     * [新增]
     **/
    int insert(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<PartnerInfo> list);

    /**
     * [更新]
     **/
    int update(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [删除]
     **/
    int delete(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<PartnerInfo> list);

    /**
     * [主键查询]
     **/
    PartnerInfo selectByObj(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [主键查询]
     **/
    PartnerInfo selectAdmin(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [条件查询]
     **/
    List<PartnerInfo> selectList (@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * [分页条件查询]
     **/
    List<PartnerInfo> selectPage (@Param("partnerInfo") PartnerInfo partnerInfo, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("partnerInfo") PartnerInfo partnerInfo);

    /**
     * 获取满足条件的用户
     * @param partnerInfo
     * @param page
     * @return
     */
    List<PartnerInfo> getPartnerPage(@Param("partnerInfo") PartnerInfo partnerInfo,@Param("page") HashMap<String,Integer> page);
}
