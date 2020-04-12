package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.AddTopicReqDto;
import com.beau.graduation.basic.reqdto.DeleteTopicReqDto;
import com.beau.graduation.basic.reqdto.EditTopicReqDto;
import com.beau.graduation.basic.reqdto.GetTopicPageReqDto;
import com.beau.graduation.basic.resdto.AddTopicResDto;
import com.beau.graduation.basic.resdto.DeleteTopicResDto;
import com.beau.graduation.basic.resdto.EditTopicResDto;
import com.beau.graduation.basic.resdto.GetTopicPageResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.config.UploadConfig;
import com.beau.graduation.dao.TopicDao;
import com.beau.graduation.model.Topic;
import com.beau.graduation.service.TopicService;
import com.beau.graduation.utils.DateUtil;
import com.beau.graduation.utils.FileUploadsUtil;
import com.beau.graduation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
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

    /**
     * 后台获取活动专题
     * @param reqDto
     * @return
     */
    @Override
    public GetTopicPageResDto getTopicPage(GetTopicPageReqDto reqDto) {
        GetTopicPageResDto resDto = new GetTopicPageResDto();

        Topic entity = new Topic();
        entity.setTopicTitle(reqDto.getTopicTitle());
        entity.setStatus(reqDto.getStatus());

        int total = dao.total(entity);
        List<Topic> topicPage = dao.getTopicPage(entity, PageUtil.getBeginAndSize(reqDto.getPageNo(), reqDto.getPageSize()));
        Page<Topic> page = new Page<>(total, topicPage);
        resDto.setTopicPage(page);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("获取专题成功");
        return resDto;
    }

    /**
     * 编辑专题活动
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public EditTopicResDto editTopic(EditTopicReqDto reqDto) throws Exception {
        EditTopicResDto resDto = new EditTopicResDto();

        Topic entity = new Topic();
        entity.setId(reqDto.getTopicId());
        Topic topic = dao.selectByObj(entity);
        if (topic == null) {
            throw new Exception("该专题不存在或已被删除");
        }
        topic.setSort(reqDto.getSort());
        topic.setStatus(reqDto.getStatus());
        dao.update(topic);

        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }

    /**
     * 删除专题
     * @param reqDto
     * @return
     */
    @Override
    public DeleteTopicResDto deleteTopic(DeleteTopicReqDto reqDto) {
        DeleteTopicResDto resDto = new DeleteTopicResDto();

        Topic entity = new Topic();
        entity.setId(reqDto.getTopicId());
        dao.delete(entity);

        resDto.setCode(ResultCode.success.getCode());
        return resDto;
    }
}