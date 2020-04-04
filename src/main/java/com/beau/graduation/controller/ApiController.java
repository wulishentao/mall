package com.beau.graduation.controller;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.GetPartnerReqDto;
import com.beau.graduation.basic.reqdto.LoginReqDto;
import com.beau.graduation.basic.reqdto.LogoutReqDto;
import com.beau.graduation.basic.resdto.GetPartnerResDto;
import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.basic.resdto.LogoutResDto;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.service.PartnerInfoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @classname: OpenApiController.java
 * @author: Beau
 * @create: 2020/03/28 15:25
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private PartnerInfoService partnerInfoService;

    /**
     * 管理员登录
     *
     * @method: login
     * @param: [reqDto, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/admin/login", produces = "application/json")
    @ApiOperation("管理员登录")
    public ApiResult login(@RequestBody LoginReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<LoginResDto> res = new ApiResult<>();
        try {
            LoginResDto resDto = partnerInfoService.selectByObj(reqDto, request, response);
            res.setCode(resDto.getCode());
            res.setData(resDto);
            resDto.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("login error: ", e);
            res.setCode(ResultCode.FAILED.getCode());
            res.setMsg("管理员登录异常");
        }
        return res;
    }

    /**
     * 管理员注销
     * @method: logout
     * @param: [reqDto, request, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/admin/logout", produces = "application/json")
    @ApiOperation("管理员注销")
    public ApiResult logout(@RequestBody LogoutReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<LogoutResDto> res = new ApiResult<>();
        try {
            LogoutResDto resDto = partnerInfoService.logout(reqDto, request, response);
            res.setCode(resDto.getCode());
            resDto.setMsg("注销登录成功");
        } catch (Exception e) {
            logger.error("logout error: ", e);
            res.setCode(ResultCode.FAILED.getCode());
            res.setMsg("管理员注销异常");
        }
        return res;
    }

    /**
     * 获取注册用户列表
     * @method: getPartnerPage
     * @param: [reqDto]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/private/getPartnerPage", produces = "application/json")
    @ApiOperation("获取注册用户列表")
    public ApiResult getPartnerPage(@RequestBody GetPartnerReqDto reqDto) {
        ApiResult<GetPartnerResDto> res = new ApiResult<>();
        try {
            GetPartnerResDto resDto = partnerInfoService.getPartnerPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            resDto.setMsg("获取注册用户列表成功");
        } catch (Exception e) {
            logger.error("getPartnerPage error: ", e);
            res.setCode(ResultCode.FAILED.getCode());
            res.setMsg("获取注册用户列表异常");
        }
        return res;
    }

}
