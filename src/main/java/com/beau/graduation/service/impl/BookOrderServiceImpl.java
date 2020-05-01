package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.GetBoughtBookPageReqDto;
import com.beau.graduation.basic.reqdto.GetCommentPageReqDto;
import com.beau.graduation.basic.resdto.GetBoughtBookPageResDto;
import com.beau.graduation.basic.resdto.GetCommentPageResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookOrderDao;
import com.beau.graduation.model.BookOrder;
import com.beau.graduation.model.dto.BookOrderDto;
import com.beau.graduation.service.BookOrderService;
import com.beau.graduation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int batchInsert(List<BookOrderDto> list) {
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
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 获取已购买的图书列表
     * @param reqDto
     * @return
     */
    @Override
    public GetBoughtBookPageResDto getBoughtBookPage(GetBoughtBookPageReqDto reqDto) {
        GetBoughtBookPageResDto resDto = new GetBoughtBookPageResDto();

        BookOrderDto bookOrderDto = new BookOrderDto();
        bookOrderDto.setUserId(reqDto.getUserId());
        int total = dao.getBoughtBookTotal(bookOrderDto);
        List<BookOrderDto> bookOrderDtoList = dao.getBoughtBookPage(bookOrderDto, PageUtil.getBeginAndSize(reqDto.getPageNo(), reqDto.getPageSize()));
        Page<BookOrderDto> page = new Page<>(total, bookOrderDtoList);

        resDto.setPage(page);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("获取已购买的图书列表成功");
        return resDto;
    }

    /**
     * 获取待评价/已评价列表
     * @param reqDto
     * @return
     */
    @Override
    public GetCommentPageResDto getCommentPage(GetCommentPageReqDto reqDto) {
        GetCommentPageResDto resDto = new GetCommentPageResDto();

        BookOrderDto entity = new BookOrderDto();
        entity.setUserId(reqDto.getUserId());
        entity.setIfComment(reqDto.getIfComment());
        int total = dao.getBoughtBookTotal(entity);
        List<BookOrderDto> boughtBookPage = dao.getBoughtBookPage(entity, PageUtil.getBeginAndSize(reqDto.getPageNo(), reqDto.getPageSize()));
        Page<BookOrderDto> page = new Page<>(total, boughtBookPage);

        resDto.setBookOrderDtoPage(page);
        resDto.setCode(ResultCode.success.getCode());
        resDto.setMsg("获取待评价/已评价列表");
        return resDto;
    }
}