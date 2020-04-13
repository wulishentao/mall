package com.beau.graduation.service.impl;

import com.beau.graduation.basic.reqdto.GetOrderPageReqDto;
import com.beau.graduation.basic.resdto.GetOrderPageResDto;
import com.beau.graduation.dao.OrderDao;
import com.beau.graduation.model.Order;
import com.beau.graduation.service.OrderService;
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
	public List<Order> selectList(Order order) {
		return dao.selectList(order);
	}

	@Override
	public int total(Order order) {
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


        return null;
    }
}