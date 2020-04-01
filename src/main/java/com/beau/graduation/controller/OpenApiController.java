package com.beau.graduation.controller;

import com.beau.graduation.basic.reqdto.AddCartReqDto;
import com.beau.graduation.basic.reqdto.LoginReqDto;
import com.beau.graduation.basic.reqdto.RegisterReqDto;
import com.beau.graduation.basic.resdto.AddCartResDto;
import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.basic.resdto.RegisterResDto;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.service.BookService;
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
@RequestMapping("/openApi")
public class OpenApiController {
    private static final Logger logger = LoggerFactory.getLogger(OpenApiController.class);

    @Autowired
    private PartnerInfoService partnerInfoService;

    @Autowired
    private BookService bookService;

    /**
     * 用户登录
     *
     * @method: login
     * @param: [reqDto, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/user/login", produces = "application/json")
    @ApiOperation("用户登录")
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
            res.setMsg("用户登录异常");
        }
        return res;
    }

    /**
     * 用户注册
     * @param
     * @return
     */
    @PostMapping(value = "/user/register", produces = "application/json")
    @ApiOperation("用户注册")
    public ApiResult register(@RequestBody RegisterReqDto registerReqDto) {
        ApiResult<RegisterResDto> res = new ApiResult<>();
        RegisterResDto resDto;
        try {
            resDto = partnerInfoService.insert(registerReqDto);
            res.setMsg(resDto.getMsg());
            res.setCode(resDto.getCode());
        } catch (Exception e) {
            logger.error("register error: ", e);
            res.setCode(ResultCode.FAILED.getCode());
            res.setMsg("注册异常");
        }
        return res;
    }


    @PostMapping(value = "/shoppingCart/add", produces = "application/json")
    @ApiOperation("加入购物车")
    public ApiResult addShoppingCart(@RequestBody AddCartReqDto addCartReqDto, HttpServletRequest request) {
        ApiResult<RegisterResDto> res = new ApiResult<>();
        AddCartResDto resDto;
        try {
            resDto = bookService.addCart(addCartReqDto,request);
        } catch (Exception e) {
            logger.error("addShoppingCart error: ", e);
            res.setCode(ResultCode.FAILED.getCode());
            res.setMsg("加入购物车异常");
        }
        return res;
    }
}
