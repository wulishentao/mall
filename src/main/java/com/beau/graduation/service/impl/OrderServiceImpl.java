package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ConfirmStatusEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.OrderDao;
import com.beau.graduation.model.BookOrder;
import com.beau.graduation.model.OperateOrder;
import com.beau.graduation.model.Order;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.dto.BookOrderDto;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.BookOrderService;
import com.beau.graduation.service.OperateOrderService;
import com.beau.graduation.service.OrderService;
import com.beau.graduation.utils.CookieUtil;
import com.beau.graduation.utils.LoginUtil;
import com.beau.graduation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
	OrderDao dao;

    @Autowired
    private OperateOrderService operateOrderService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private BookOrderService bookOrderService;

    @Override
    public int insert(Order order) {
        return dao.insert(order);
    }

    @Override
    public int batchInsert(List<Order> list) {
    	return dao.batchInsert(list);
    }

    @Override
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
    public OrderDeleteResDto orderDelete(OrderDeleteReqDto reqDto) {
        OrderDeleteResDto resDto = new OrderDeleteResDto();

        List<Order> orders = new ArrayList<>();
        List<String> orderIds = reqDto.getOrderIds();
        for (String orderId : orderIds) {
            Order order = new Order();
            order.setOrderId(orderId);
            orders.add(order);
        }
        dao.batchDelete(orders);

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

        //查询订单包含的图书信息
        BookOrder entity = new BookOrder();
        entity.setOrderId(reqDto.getOrderId());
        BookOrderDto bookOrderDto = bookOrderService.selectByObj(entity);
        orderDto.setBookOrderDto(bookOrderDto);

        //查询订单包含的操作信息
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
            orderDto.setUpdateTime(new Date());
            dao.update(orderDto);
            // 将操作信息存入订单操作记录表中
            operateOrder.setOperator(pi.getAccountName());
            operateOrder.setOperateTime(new Date());
            operateOrderService.insert(operateOrder);
        }
    }

}