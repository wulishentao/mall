package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.LoginReqDto;
import com.beau.graduation.basic.reqdto.RegisterReqDto;
import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.basic.resdto.RegisterResDto;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.common.PageList;
import com.beau.graduation.model.PartnerInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 业务层
 * PartnerInfoService
 * @author beau
 * @date 2020/03/28
 */
public interface PartnerInfoService {

    /**
     * [新增]
     **/
    void insert(PartnerInfo partnerInfo);

    /**
     * [新增]
     **/
    RegisterResDto insert(RegisterReqDto reqDto);

    /**
     * [批量新增]
     **/
    int batchInsert(List<PartnerInfo> list);

    /**
     * [更新]
     **/
    int update(PartnerInfo partnerInfo);

    /**
     * [删除]
     **/
    int delete(PartnerInfo partnerInfo);

    /**
     * [批量删除]
     **/
    int batchDelete(List<PartnerInfo> list);

    /**
     * [查询]
     **/
    LoginResDto selectByObj(LoginReqDto reqDto, HttpServletRequest request, HttpServletResponse response);

    /**
     * [条件查询]
     **/
    List<PartnerInfo> selectList (PartnerInfo partnerInfo);

    /**
     * [分页条件查询]
     **/
    PageList<PartnerInfo> selectPage (PartnerInfo partnerInfo, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(PartnerInfo partnerInfo);
}
