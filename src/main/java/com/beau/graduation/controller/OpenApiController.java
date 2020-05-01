package com.beau.graduation.controller;

import com.beau.graduation.annotation.CheckOrder;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.service.*;
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

    @Autowired
    private BookCommentService bookCommentService;

    @Autowired
    private BookOrderService bookOrderService;

    @Autowired
    private TopicService topicService;

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

    @PostMapping(value = "/order/submitOrder", produces = "application/json")
    @ApiOperation("提交订单")
    public ApiResult submitOrder(@RequestBody SubmitOrderReqDto reqDto) {
        ApiResult<SubmitOrderResDto> res = new ApiResult<>();
        try {
            SubmitOrderResDto resDto = orderService.submitOrder(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("submitOrder error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("提交订单异常");
        }
        return res;
    }

    @CheckOrder
    @PostMapping(value = "/order/confirmPayment", produces = "application/json")
    @ApiOperation("用户确认支付")
    public ApiResult confirmPayment(@RequestBody ConfirmPaymentReqDto reqDto, HttpServletRequest request) {
        ApiResult<ConfirmPaymentResDto> res = new ApiResult<>();
        try {
            ConfirmPaymentResDto resDto = orderService.confirmPayment(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("confirmPayment error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("确认支付异常");
        }
        return res;
    }

    @PostMapping(value = "/order/cancel", produces = "application/json")
    @ApiOperation("用户取消订单")
    public ApiResult cancelOrder(@RequestBody CancelOrderReqDto reqDto, HttpServletRequest request) {
        ApiResult<CancelOrderResDto> res = new ApiResult<>();
        try {
            CancelOrderResDto resDto = orderService.cancelOrder(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("confirmPayment error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("确认支付异常");
        }
        return res;
    }

    @PostMapping(value = "/order/confirm", produces = "application/json")
    @ApiOperation("用户确认收货")
    public ApiResult confirmOrder(@RequestBody ConfirmOrderReqDto reqDto, HttpServletRequest request) {
        ApiResult<ConfirmOrderResDto> res = new ApiResult<>();
        try {
            ConfirmOrderResDto resDto = orderService.confirmOrder(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("confirmOrder error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("用户确认收货异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/getCommentPage", produces = "application/json")
    @ApiOperation("获取待评价/已评价列表")
    public ApiResult getCommentPage(@RequestBody GetCommentPageReqDto reqDto) {
        ApiResult<GetCommentPageResDto> res = new ApiResult<>();
        try {
            GetCommentPageResDto resDto = bookOrderService.getCommentPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getCommentPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取待评价/已评价列表异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/comment", produces = "application/json")
    @ApiOperation("评价图书")
    public ApiResult commentBook(@RequestBody CommentBookReqDto reqDto) {
        ApiResult<CommentBookResDto> res = new ApiResult<>();
        try {
            CommentBookResDto resDto = bookCommentService.commentBook(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("commentBook error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("图书评价异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/home", produces = "application/json")
    @ApiOperation("个人中心")
    public ApiResult home(@RequestBody HomeReqDto reqDto, HttpServletRequest request) {
        ApiResult<HomeResDto> res = new ApiResult<>();
        try {
            HomeResDto resDto = partnerInfoService.home(reqDto, request);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("home error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("个人中心获取异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/edit", produces = "application/json")
    @ApiOperation("修改个人信息")
    public ApiResult edit(@RequestBody EditReqDto reqDto) {
        ApiResult<EditResDto> res = new ApiResult<>();
        try {
            EditResDto resDto = partnerInfoService.edit(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("edit error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("修改个人信息异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/getOrderPage", produces = "application/json")
    @ApiOperation("获取订单列表")
    public ApiResult getOrderPage(@RequestBody GetOrderPageReqDto reqDto) {
        ApiResult<GetOrderPageResDto> res = new ApiResult<>();
        try {
            GetOrderPageResDto resDto = orderService.getOrderPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getOrderPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取订单列表异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/getOrderInfo", produces = "application/json")
    @ApiOperation("获取订单详情")
    public ApiResult getOrderInfo(@RequestBody ViewOrderInfoReqDto reqDto) {
        ApiResult<ViewOrderInfoResDto> res = new ApiResult<>();
        try {
            ViewOrderInfoResDto resDto = orderService.viewOrderInfo(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getOrderInfo error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取订单异常");
        }
        return res;
    }

    @PostMapping(value = "/personal/getBoughtBookPage", produces = "application/json")
    @ApiOperation("已购买的图书列表")
    public ApiResult getBoughtBookPage(@RequestBody GetBoughtBookPageReqDto reqDto) {
        ApiResult<GetBoughtBookPageResDto> res = new ApiResult<>();
        try {
            GetBoughtBookPageResDto resDto = bookOrderService.getBoughtBookPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getOrderInfo error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取已购买的图书列表异常");
        }
        return res;
    }

    @PostMapping(value = "/home/globalSearch", produces = "application/json")
    @ApiOperation("首页搜索")
    public ApiResult globalSearch(@RequestBody GlobalSearchReqDto reqDto) {
        ApiResult<GlobalSearchResDto> res = new ApiResult<>();
        try {
            GlobalSearchResDto resDto = bookService.globalSearch(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("globalSearch error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("首页搜索异常");
        }
        return res;
    }

    @PostMapping(value = "/home/getTopicPage", produces = "application/json")
    @ApiOperation("专题滚动栏")
    public ApiResult getTopicPage(@RequestBody GetTopicPageReqDto reqDto) {
        ApiResult<GetTopicPageResDto> res = new ApiResult<>();
        try {
            GetTopicPageResDto resDto = topicService.getTopicPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getTopicPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("专题滚动栏获取异常");
        }
        return res;
    }

    @PostMapping(value = "/home/getNewBook", produces = "application/json")
    @ApiOperation("新书上架")
    public ApiResult getNewBook(@RequestBody GetNewBookReqDto reqDto) {
        ApiResult<GetNewBookResDto> res = new ApiResult<>();
        try {
            GetNewBookResDto resDto = bookService.getNewBook(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getNewBook error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("新书上架异常");
        }
        return res;
    }

    @PostMapping(value = "/home/newBookHotPage", produces = "application/json")
    @ApiOperation("新书热卖榜")
    public ApiResult newBookHotPage(@RequestBody NewBookHotPageReqDto reqDto) {
        ApiResult<NewBookHotPageResDto> res = new ApiResult<>();
        try {
            NewBookHotPageResDto resDto = bookService.newBookHotPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("newBookHotPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("新书热卖榜异常");
        }
        return res;
    }

    @PostMapping(value = "/home/bookSellingPage", produces = "application/json")
    @ApiOperation("图书畅销榜")
    public ApiResult bookSellingPage(@RequestBody BookSellingPageReqDto reqDto) {
        ApiResult<BookSellingPageResDto> res = new ApiResult<>();
        try {
            BookSellingPageResDto resDto = bookService.bookSellingPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("bookSellingPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("图书畅销榜异常");
        }
        return res;
    }

    @PostMapping(value = "/home/recommend", produces = "application/json")
    @ApiOperation("主编推荐")
    public ApiResult recommend(@RequestBody RecommendReqDto reqDto) {
        ApiResult<RecommendResDto> res = new ApiResult<>();
        try {
            RecommendResDto resDto = bookService.recommend(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("recommend error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("主编推荐获取异常");
        }
        return res;
    }

}
