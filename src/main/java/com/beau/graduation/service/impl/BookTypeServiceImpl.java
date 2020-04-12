package com.beau.graduation.service.impl;

import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.AddCommodityTypeReqDto;
import com.beau.graduation.basic.reqdto.DelCommodityTypeReqDto;
import com.beau.graduation.basic.reqdto.GetCommodityTypeReqDto;
import com.beau.graduation.basic.resdto.AddCommodityTypeResDto;
import com.beau.graduation.basic.resdto.DelCommodityTypeResDto;
import com.beau.graduation.basic.resdto.GetCommodityTypeResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.BookDao;
import com.beau.graduation.dao.BookTypeDao;
import com.beau.graduation.model.Book;
import com.beau.graduation.model.BookType;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.service.BookService;
import com.beau.graduation.service.BookTypeService;
import com.beau.graduation.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
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

	@Autowired
	private BookService bookService;

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
	public int total(BookType bookType) {
		return dao.total(bookType);
	}

	/**
	 * 获取书籍标签列表
	 * @method: getCommodityTypePage
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.GetCommodityTypeResDto
	 */
	@Override
	public GetCommodityTypeResDto getCommodityTypePage(GetCommodityTypeReqDto reqDto) {
		GetCommodityTypeResDto resDto = new GetCommodityTypeResDto();
		Integer pageNo = reqDto.getPageNo();
		Integer pageSize = reqDto.getPageSize();

		BookType entity = new BookType();
		if (StringUtils.isNotEmpty(reqDto.getTypeName())) {
			entity.setName(reqDto.getTypeName());
		}
		entity.setParentId(reqDto.getParentId());
		int total = dao.total(entity);

		List<BookType> bookTypes = dao.selectPage(entity, PageUtil.getBeginAndSize(pageNo, pageSize));
		Page<BookType> page = new Page<>(total, bookTypes);
		resDto.setPage(page);
		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}

	/**
	 * 添加商品标签
	 * @method: addCommodityType
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.AddCommodityTypeResDto
	 */
	@Override
	public AddCommodityTypeResDto addCommodityType(AddCommodityTypeReqDto reqDto) {
		AddCommodityTypeResDto resDto = new AddCommodityTypeResDto();

		BookType entity = new BookType();
		if (StringUtils.isNotEmpty(reqDto.getTitle())) {
			entity.setName(reqDto.getTitle());
		}
		if (StringUtils.isNotEmpty(reqDto.getTitle())) {
			entity.setRemark(reqDto.getRemark());
		}
		entity.setParentId(reqDto.getParentId());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());

		int insert = dao.insert(entity);
		if (insert > 0) {
			resDto.setCode(ResultCode.success.getCode());
			resDto.setMsg("添加标签成功");
		} else {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("添加标签失败");
		}
		return resDto;
	}

	/**
	 * 删除标签
	 * @param reqDto
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DelCommodityTypeResDto delCommodityType(DelCommodityTypeReqDto reqDto) {
		DelCommodityTypeResDto resDto = new DelCommodityTypeResDto();

		BookType entity = new BookType();
		entity.setId(reqDto.getTypeId());
		// 删除书籍类型记录
		dao.delete(entity);

		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}
}