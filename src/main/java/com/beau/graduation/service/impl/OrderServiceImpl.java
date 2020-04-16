package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.GetOrderPageReqDto;
import com.beau.graduation.basic.resdto.GetOrderPageResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.OrderDao;
import com.beau.graduation.model.Order;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.OrderService;
import com.beau.graduation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * OrderServiceImpl
 * @author beau
 * @date 2020/04/13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
	OrderDao dao;

    @Override
    public int insert(Order order) {
        return dao.insert(order);
    }

    @Override
    public int batchInsert(List<Order> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(Order order) {
    	return dao.update(order);
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
	public Order selectByObj(Order order) {
		return dao.selectByObj(order);
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
}