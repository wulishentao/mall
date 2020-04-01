package com.beau.graduation.service.impl;

import com.beau.graduation.common.PageList;
import com.beau.graduation.dao.BookRelationTypeDao;
import com.beau.graduation.model.BookRelationType;
import com.beau.graduation.service.BookRelationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * BookRelationTypeServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class BookRelationTypeServiceImpl implements BookRelationTypeService {

    @Autowired
	BookRelationTypeDao dao;

    @Override
    public int insert(BookRelationType bookRelationType) {
        return dao.insert(bookRelationType);
    }

    @Override
    public int batchInsert(List<BookRelationType> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookRelationType bookRelationType) {
    	return dao.update(bookRelationType);
    }

    @Override
    public int delete(BookRelationType bookRelationType) {
    	return dao.delete(bookRelationType);
    }

    @Override
    public int batchDelete(List<BookRelationType> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookRelationType selectByObj(BookRelationType bookRelationType) {
		return dao.selectByObj(bookRelationType);
	}

	@Override
	public List<BookRelationType> selectList(BookRelationType bookRelationType) {
		return dao.selectList(bookRelationType);
	}

	@Override
	public PageList<BookRelationType> selectPage(BookRelationType bookRelationType, Integer offset, Integer pageSize) {
		PageList<BookRelationType> pageList = new PageList<>();

		int total = this.total(bookRelationType);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<BookRelationType> list = dao.selectPage(bookRelationType, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(BookRelationType bookRelationType) {
		return dao.total(bookRelationType);
	}
}