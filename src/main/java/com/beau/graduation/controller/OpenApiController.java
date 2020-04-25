package com.beau.graduation.controller;

import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.service.BookService;
import com.beau.graduation.service.OrderService;
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

    @Autowired
    private OrderService orderService;

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
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("用户登录异常");
        }
        return res;
    }

    @PostMapping(value = "/user/logout", produces = "application/json")
    @ApiOperation("用户注销")
    public ApiResult logout(@RequestBody LogoutReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<LogoutResDto> res = new ApiResult<>();
        try {
            LogoutResDto resDto = partnerInfoService.logout(reqDto, request, response);
            res.setCode(resDto.getCode());
            res.setMsg("注销登录成功");
        } catch (Exception e) {
            logger.error("logout error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("用户注销异常");
        }
        return res;
    }

    /**
     * 用户注册
     * @method: register
     * @param: [registerReqDto]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/user/register", produces = "application/json")
    @ApiOperation("用户注册")
    public ApiResult register(@RequestBody RegisterReqDto registerReqDto) {
        ApiResult<RegisterResDto> res = new ApiResult<>();
        try {
            RegisterResDto resDto = partnerInfoService.insert(registerReqDto);
            res.setMsg(resDto.getMsg());
            res.setCode(resDto.getCode());
        } catch (Exception e) {
            logger.error("register error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("注册异常");
        }
        return res;
    }

    /**
     * 添加书籍进购物车
     * @method: addShoppingCart
     * @param: [addCartReqDto, request, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/shoppingCart/add", produces = "application/json")
    @ApiOperation("添加书籍进购物车")
    public ApiResult addShoppingCart(@RequestBody AddCartReqDto addCartReqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<AddCartResDto> res = new ApiResult<>();
        try {
            AddCartResDto resDto = bookService.addCart(addCartReqDto, request, response);
            res.setCode(resDto.getCode());
            res.setMsg("添加购物车成功");
        } catch (Exception e) {
            logger.error("addShoppingCart error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("加入购物车异常");
        }
        return res;
    }

    /**
     * 获取购物车商品
     * @method: getShoppingCart
     * @param: [syncCartReqDto, request, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/shoppingCart/get", produces = "application/json")
    @ApiOperation("获取购物车商品")
    public ApiResult getShoppingCart(@RequestBody SyncCartReqDto syncCartReqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<SyncCartResDto> res = new ApiResult<>();
        try {
            SyncCartResDto resDto = bookService.getShoppingCart(syncCartReqDto, request, response);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getShoppingCart error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("查看购物车异常");
        }
        return res;
    }

    /**
     * 同步购物车
     * @method: syncShoppingCart
     * @param: [syncCartReqDto, request, response]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/shoppingCart/sync", produces = "application/json")
    @ApiOperation("同步购物车")
    public ApiResult syncShoppingCart(@RequestBody SyncCartReqDto syncCartReqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<SyncCartResDto> res = new ApiResult<>();
        try {
            SyncCartResDto resDto = bookService.syncShoppingCart(syncCartReqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("syncShoppingCart error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("同步购物车异常");
        }
        return res;
    }

    @PostMapping(value = "/shoppingCart/delete", produces = "application/json")
    @ApiOperation("删除购物车商品")
    public ApiResult delShoppingCart(@RequestBody DelCartReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        ApiResult<DelCartResDto> res = new ApiResult<>();
        try {
            DelCartResDto resDto = bookService.delShoppingCart(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("delShoppingCart error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("删除购物车商品异常");
        }
        return res;
    }

    @PostMapping(value = "/order/confirmOrderInfo", produces = "application/json")
    @ApiOperation("确认订单页面信息获取")
    public ApiResult confirmOrderInfo(@RequestBody ConfirmOrderInfoReqDto reqDto, HttpServletRequest request) {
        ApiResult<ConfirmOrderInfoResDto> res = new ApiResult<>();
        try {
            ConfirmOrderInfoResDto resDto = orderService.confirmOrderInfo(reqDto, request);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("confirmOrderInfo error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("确认订单页面信息获取异常");
        }
        return res;
    }



}
