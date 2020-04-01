package com.beau.graduation.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.beau.graduation.basic.reqdto.AddCartReqDto;
import com.beau.graduation.basic.resdto.AddCartResDto;
import com.beau.graduation.common.PageList;
import com.beau.graduation.dao.BookDao;
import com.beau.graduation.model.Book;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.ShoppingCart;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.service.BookService;
import com.beau.graduation.utils.CookieUtil;
import com.beau.graduation.utils.LoginUtil;
import com.beau.graduation.utils.RedisUtil;
import com.beau.graduation.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务层实现类
 * BookServiceImpl
 * @author beau
 * @date 2020/03/31
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao dao;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private LoginUtil loginUtil;

    @Override
    public int insert(Book book) {
        return dao.insert(book);
    }

    @Override
    public int batchInsert(List<Book> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(Book book) {
    	return dao.update(book);
    }

    @Override
    public int delete(Book book) {
    	return dao.delete(book);
    }

    @Override
    public int batchDelete(List<Book> list) {
        return dao.batchDelete(list);
    }

	@Override
	public BookDto selectByObj(Book book) {
		return dao.selectByObj(book);
	}

	@Override
	public BookDto selectById(Long bookId) {
		return dao.selectById(bookId);
	}

	@Override
	public List<Book> selectList(Book book) {
		return dao.selectList(book);
	}

	@Override
	public PageList<Book> selectPage(Book book, Integer offset, Integer pageSize) {
		PageList<Book> pageList = new PageList<>();

		int total = this.total(book);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<Book> list = dao.selectPage(book, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(Book book) {
		return dao.total(book);
	}

	@Override
	public AddCartResDto addCart(AddCartReqDto reqDto, HttpServletRequest request) {
		ShoppingCart cart = new ShoppingCart();
		// 先根据bookId查出图书所有信息
		Long bookId = reqDto.getBookId();
		BookDto bookDto = selectById(bookId);
		bookDto.setAmount(reqDto.getAmount());

		// 先查询用户是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		PartnerInfo pi = loginUtil.getUser(userToken);

		if (pi != null) {
			String key = "cart_" + pi.getId();
			// 登录状态下
			cart = redisUtil.get(key, ShoppingCart.class);
			List<BookDto> collect = cart.getBookDtoList().stream().filter(dto -> {
				if (bookId.equals(dto.getId())) {
					dto.setAmount(dto.getAmount() + bookDto.getAmount());

				}
				return true;
			}).collect(Collectors.toList());
		}
		cart.getBookDtoList().add(bookDto);

		// 将购物车实体存入redis
		String cartUuid = UuidUtil.getUuid();
		redisUtil.set(cartUuid,JSON.toJSONString(cart));
		return null;
	}
}