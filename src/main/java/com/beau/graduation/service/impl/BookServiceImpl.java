package com.beau.graduation.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.basic.reqdto.AddCartReqDto;
import com.beau.graduation.basic.reqdto.SyncCartReqDto;
import com.beau.graduation.basic.resdto.AddCartResDto;
import com.beau.graduation.basic.resdto.SyncCartResDto;
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
import javax.servlet.http.HttpServletResponse;
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
	public AddCartResDto addCart(AddCartReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart cart = new ShoppingCart();
		AddCartResDto resDto = new AddCartResDto();
		// 先根据bookId查出图书所有信息
		Long bookId = reqDto.getBookId();
		BookDto bookDto = selectById(bookId);
		bookDto.setAmount(reqDto.getAmount());
		bookDto.setExist(false);

		// 先查询用户是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		if (!StringUtils.isEmpty(userToken)) {
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				String key = "cart_" + pi.getId();
				// 登录状态下
				cart = redisUtil.get(key, ShoppingCart.class);
				addToFormer(bookDto, cart);
				// 将合并的购物车信息重新存入redis中
				redisUtil.set(key, JSON.toJSONString(cart));
				resDto.setCode(ResultCode.SUCCESS.getCode());
				return resDto;
			}
		}

		// 未登录状态下
		String cart_uuid = CookieUtil.getCookieValue(request, "cart_uuid");
		cart.getBookDtoList().add(bookDto);
		if (!StringUtils.isEmpty(cart_uuid)) {
			if (redisUtil.hasKey(cart_uuid)) {
				// redis里面存储了cart_uuid
				cart = redisUtil.get(cart_uuid, ShoppingCart.class);
				addToFormer(bookDto, cart);
			}
			// 将合并的购物车信息重新存入redis中
			redisUtil.set(cart_uuid, JSON.toJSONString(cart));
			resDto.setCode(ResultCode.SUCCESS.getCode());
			return resDto;
		}

		// 重新生成cart_uuid
		// 将购物车实体存入redis
		String cartUuid = UuidUtil.getUuid();
		redisUtil.set(cartUuid,JSON.toJSONString(cart));
		// 将cart_uuid存入cookie中
		CookieUtil.addCookie(response, "cart_uuid", cartUuid, null);
		resDto.setCode(ResultCode.SUCCESS.getCode());
		return resDto;
	}

	/**
	 * 获取购物车所有商品
	 *
	 * @method: getShoppingCart
	 * @param: [syncCartReqDto]
	 * @return: com.beau.graduation.basic.resdto.SyncCartResDto
	 */
	@Override
	public SyncCartResDto getShoppingCart(SyncCartReqDto syncCartReqDto, HttpServletRequest request, HttpServletResponse response) {
		SyncCartResDto resDto = new SyncCartResDto();
		// 判断是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		if (!StringUtils.isEmpty(userToken)) {
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				// 已登录
				String key = "cart_" + pi.getId();
				ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
				resDto.setShoppingCart(shoppingCart);
				resDto.setCode(ResultCode.SUCCESS.getCode());
				return resDto;
			}
		}

		// 未登录状态
		String cartUuid = CookieUtil.getCookieValue(request, "cart_uuid");
		if (!StringUtils.isEmpty(cartUuid)) {
			if (redisUtil.hasKey(cartUuid)) {
				ShoppingCart shoppingCart = redisUtil.get(cartUuid, ShoppingCart.class);
				resDto.setCode(ResultCode.SUCCESS.getCode());
				resDto.setShoppingCart(shoppingCart);
			} else {
				resDto.setCode(ResultCode.FAILED.getCode());
				resDto.setMsg("购物车异常");
			}
			return resDto;
		}
		String uuid = UuidUtil.getUuid();
		ShoppingCart cart = new ShoppingCart();
		redisUtil.set(uuid, JSON.toJSONString(cart));
		CookieUtil.addCookie(response, "cart_uuid", cartUuid, null);
		resDto.setShoppingCart(cart);
		resDto.setCode(ResultCode.SUCCESS.getCode());
		return resDto;
	}

	/**
	 * 同步购物车
	 * @method: syncShoppingCart
	 * @param: [syncCartReqDto]
	 * @return: com.beau.graduation.basic.resdto.SyncCartResDto
	 */
	@Override
	public SyncCartResDto syncShoppingCart(SyncCartReqDto syncCartReqDto, HttpServletRequest request) {
		SyncCartResDto resDto = new SyncCartResDto();

		// 校验是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		if (!StringUtils.isEmpty(userToken)) {
			// 已登录
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				String key = "cart_" + pi.getId();
				syncCartWithKey(key, syncCartReqDto);
				resDto.setCode(ResultCode.SUCCESS.getCode());
				resDto.setMsg("同步成功");
				return resDto;
			}
		}

		// 未登录
		String cartUuid = CookieUtil.getCookieValue(request, "cart_uuid");
		if (!StringUtils.isEmpty(cartUuid)) {
			ShoppingCart cart = redisUtil.get(cartUuid, ShoppingCart.class);
			if (cart != null) {
				syncCartWithKey(cartUuid, syncCartReqDto);
				resDto.setCode(ResultCode.SUCCESS.getCode());
				resDto.setMsg("同步成功");
				return resDto;
			}
		}
		resDto.setCode(ResultCode.FAILED.getCode());
		resDto.setMsg("同步失败");
		return resDto;
	}

	private void syncCartWithKey(String key, SyncCartReqDto syncCartReqDto) {
		ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
		if ("1".equals(syncCartReqDto.getDelFlag())) {
			List<BookDto> collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
				if (bookDto.getId().equals(syncCartReqDto.getBookId())) {
					bookDto = null;
				}
				return true;
			}).collect(Collectors.toList());
			shoppingCart.setBookDtoList(collect);
			redisUtil.set(key, JSON.toJSONString(shoppingCart));

		} else {
			List<BookDto> collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
				if (bookDto.getId().equals(syncCartReqDto.getBookId())) {
					bookDto.setAmount(syncCartReqDto.getAmount());
				}
				return true;
			}).collect(Collectors.toList());
			shoppingCart.setBookDtoList(collect);
			redisUtil.set(key, JSON.toJSONString(shoppingCart));
		}
	}

	/**
	 * 将商品添加进已有的购物车中
	 * @param bookDto
	 * @param cart
	 */
	private void addToFormer(BookDto bookDto, ShoppingCart cart) {
		List<BookDto> collect = cart.getBookDtoList().stream().filter(dto -> {
			if (bookDto.getId().equals(dto.getId())) {
				dto.setAmount(dto.getAmount() + bookDto.getAmount());
				bookDto.setExist(true);
			}
			return true;
		}).collect(Collectors.toList());
		if (!bookDto.getExist()) {
			collect.add(bookDto);
		}
		cart.setBookDtoList(collect);
	}
}