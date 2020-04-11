package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.RelatedBookReqDto;
import com.beau.graduation.basic.resdto.RelatedBookResDto;
import com.beau.graduation.dao.BookRelationTopicDao;
import com.beau.graduation.model.BookRelationTopic;
import com.beau.graduation.model.Topic;
import com.beau.graduation.service.BookRelationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 专题批量关联图书
     * @param reqDto
     * @return
     */
    @Override
    public RelatedBookResDto batchRelatedBook(RelatedBookReqDto reqDto) {
        RelatedBookResDto resDto = new RelatedBookResDto();

        List<BookRelationTopic> topics = new ArrayList<>();
        Long topicId = reqDto.getTopicId();
        List<Long> bookIds = reqDto.getBookIds();
        if (bookIds.size() == 0) {
            resDto.setCode(ResultCode.failed.getCode());
            resDto.setMsg("书籍列表不能为空");
            return resDto;
        }
        // 更新关联图书
        // 1.先删除该专题下的所有关联图书
        BookRelationTopic bookRelationTopic = new BookRelationTopic();
        bookRelationTopic.setTopicId(topicId);
        dao.delete(bookRelationTopic);
        // 2.批量关联图书
        for (Long bookId : bookIds) {
            BookRelationTopic relationTopic = new BookRelationTopic();
            relationTopic.setBookId(bookId);
            relationTopic.setCreateTime(new Date());
            relationTopic.setTopicId(topicId);
            topics.add(relationTopic);
        }
        dao.batchInsert(topics);
        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }
}