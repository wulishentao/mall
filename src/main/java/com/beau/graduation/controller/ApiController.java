package com.beau.graduation.controller;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.annotation.CheckOrder;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.service.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private BookRelationTopicService relationTopicService;

    @Autowired
    private OrderService orderService;

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
            res.setCode(ResultCode.failed.getCode());
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
            res.setMsg("注销登录成功");
        } catch (Exception e) {
            logger.error("logout error: ", e);
            res.setCode(ResultCode.failed.getCode());
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
    @PostMapping(value = "/private/user/getPartnerPage", produces = "application/json")
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
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取注册用户列表异常");
        }
        return res;
    }

    @PostMapping(value = "/private/user/editPartnerStatus", produces = "application/json")
    @ApiOperation("启用/禁用用户")
    public ApiResult editPartnerStatus(@RequestBody EditPartnerStatusReqDto reqDto) {
        ApiResult<EditPartnerStatusResDto> res = new ApiResult<>();
        try {
            EditPartnerStatusResDto resDto = partnerInfoService.editPartnerStatus(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("editPartnerStatus error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("启用/禁用用户异常");
        }
        return res;
    }

    /**
     * 获取书籍列表
     * @method: getCommodityPage
     * @param: [reqDto]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/private/commodity/getCommodityPage", produces = "application/json")
    @ApiOperation("获取商品书籍列表")
    public ApiResult getCommodityPage(@RequestBody GetCommodityReqDto reqDto) {
        ApiResult<GetCommodityResDto> res = new ApiResult<>();
        try {
            GetCommodityResDto resDto = bookService.getCommodityPage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            resDto.setMsg("获取商品书籍列表成功");
        } catch (Exception e) {
            logger.error("getCommodityPage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取商品列表异常");
        }
        return res;
    }

    /**
     * 添加书籍
     * @method: addCommodity
     * @param: [reqDto]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/private/commodity/addCommodity", produces = "application/json")
    @ApiOperation("添加书籍")
    public ApiResult addCommodity(AddCommodityReqDto reqDto) {
        ApiResult<AddCommodityResDto> res = new ApiResult<>();
        try {
            AddCommodityResDto resDto = bookService.addCommodity(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("添加书籍成功");
        } catch (Exception e) {
            logger.error("addCommodity error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("添加书籍异常");
        }
        return res;
    }

    /**
     * 书籍详情
     * @method: commodityDetailed
     * @param: [reqDto]
     * @return: com.beau.graduation.common.ApiResult
     */
    @PostMapping(value = "/private/commodity/commodityDetailed", produces = "application/json")
    @ApiOperation("书籍详情")
    public ApiResult commodityDetailed(@RequestBody CommodityDetailedReqDto reqDto) {
        ApiResult<CommodityDetailedResDto> res = new ApiResult<>();
        try {
            CommodityDetailedResDto resDto = bookService.commodityDetailed(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg("获取书籍详情成功");
        } catch (Exception e) {
            logger.error("commodityDetailed error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取书籍详情异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/editCommodity", produces = "application/json")
    @ApiOperation("编辑书籍信息")
    public ApiResult editCommodity(EditCommodityReqDto reqDto) {
        ApiResult<EditCommodityResDto> res = new ApiResult<>();
        try {
            EditCommodityResDto resDto = bookService.editCommodity(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("编辑书籍信息成功");
        } catch (Exception e) {
            logger.error("editCommodity error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("编辑书籍信息异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/delCommodity", produces = "application/json")
    @ApiOperation("删除书籍")
    public ApiResult delCommodity(@RequestBody DelCommodityReqDto reqDto) {
        ApiResult<DelCommodityResDto> res = new ApiResult<>();
        try {
            DelCommodityResDto resDto = bookService.delCommodity(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("删除书籍成功");
        } catch (Exception e) {
            logger.error("delCommodity error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("删除书籍异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/getCommodityTypePage", produces = "application/json")
    @ApiOperation("获取书籍标签列表")
    public ApiResult getCommodityTypePage(@RequestBody GetCommodityTypeReqDto reqDto) {
        ApiResult<GetCommodityTypeResDto> res = new ApiResult<>();
        try {
            GetCommodityTypeResDto resDto = bookTypeService.getCommodityTypePage(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg("获取书籍标签列表成功");
        } catch (Exception e) {
            logger.error("getCommodityTypePage error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取书籍标签列表异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/addCommodityType", produces = "application/json")
    @ApiOperation("添加书籍标签")
    public ApiResult addCommodityType(@RequestBody AddCommodityTypeReqDto reqDto) {
        ApiResult<AddCommodityTypeResDto> res = new ApiResult<>();
        try {
            AddCommodityTypeResDto resDto = bookTypeService.addCommodityType(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("addCommodityType error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("添加书籍标签异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/editCommodityType", produces = "application/json")
    @ApiOperation("编辑书籍标签")
    public ApiResult editCommodityType(@RequestBody EditCommodityTypeReqDto reqDto) {
        ApiResult<EditCommodityTypeResDto> res = new ApiResult<>();
        try {
            EditCommodityTypeResDto resDto = bookTypeService.editCommodityType(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("添加书籍标签成功");
        } catch (Exception e) {
            logger.error("editCommodityType error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("编辑书籍标签异常");
        }
        return res;
    }

    @PostMapping(value = "/private/commodity/delCommodityType", produces = "application/json")
    @ApiOperation("删除书籍标签")
    public ApiResult delCommodityType(@RequestBody DelCommodityTypeReqDto reqDto) {
        ApiResult<DelCommodityTypeResDto> res = new ApiResult<>();
        try {
            DelCommodityTypeResDto resDto = bookTypeService.delCommodityType(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("删除书籍标签成功");
        } catch (Exception e) {
            logger.error("delCommodityType error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("删除书籍标签异常");
        }
        return res;
    }

    @PostMapping(value = "/private/order/getOrderPage", produces = "application/json")
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

    @PostMapping(value = "/private/order/delivery", produces = "application/json")
    @ApiOperation("订单发货")
    public ApiResult orderDelivery(@RequestBody OrderDeliveryReqDto reqDto, HttpServletRequest request) {
        ApiResult<OrderDeliveryResDto> res = new ApiResult<>();
        try {
            OrderDeliveryResDto resDto = orderService.orderDelivery(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("orderDelivery error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("订单发货异常");
        }
        return res;
    }

    @PostMapping(value = "/private/order/closed", produces = "application/json")
    @ApiOperation("订单关闭")
    public ApiResult orderClosed(@RequestBody OrderClosedReqDto reqDto, HttpServletRequest request) {
        ApiResult<OrderClosedResDto> res = new ApiResult<>();
        try {
            OrderClosedResDto resDto = orderService.orderClosed(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("orderClosed error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("订单关闭异常");
        }
        return res;
    }

    @PostMapping(value = "/private/order/cancel", produces = "application/json")
    @ApiOperation("取消订单")
    public ApiResult orderCancel(@RequestBody OrderCancelReqDto reqDto, HttpServletRequest request) {
        ApiResult<OrderCancelResDto> res = new ApiResult<>();
        try {
            OrderCancelResDto resDto = orderService.orderCancel(reqDto, request);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("orderCancel error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("取消订单异常");
        }
        return res;
    }

    @PostMapping(value = "/private/order/delete", produces = "application/json")
    @ApiOperation("删除订单记录")
    public ApiResult orderDelete(@RequestBody OrderDeleteReqDto reqDto) {
        ApiResult<OrderDeleteResDto> res = new ApiResult<>();
        try {
            OrderDeleteResDto resDto = orderService.orderDelete(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("orderDelete error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("删除订单记录异常");
        }
        return res;
    }

    @PostMapping(value = "/private/order/view", produces = "application/json")
    @ApiOperation("查看订单详情")
    @CheckOrder
    public ApiResult viewOrderInfo(@RequestBody ViewOrderInfoReqDto reqDto) {
        ApiResult<ViewOrderInfoResDto> res = new ApiResult<>();
        try {
            ViewOrderInfoResDto resDto = orderService.viewOrderInfo(reqDto);
            res.setData(resDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("viewOrderInfo error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("查看订单详情异常");
        }
        return res;
    }

    @PostMapping(value = "/private/marketing/addTopic", produces = "application/json")
    @ApiOperation("添加活动专题")
    public ApiResult addTopic(AddTopicReqDto reqDto) {
        ApiResult<AddTopicResDto> res = new ApiResult<>();
        try {
            AddTopicResDto resDto = topicService.addTopic(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("添加活动专题成功");
        } catch (Exception e) {
            logger.error("addTopic error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("添加活动专题异常");
        }
        return res;
    }

    @PostMapping(value = "/private/topic/getTopicPage", produces = "application/json")
    @ApiOperation("后台获取活动专题")
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
            res.setMsg("后台获取活动专题异常");
        }
        return res;
    }

    @PostMapping(value = "/private/topic/editTopic", produces = "application/json")
    @ApiOperation("编辑专题")
    public ApiResult editTopic(@RequestBody EditTopicReqDto reqDto) {
        ApiResult<EditTopicResDto> res = new ApiResult<>();
        try {
            EditTopicResDto resDto = topicService.editTopic(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("修改专题成功");
        } catch (Exception e) {
            logger.error("editTopic error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("修改专题异常");
        }
        return res;
    }

    @PostMapping(value = "/private/topic/deleteTopic", produces = "application/json")
    @ApiOperation("删除专题")
    public ApiResult deleteTopic(@RequestBody DeleteTopicReqDto reqDto) {
        ApiResult<DeleteTopicResDto> res = new ApiResult<>();
        try {
            DeleteTopicResDto resDto = topicService.deleteTopic(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("删除专题成功");
        } catch (Exception e) {
            logger.error("deleteTopic error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("删除专题异常");
        }
        return res;
    }

    @PostMapping(value = "/private/topic/getRelatableBooks", produces = "application/json")
    @ApiOperation("获取专题可关联图书")
    public ApiResult getRelatableBooks(@RequestBody GetRelatableBooksReqDto reqDto) {
        ApiResult<GetRelatableBooksResDto> res = new ApiResult<>();
        try {
            GetRelatableBooksResDto resDto = topicService.getRelatableBooks(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg(resDto.getMsg());
        } catch (Exception e) {
            logger.error("getRelatableBooks error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("获取专题可关联图书异常");
        }
        return res;
    }

    @PostMapping(value = "/private/marketing/batchRelatedBook", produces = "application/json")
    @ApiOperation("专题批量关联图书")
    public ApiResult batchRelatedBook(@RequestBody RelatedBookReqDto reqDto) {
        ApiResult<RelatedBookResDto> res = new ApiResult<>();
        try {
            RelatedBookResDto resDto = relationTopicService.batchRelatedBook(reqDto);
            res.setCode(resDto.getCode());
            res.setMsg("专题批量关联图书成功");
        } catch (Exception e) {
            logger.error("batchRelatedBook error: ", e);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("专题批量关联图书异常");
        }
        return res;
    }

}
