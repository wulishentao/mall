package com.beau.graduation.service.impl;

import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookTypeDao;
import com.beau.graduation.model.BookType;
import com.beau.graduation.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * BookTypeServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
	BookTypeDao dao;

    @Override
    public int insert(BookType bookType) {
        return dao.insert(bookType);
    }

    @Override
    public int batchInsert(List<BookType> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookType bookType) {
    	return dao.update(bookType);
    }

    @Override
    public int delete(BookType bookType) {
    	return dao.delete(bookType);
    }

    @Override
    public int batchDelete(List<BookType> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookType selectByObj(BookType bookType) {
		return dao.selectByObj(bookType);
	}

	@Override
	public List<BookType> selectList(BookType bookType) {
		return dao.selectList(bookType);
	}

	@Override
	public Page<BookType> selectPage(BookType bookType, Integer offset, Integer pageSize) {
		Page<BookType> pageList = new Page<>();

		int total = this.total(bookType);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<BookType> list = dao.selectPage(bookType, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(BookType bookType) {
		return dao.total(bookType);
	}
}