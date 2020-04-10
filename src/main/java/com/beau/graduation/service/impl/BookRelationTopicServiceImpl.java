package com.beau.graduation.service.impl;

import com.beau.graduation.dao.BookRelationTopicDao;
import com.beau.graduation.model.BookRelationTopic;
import com.beau.graduation.service.BookRelationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层实现类
 * BookRelationTopicServiceImpl
 * @author beau
 * @date 2020/04/09
 */
@Service
public class BookRelationTopicServiceImpl implements BookRelationTopicService {

    @Autowired
	BookRelationTopicDao dao;

    @Override
    public int insert(BookRelationTopic bookRelationTopic) {
        return dao.insert(bookRelationTopic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<BookRelationTopic> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(BookRelationTopic bookRelationTopic) {
    	return dao.update(bookRelationTopic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(BookRelationTopic bookRelationTopic) {
    	return dao.delete(bookRelationTopic);
    }

    @Override
    public int batchDelete(List<BookRelationTopic> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookRelationTopic selectByObj(BookRelationTopic bookRelationTopic) {
		return dao.selectByObj(bookRelationTopic);
	}

	@Override
	public List<BookRelationTopic> selectList(BookRelationTopic bookRelationTopic) {
		return dao.selectList(bookRelationTopic);
	}

	@Override
	public int total(BookRelationTopic bookRelationTopic) {
		return dao.total(bookRelationTopic);
	}
}