package com.beau.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.beau.graduation.Enum.ConfirmStatusEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookDao;
import com.beau.graduation.dao.OrderDao;
import com.beau.graduation.dao.PartnerInfoDao;
import com.beau.graduation.model.*;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.model.dto.BookOrderDto;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.*;
import com.beau.graduation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务层实现类
 * OrderServiceImpl
 * @author beau
 * @date 2020/04/13
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final String ADMIN_TOKEN = "admin_token";

    private static final String FROM_CART = "1";

    private static final String USER_TOKEN = "user_token";

    @Autowired
	OrderDao dao;

    @Autowired
    private OperateOrderService operateOrderService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private BookOrderService bookOrderService;

    @Autowired
    private PartnerInfoDao partnerInfoDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int insert(Order order) {
        return dao.insert(order);
    }

    @Override
    public int batchInsert(List<Order> list) {
    	return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(OrderDto orderDto) {
    	return dao.update(orderDto);
    }

    @Override
    public int delete(Order order) {
    	return dao.delete(order);
    }

    @Override
    public int batchDelete(List<Order> list) {
        return dao.batchDelete(list);
    }

	@Override
	public OrderDto selectByOrderId(String orderId) {
		return dao.selectByOrderId(orderId);
	}

	@Override
	public int total(OrderDto order) {
		return dao.total(order);
	}

    /**
     * 获取订单列表
     * @param reqDto
     * @return
     */
    @Override
    public GetOrderPageResDto getOrderPage(GetOrderPageReqDto reqDto) {
        GetOrderPageResDto resDto = new GetOrderPageResDto();

        OrderDto entity = new OrderDto();
        entity.setOrderId(reqDto.getOrderId());
        entity.setBeginTime(reqDto.getBeginTime());
        entity.setEndTime(reqDto.getEndTime());
        entity.setReceive(reqDto.getReceiver());
        entity.setConfirmStatus(reqDto.getOrderStatus());
        if (reqDto.getUserId() != 0) {
            entity.setUserId(reqDto.getUserId());
        }

        int total = dao.total(entity);
        List<OrderDto> orderList = dao.getOrderPage(entity, PageUtil.getBeginAndSize(reqDto.getPageNo(), reqDto.getPageSize()));
        Page<OrderDto> orderDtoPage = new Page<>(total, orderList);
        resDto.setPage(orderDtoPage);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单列表获取成功");
        return resDto;
    }

    /**
     * 订单发货
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDeliveryResDto orderDelivery(OrderDeliveryReqDto reqDto, HttpServletRequest request) {
        OrderDeliveryResDto resDto = new OrderDeliveryResDto();

        changeOrderStatus(ConfirmStatusEnum.shipped.getCode(), reqDto.getOperateOrders(), request);

        resDto.setMsg("订单发货成功");
        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }

    /**
     * 关闭订单
     * @param reqDto
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderClosedResDto orderClosed(OrderClosedReqDto reqDto, HttpServletRequest request) {
        OrderClosedResDto resDto = new OrderClosedResDto();

        changeOrderStatus(ConfirmStatusEnum.closed.getCode(),reqDto.getOperateOrders(), request);

        resDto.setMsg("关闭订单成功");
        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }

    /**
     * 取消订单
     * @param reqDto
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCancelResDto orderCancel(OrderCancelReqDto reqDto, HttpServletRequest request) {
        OrderCancelResDto resDto = new OrderCancelResDto();

        String cookieValue = CookieUtil.getCookieValue(request, ADMIN_TOKEN);
        PartnerInfo pi = loginUtil.getUser(cookieValue);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(reqDto.getOrderId());
        orderDto.setConfirmStatus(ConfirmStatusEnum.closed.getCode());
        orderDto.setUpdateTime(new Date());
        dao.update(orderDto);
        // 将操作信息存入订单操作记录表中
        OperateOrder operateOrder = new OperateOrder();
        operateOrder.setRemark(reqDto.getRemark());
        operateOrder.setOrderId(reqDto.getOrderId());
        operateOrder.setOperator(pi.getAccountName());
        operateOrder.setOperateTime(new Date());
        operateOrder.setConfirmStatus(ConfirmStatusEnum.closed.getCode());
        operateOrderService.insert(operateOrder);

        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单取消成功");
        return resDto;
    }

    /**
     * 删除订单记录
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDeleteResDto orderDelete(OrderDeleteReqDto reqDto) {
        OrderDeleteResDto resDto = new OrderDeleteResDto();

        List<Order> orders = new ArrayList<>();
        ArrayList<BookOrder> bookOrders = new ArrayList<>();
        List<String> orderIds = reqDto.getOrderIds();
        for (String orderId : orderIds) {
            Order order = new Order();
            BookOrder bookOrder = new BookOrder();
            order.setOrderId(orderId);
            bookOrder.setOrderId(orderId);
            orders.add(order);
            bookOrders.add(bookOrder);
        }
        //删除订单记录
        dao.batchDelete(orders);

        //删除订单记录的书籍记录
        bookOrderService.batchDelete(bookOrders);

        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单记录删除成功");
        return resDto;
    }

    /**
     * 查看订单详情
     * @param reqDto
     * @return
     */
    @Override
    public ViewOrderInfoResDto viewOrderInfo(ViewOrderInfoReqDto reqDto) {
        ViewOrderInfoResDto resDto = new ViewOrderInfoResDto();

        // 查询订单基本信息
        OrderDto orderDto = dao.selectByOrderId(reqDto.getOrderId());

        // 查询订单包含的图书信息
        BookOrder entity = new BookOrder();
        entity.setOrderId(reqDto.getOrderId());
        List<BookOrderDto> bookOrderDtoList = bookOrderService.selectByObj(entity);
        orderDto.setBookOrderDtoList(bookOrderDtoList);

        // 查询订单包含的操作信息
        OperateOrder operateOrder = new OperateOrder();
        operateOrder.setOrderId(reqDto.getOrderId());
        List<OperateOrder> operateOrders = operateOrderService.selectList(operateOrder);
        orderDto.setOperateOrders(operateOrders);

        resDto.setOrderDto(orderDto);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单详情获取成功");
        return resDto;
    }

    /**
     * 改变订单状态
     * @param changedStatus 改变后的订单状态
     * @param operateOrders
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeOrderStatus(String changedStatus, List<OperateOrder> operateOrders, HttpServletRequest request) {
        String cookieValue = CookieUtil.getCookieValue(request, ADMIN_TOKEN);
        PartnerInfo pi = loginUtil.getUser(cookieValue);

        OrderDto orderDto = new OrderDto();
        for (OperateOrder operateOrder : operateOrders) {
            orderDto.setOrderId(operateOrder.getOrderId());
            orderDto.setConfirmStatus(changedStatus);
            orderDto.setLogisticsCompany(operateOrder.getCompanyName());
            orderDto.setShipmentNumber(operateOrder.getShipmentNo());
            orderDto.setUpdateTime(new Date());
            dao.update(orderDto);
            // 将操作信息存入订单操作记录表中
            operateOrder.setOperator(pi.getAccountName());
            operateOrder.setOperateTime(new Date());
            operateOrder.setConfirmStatus(changedStatus);
            operateOrderService.insert(operateOrder);
        }
    }

    /**
     * 确认订单详情页
     * @param reqDto
     * @param request
     * @return
     */
    @Override
    public ConfirmOrderInfoResDto confirmOrderInfo(ConfirmOrderInfoReqDto reqDto, HttpServletRequest request) {
        ConfirmOrderInfoResDto resDto = new ConfirmOrderInfoResDto();
        String userToken = CookieUtil.getCookieValue(request, "user_token");
        PartnerInfo loginUser = loginUtil.getUser(userToken);
        List<BookDto> bookDtoList = reqDto.getBookDtoList();

        PartnerInfo partnerInfo = partnerInfoDao.selectByObj(loginUser);
        PartnerInfo resultPersonal = new PartnerInfo();
        resultPersonal.setId(partnerInfo.getId());
        resultPersonal.setPhone(partnerInfo.getPhone());
        resultPersonal.setAddress(partnerInfo.getAddress());
        resultPersonal.setAccountName(partnerInfo.getAccountName());
        resDto.setUser(resultPersonal);
        if (FROM_CART.equals(reqDto.getOrigin())) {
            // 确认订单中的书籍来自购物车
            ShoppingCart shoppingCart = redisUtil.get("cart_".concat(String.valueOf(loginUser.getId())), ShoppingCart.class);
            List<BookDto> list = shoppingCart.getBookDtoList();
            for (BookDto bookDto : list) {
                for (BookDto dto : bookDtoList) {
                    if (dto.getId().equals(bookDto.getId())) {
                        dto.setImgUrl(bookDto.getImgUrl());
                        dto.setPrice(bookDto.getPrice());
                    }
                }
            }
            resDto.setBookDtoList(bookDtoList);
            resDto.setOrigin(FROM_CART);
            resDto.setCode(ResultCode.success.getCode());
            resDto.setMsg("确认订单信息获取成功");
            return resDto;
        }

        // 确认订单书籍来自直接购买
        BookDto bookDto = bookDtoList.get(0);
        BookDto dto = bookService.selectById(bookDto.getId());
        dto.setAmount(bookDto.getAmount());
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(dto);
        resDto.setBookDtoList(bookDtos);
        resDto.setOrigin("0");
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("确认订单信息获取成功");
        return resDto;
    }

    /**
     * 提交订单
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SubmitOrderResDto submitOrder(SubmitOrderReqDto reqDto) {
        SubmitOrderResDto resDto = new SubmitOrderResDto();
        List<BookDto> bookDtoList = reqDto.getBookDtoList();
        ArrayList<BookOrderDto> bookOrderDtos = new ArrayList<>();

        // 生成订单号
        String orderId = DateUtil.dateTime().concat(UuidUtil.getUuid().substring(0,7));
        for (BookDto bookDto : bookDtoList) {
            BookDto book = bookService.selectByBookId(bookDto.getId());
            if (book.getReserve() < bookDto.getAmount()) {
                // 该书籍库存不足
                resDto.setCode(ResultCode.failed.getCode());
                resDto.setMsg(bookDto.getTitle() + " 库存不足");
                return resDto;
            }
        }
        for (BookDto bookDto : bookDtoList) {
            BookDto book = bookService.selectByBookId(bookDto.getId());
            BookOrderDto bookOrderDto = new BookOrderDto();
            bookOrderDto.setOrderId(orderId);
            bookOrderDto.setAmount(bookDto.getAmount());
            bookOrderDto.setBookId(bookDto.getId());
            bookOrderDto.setCreateTime(DateUtil.getNowDate());
            bookOrderDto.setTotalPrice(book.getPrice().multiply(new BigDecimal(String.valueOf(bookDto.getAmount()))));
            // 调整库存数量
            Book entity = new Book();
            entity.setId(book.getId());
            entity.setReserve(book.getReserve() - bookDto.getAmount());
            bookService.update(entity);
            bookOrderDtos.add(bookOrderDto);
        }
        // 将所有记录存入书籍订单表
        bookOrderService.batchInsert(bookOrderDtos);

        Order entity = new Order();
        entity.setUserId(reqDto.getUserId());
        entity.setConfirmStatus(ConfirmStatusEnum.pending_pay.getCode());
        entity.setCreateTime(DateUtil.getNowDate());
        entity.setUpdateTime(DateUtil.getNowDate());
        entity.setOrderId(orderId);
        // 存入订单表
        dao.insert(entity);
        if (FROM_CART.equals(reqDto.getOrigin())) {
            // 订单来自购物车,删除购物车中的相应记录
            String key = "cart_" + reqDto.getUserId();
            List<Long> bookIds = bookDtoList.stream().map(Book::getId).collect(Collectors.toList());
            ShoppingCart shoppingCart = delCartWithKey(key, bookIds);
            redisUtil.getAndSet(key, JSONObject.toJSONString(shoppingCart));
        }
        resDto.setOrderId(orderId);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单提交成功");
        return resDto;
    }

    /**
     * 根据key删除redis中的购物车商品
     * @param key
     * @param bookIds
     * @return
     */
    private ShoppingCart delCartWithKey(String key, List<Long> bookIds) {
        ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
        List<BookDto> collect = new ArrayList<>();
        List<BookDto> bookDtoList = shoppingCart.getBookDtoList();
        for (Long bookId : bookIds) {
            for (BookDto dto : bookDtoList) {
                if (dto.getId().equals(bookId)) {
                    collect.add(dto);
                    break;
                }
            }
        }
        bookDtoList.removeAll(collect);
        shoppingCart.setBookDtoList(bookDtoList);
        return shoppingCart;
    }

    /**
     * 确认支付
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConfirmPaymentResDto confirmPayment(ConfirmPaymentReqDto reqDto, HttpServletRequest request) {
        ConfirmPaymentResDto resDto = new ConfirmPaymentResDto();
        String token = CookieUtil.getCookieValue(request, USER_TOKEN);
        PartnerInfo pi = loginUtil.getUser(token);

        // 修改订单状态为代发货
        String status = ConfirmStatusEnum.pending_delivered.getCode();
        String remark = "用户确认付款";
        userChangeConfirmStatus(status, reqDto.getOrderId(), pi.getAccountName(), remark);


        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("购买成功");
        return resDto;
    }

    /**
     * 更改订单状态且存储操作信息
     * @param status
     * @param orderId
     * @param accountName
     * @param remark
     */
    private void userChangeConfirmStatus(String status, String orderId, String accountName, String remark) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderId);
        orderDto.setConfirmStatus(status);
        dao.update(orderDto);

        OperateOrder operateOrder = new OperateOrder();
        operateOrder.setConfirmStatus(status);
        operateOrder.setOperator(accountName);
        operateOrder.setOrderId(orderId);
        operateOrder.setRemark(remark);
        operateOrder.setOperateTime(new Date());
        operateOrderService.insert(operateOrder);
    }

    /**
     * 用户取消订单
     *
     * @param reqDto
     * @return
     */
    @Override
    public CancelOrderResDto cancelOrder(CancelOrderReqDto reqDto, HttpServletRequest request) {
        CancelOrderResDto resDto = new CancelOrderResDto();
        String userToken = CookieUtil.getCookieValue(request, USER_TOKEN);
        PartnerInfo partnerInfo = loginUtil.getUser(userToken);

        String status = ConfirmStatusEnum.closed.getCode();
        String remark = "用户主动取消订单";
        userChangeConfirmStatus(status, reqDto.getOrderId(), partnerInfo.getAccountName(), remark);

        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("订单取消成功");
        return resDto;
    }

    /**
     * 确认收货
     * @param reqDto
     * @param request
     * @return
     */
    @Override
    public ConfirmOrderResDto confirmOrder(ConfirmOrderReqDto reqDto, HttpServletRequest request) {
        ConfirmOrderResDto resDto = new ConfirmOrderResDto();
        String userToken = CookieUtil.getCookieValue(request, USER_TOKEN);
        PartnerInfo partnerInfo = loginUtil.getUser(userToken);

        String status = ConfirmStatusEnum.received.getCode();
        String remark = "用户确认收货";
        userChangeConfirmStatus(status, reqDto.getOrderId(), partnerInfo.getAccountName(), remark);

        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("确认收货成功");
        return resDto;
    }

}