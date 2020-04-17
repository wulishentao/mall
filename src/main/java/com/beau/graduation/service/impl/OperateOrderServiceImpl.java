package com.beau.graduation.service.impl;

import com.beau.graduation.dao.OperateOrderDao;
import com.beau.graduation.model.OperateOrder;
import com.beau.graduation.service.OperateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层实现类
 * OperateOrderServiceImpl
 * @author beau
 * @date 2020/04/17
 */
@Service
public class OperateOrderServiceImpl implements OperateOrderService {

    @Autowired
    OperateOrderDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(OperateOrder operateOrder) {
        return dao.insert(operateOrder);
    }

    @Override
    public int batchInsert(List<OperateOrder> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(OperateOrder operateOrder) {
    	return dao.update(operateOrder);
    }

    @Override
    public int delete(OperateOrder operateOrder) {
    	return dao.delete(operateOrder);
    }

    @Override
    public int batchDelete(List<OperateOrder> list) {
        return dao.batchDelete(list);
    }

	@Override
	public OperateOrder selectByObj(OperateOrder operateOrder) {
		return dao.selectByObj(operateOrder);
	}

	@Override
	public List<OperateOrder> selectList(OperateOrder operateOrder) {
		return dao.selectList(operateOrder);
	}

	@Override
	public int total(OperateOrder operateOrder) {
		return dao.total(operateOrder);
	}
}