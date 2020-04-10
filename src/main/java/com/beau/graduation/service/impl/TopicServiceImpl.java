package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.AddTopicReqDto;
import com.beau.graduation.basic.resdto.AddTopicResDto;
import com.beau.graduation.config.UploadConfig;
import com.beau.graduation.dao.TopicDao;
import com.beau.graduation.model.Topic;
import com.beau.graduation.service.TopicService;
import com.beau.graduation.utils.DateUtil;
import com.beau.graduation.utils.FileUploadsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 业务层实现类
 * TopicServiceImpl
 * @author beau
 * @date 2020/04/09
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
	TopicDao dao;

    @Override
    public int insert(Topic topic) {
        return dao.insert(topic);
    }

    @Override
    public int batchInsert(List<Topic> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(Topic topic) {
    	return dao.update(topic);
    }

    @Override
    public int delete(Topic topic) {
    	return dao.delete(topic);
    }

    @Override
    public int batchDelete(List<Topic> list) {
        return dao.batchDelete(list);
    }

	@Override
	public Topic selectByObj(Topic topic) {
		return dao.selectByObj(topic);
	}

	@Override
	public List<Topic> selectList(Topic topic) {
		return dao.selectList(topic);
	}

	@Override
	public int total(Topic topic) {
		return dao.total(topic);
	}

	/**
     * 添加活动专题
	 * @method: addTopic
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.AddTopicResDto
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddTopicResDto addTopic(AddTopicReqDto reqDto) throws IOException {
        AddTopicResDto resDto = new AddTopicResDto();

        Topic entity = new Topic();
        entity.setBeginTime(DateUtil.parseDate(reqDto.getBeginTime()));
        entity.setEndTime(DateUtil.parseDate(reqDto.getEndTime()));
        entity.setTopicTitle(reqDto.getTopicTitle());
        entity.setTopicIntroduce(reqDto.getTopicIntroduce());
        entity.setSort(reqDto.getSort());
        // 保存图片
        String filePath = FileUploadsUtil.uploadPicture(UploadConfig.getTopicPicUploadPath(), reqDto.getTopicImag());
        entity.setTopicImg(filePath);
        dao.insert(entity);

        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }
}