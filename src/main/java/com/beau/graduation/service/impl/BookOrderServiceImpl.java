package com.beau.graduation.service.impl;

import com.beau.graduation.dao.BookOrderDao;
import com.beau.graduation.model.BookOrder;
import com.beau.graduation.model.dto.BookOrderDto;
import com.beau.graduation.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * BookOrderServiceImpl
 * @author beau
 * @date 2020/04/17
 */
@Service
public class BookOrderServiceImpl implements BookOrderService {

    @Autowired
    BookOrderDao dao;

    @Override
    public int insert(BookOrder bookOrder) {
        return dao.insert(bookOrder);
    }

    @Override
    public int batchInsert(List<BookOrder> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookOrder bookOrder) {
    	return dao.update(bookOrder);
    }

    @Override
    public int delete(BookOrder bookOrder) {
    	return dao.delete(bookOrder);
    }

    @Override
    public int batchDelete(List<BookOrder> list) {
        return dao.batchDelete(list);
    }

	@Override
	public List<BookOrderDto> selectByObj(BookOrder bookOrder) {
		return dao.getBookByOrderId(bookOrder);
	}

	@Override
	public List<BookOrder> selectList(BookOrder bookOrder) {
		return dao.selectList(bookOrder);
	}

	@Override
	public int total(BookOrder bookOrder) {
		return dao.total(bookOrder);
	}
}