package com.beau.graduation.service.impl;

import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookCommentDao;
import com.beau.graduation.model.BookComment;
import com.beau.graduation.service.BookCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * BookCommentServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class BookCommentServiceImpl implements BookCommentService {

    @Autowired
	BookCommentDao dao;

    @Override
    public int insert(BookComment bookComment) {
        return dao.insert(bookComment);
    }

    @Override
    public int batchInsert(List<BookComment> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookComment bookComment) {
    	return dao.update(bookComment);
    }

    @Override
    public int delete(BookComment bookComment) {
    	return dao.delete(bookComment);
    }

    @Override
    public int batchDelete(List<BookComment> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookComment selectByObj(BookComment bookComment) {
		return dao.selectByObj(bookComment);
	}

	@Override
	public List<BookComment> selectList(BookComment bookComment) {
		return dao.selectList(bookComment);
	}

	@Override
	public Page<BookComment> selectPage(BookComment bookComment, Integer offset, Integer pageSize) {
		Page<BookComment> pageList = new Page<>();

		int total = this.total(bookComment);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<BookComment> list = dao.selectPage(bookComment, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(BookComment bookComment) {
		return dao.total(bookComment);
	}
}