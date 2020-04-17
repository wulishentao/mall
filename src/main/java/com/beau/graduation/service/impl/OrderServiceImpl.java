package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ConfirmStatusEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.GetOrderPageReqDto;
import com.beau.graduation.basic.reqdto.OrderDeliveryReqDto;
import com.beau.graduation.basic.resdto.GetOrderPageResDto;
import com.beau.graduation.basic.resdto.OrderDeliveryResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.OrderDao;
import com.beau.graduation.model.OperateOrder;
import com.beau.graduation.model.Order;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.OperateOrderService;
import com.beau.graduation.service.OrderService;
import com.beau.graduation.utils.CookieUtil;
import com.beau.graduation.utils.LoginUtil;
import com.beau.graduation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    private static final String token = "admin_token";

    @Autowired
	OrderDao dao;

    @Autowired
    private OperateOrderService operateOrderService;

    @Autowired
    private LoginUtil loginUtil;

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
        String cookieValue = CookieUtil.getCookieValue(request, token);
        PartnerInfo pi = loginUtil.getUser(cookieValue);

        List<String> collect = reqDto.getOrderIds().stream().filter(orderId -> {
            OrderDto orderDto = dao.selectByOrderId(orderId);
            return ConfirmStatusEnum.pending_delivered.getCode().equals(orderDto.getConfirmStatus());
        }).collect(Collectors.toList());
        if (collect.size() == 0) {
            resDto.setCode(ResultCode.no_actionable_item.getCode());
            resDto.setMsg("无可操作项");
            return resDto;
        }
        OrderDto orderDto = new OrderDto();
        for (String orderId : collect) {
            orderDto.setOrderId(orderId);
            orderDto.setConfirmStatus(ConfirmStatusEnum.shipped.getCode());
            orderDto.setUpdateTime(new Date());
            dao.update(orderDto);
            // 将操作信息存入订单操作记录表中
            OperateOrder operateOrder = new OperateOrder();
            operateOrder.setOperateName(pi.getAccountName());
            operateOrder.setOperateTime(new Date());
            operateOrder.setOrderId(orderId);
            operateOrderService.insert(operateOrder);
        }

        resDto.setMsg("订单发货成功");
        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }
}