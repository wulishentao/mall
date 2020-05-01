package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ConfirmStatusEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.CommentBookReqDto;
import com.beau.graduation.basic.resdto.CommentBookResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookCommentDao;
import com.beau.graduation.dao.BookOrderDao;
import com.beau.graduation.model.BookComment;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.dto.BookOrderDto;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.BookCommentService;
import com.beau.graduation.service.BookOrderService;
import com.beau.graduation.service.OrderService;
import com.beau.graduation.utils.CookieUtil;
import com.beau.graduation.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 业务层实现类
 * BookCommentServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class BookCommentServiceImpl implements BookCommentService {

	private static final String USER_TOKEN = "user_token";

    @Autowired
	BookCommentDao dao;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BookOrderService bookOrderService;

	@Autowired
	private LoginUtil loginUtil;

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

	/**
	 * 评价图书
	 * @param reqDto
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommentBookResDto commentBook(CommentBookReqDto reqDto) {
		CommentBookResDto resDto = new CommentBookResDto();

		// 判断订单是否已结束,进入评价阶段
		OrderDto orderDto = orderService.selectByOrderId(reqDto.getOrderId());
		if (!ConfirmStatusEnum.received.getCode().equals(orderDto.getConfirmStatus())) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("订单还未结束,现在无法评价");
			return resDto;
		}
		BookOrderDto bookOrderDto = new BookOrderDto();
		bookOrderDto.setIfComment("1");
		bookOrderDto.setBookId(reqDto.getBookId());
		bookOrderDto.setOrderId(reqDto.getOrderId());
		bookOrderService.update(bookOrderDto);

		BookComment entity = new BookComment();
		entity.setBookId(reqDto.getBookId());
		entity.setOrderId(orderDto.getOrderId());
		entity.setRemark(reqDto.getRemark());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		dao.insert(entity);

		resDto.setCode(ResultCode.success.getCode());
		resDto.setMsg("图书评价成功");
		return resDto;
	}
}