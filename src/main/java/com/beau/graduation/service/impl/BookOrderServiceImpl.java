package com.beau.graduation.service.impl;

import com.beau.graduation.common.PageList;
import com.beau.graduation.dao.BookOrderDao;
import com.beau.graduation.model.BookOrder;
import com.beau.graduation.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * BookOrderServiceImpl
 * @author beau
 * @date 2020/03/28
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
	public BookOrder selectByObj(BookOrder bookOrder) {
		return dao.selectByObj(bookOrder);
	}

	@Override
	public List<BookOrder> selectList(BookOrder bookOrder) {
		return dao.selectList(bookOrder);
	}

	@Override
	public PageList<BookOrder> selectPage(BookOrder bookOrder, Integer offset, Integer pageSize) {
		PageList<BookOrder> pageList = new PageList<>();

		int total = this.total(bookOrder);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<BookOrder> list = dao.selectPage(bookOrder, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(BookOrder bookOrder) {
		return dao.total(bookOrder);
	}
}