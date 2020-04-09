package com.beau.graduation.service.impl;

import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookImageDao;
import com.beau.graduation.model.BookImage;
import com.beau.graduation.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层实现类
 * BookImageServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class BookImageServiceImpl implements BookImageService {

    @Autowired
    BookImageDao dao;

    @Override
	@Transactional(rollbackFor = Exception.class)
    public int insert(BookImage bookImage) {
        return dao.insert(bookImage);
    }

    @Override
	@Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<BookImage> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookImage bookImage) {
    	return dao.update(bookImage);
    }

    @Override
    public int delete(BookImage bookImage) {
    	return dao.delete(bookImage);
    }

    @Override
    public int batchDelete(List<BookImage> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookImage selectByObj(BookImage bookImage) {
		return dao.selectByObj(bookImage);
	}

	@Override
	public List<BookImage> selectList(BookImage bookImage) {
		return dao.selectList(bookImage);
	}

	@Override
	public Page<BookImage> selectPage(BookImage bookImage, Integer offset, Integer pageSize) {
		Page<BookImage> pageList = new Page<>();

		int total = this.total(bookImage);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<BookImage> list = dao.selectPage(bookImage, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(BookImage bookImage) {
		return dao.total(bookImage);
	}
}