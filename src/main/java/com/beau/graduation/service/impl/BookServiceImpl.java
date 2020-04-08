package com.beau.graduation.service.impl;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.Enum.SaleStatusEnum;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.Page;
import com.beau.graduation.config.UploadConfig;
import com.beau.graduation.dao.BookDao;
import com.beau.graduation.model.*;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.service.BookImageService;
import com.beau.graduation.service.BookRelationTypeService;
import com.beau.graduation.service.BookService;
import com.beau.graduation.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
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
	private BookImageService bookImageService;

	@Autowired
	private BookRelationTypeService relationTypeService;

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
	public Page<Book> selectPage(Book book, Integer offset, Integer pageSize) {
		Page<Book> pageList = new Page<>();

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
		List<BookDto> bookList = new ArrayList<>();
		// 先根据bookId查出图书所有信息
		Long bookId = reqDto.getBookId();
		BookDto bookDto = selectById(bookId);
		bookDto.setAmount(reqDto.getAmount());
		bookDto.setExist(false);

		// 先查询用户是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		if (StringUtils.isNotEmpty(userToken)) {
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				String key = "cart_" + pi.getId();
				// 登录状态下
				cart = redisUtil.get(key, ShoppingCart.class);
				addToFormer(bookDto, cart);
				// 将合并的购物车信息重新存入redis中
				redisUtil.set(key, JSON.toJSONString(cart));
				resDto.setCode(ResultCode.success.getCode());
				return resDto;
			}
		}

		// 未登录状态下
		String cart_uuid = CookieUtil.getCookieValue(request, "cart_uuid");
		bookList.add(bookDto);
		cart.setBookDtoList(bookList);
		if (StringUtils.isNotEmpty(cart_uuid)) {
			if (redisUtil.hasKey(cart_uuid)) {
				// redis里面存储了cart_uuid
				cart = redisUtil.get(cart_uuid, ShoppingCart.class);
				addToFormer(bookDto, cart);
			}
			// 将合并的购物车信息重新存入redis中
			redisUtil.set(cart_uuid, JSON.toJSONString(cart));
			resDto.setCode(ResultCode.success.getCode());
			return resDto;
		}

		// 重新生成cart_uuid
		// 将购物车实体存入redis
		String cartUuid = UuidUtil.getUuid();
		redisUtil.set(cartUuid,JSON.toJSONString(cart));
		// 将cart_uuid存入cookie中
		CookieUtil.addCookie(response, "cart_uuid", cartUuid, null);
		resDto.setCode(ResultCode.success.getCode());
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
		if (StringUtils.isNotEmpty(userToken)) {
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				// 已登录
				String key = "cart_" + pi.getId();
				ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
				resDto.setShoppingCart(shoppingCart);
				resDto.setCode(ResultCode.success.getCode());
				return resDto;
			}
		}

		// 未登录状态
		String cartUuid = CookieUtil.getCookieValue(request, "cart_uuid");
		ShoppingCart cart = new ShoppingCart();
		if (StringUtils.isNotEmpty(cartUuid)) {
			if (redisUtil.hasKey(cartUuid)) {
				cart = redisUtil.get(cartUuid, ShoppingCart.class);
			} else {
				redisUtil.set(cartUuid, JSON.toJSONString(cart));
			}
		} else {
			String uuid = UuidUtil.getUuid();
			cart = new ShoppingCart();
			redisUtil.set(uuid, JSON.toJSONString(cart));
			CookieUtil.addCookie(response, "cart_uuid", cartUuid, null);
		}
		resDto.setShoppingCart(cart);
		resDto.setCode(ResultCode.success.getCode());
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
		if (StringUtils.isNotEmpty(userToken)) {
			// 已登录
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				String key = "cart_" + pi.getId();
				syncCartWithKey(key, syncCartReqDto);
				resDto.setCode(ResultCode.success.getCode());
				resDto.setMsg("同步成功");
				return resDto;
			}
		}

		// 未登录
		String cartUuid = CookieUtil.getCookieValue(request, "cart_uuid");
		if (StringUtils.isNotEmpty(cartUuid)) {
			ShoppingCart cart = redisUtil.get(cartUuid, ShoppingCart.class);
			if (cart != null) {
				syncCartWithKey(cartUuid, syncCartReqDto);
				resDto.setCode(ResultCode.success.getCode());
				resDto.setMsg("同步成功");
				return resDto;
			}
		}
		resDto.setCode(ResultCode.failed.getCode());
		resDto.setMsg("同步失败");
		return resDto;
	}

	private void syncCartWithKey(String key, SyncCartReqDto syncCartReqDto) {
		ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
		if ("1".equals(syncCartReqDto.getDelFlag())) {
			List<BookDto> collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
				if (bookDto.getId().equals(syncCartReqDto.getBookId())) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
			shoppingCart.setBookDtoList(collect);
		} else {
			List<BookDto> collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
				if (bookDto.getId().equals(syncCartReqDto.getBookId())) {
					bookDto.setAmount(syncCartReqDto.getAmount());
				}
				return true;
			}).collect(Collectors.toList());
			shoppingCart.setBookDtoList(collect);
		}
		redisUtil.set(key, JSON.toJSONString(shoppingCart));
	}

	/**
	 * 将商品添加进已有的购物车中
	 * @param bookDto
	 * @param cart
	 */
	private void addToFormer(BookDto bookDto, ShoppingCart cart) {
		if (cart.getBookDtoList() == null) {
			List<BookDto> bookDtoList = new ArrayList<>();
			bookDtoList.add(bookDto);
			cart.setBookDtoList(bookDtoList);
		} else {
			List<BookDto> collect = cart.getBookDtoList().stream().filter(dto -> {
				if (bookDto.getId().equals(dto.getId())) {
					dto.setAmount(dto.getAmount() + bookDto.getAmount());
					if (!dto.getPrice().equals(bookDto.getPrice())) {
						dto.setPrice(bookDto.getPrice());
					}
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

	@Override
	public GetCommodityResDto getCommodityPage(GetCommodityReqDto reqDto) {
		GetCommodityResDto resDto = new GetCommodityResDto();
		BookDto entity = new BookDto();

		if (StringUtils.isNotEmpty(reqDto.getTitle())) {
			entity.setTitle(reqDto.getTitle());
		}
		if (StringUtils.isNotEmpty(reqDto.getBookType())) {
			entity.setBookType(reqDto.getBookType());
		}
		if (StringUtils.isNotEmpty(reqDto.getSaleStatus())) {
			entity.setSaleStatus(reqDto.getSaleStatus());
		}
		if (StringUtils.isNotEmpty(reqDto.getReviewStatus())) {
			entity.setReviewStatus(reqDto.getReviewStatus());
		}

		int total = dao.totalByDto(entity);
		Integer pageNo = reqDto.getPageNo();
		Integer pageSize = reqDto.getPageSize();

		List<BookDto> bookDtoList = dao.getCommodityPage(entity, PageUtil.getBeginAndSize(pageNo, pageSize));
		Page<BookDto> page = new Page<>(total, bookDtoList);
		resDto.setBookDtoPage(page);
		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}

	/**
	 * 添加商品
	 *
	 * @method: addCommodity
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.AddCommodityResDto
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AddCommodityResDto addCommodity(AddCommodityReqDto reqDto) throws Exception {
		AddCommodityResDto resDto = new AddCommodityResDto();

		if (StringUtils.isEmpty(reqDto.getTitle())) {
			throw new Exception("图书名称不能为空");
		}
		if (reqDto.getTypeId() == 0) {
			throw new Exception("图书标签不能为空");
		}
		if (StringUtils.isEmpty(reqDto.getAuthor())) {
			throw new Exception("图书作者不能为空");
		}
		if (StringUtils.isEmpty(reqDto.getPublisher())) {
			throw new Exception("图书出版商不能为空");
		}
		if (StringUtils.isEmpty(reqDto.getPublishDate())) {
			throw new Exception("图书出版日期不能为空");
		}
		// 存储图书基本信息
		Book entity = new Book();
		entity.setTitle(reqDto.getTitle());
		entity.setAuthor(reqDto.getAuthor());
		entity.setIntroduction(reqDto.getIntroduction());
		entity.setRecommendFlag(reqDto.getRecommendFlg());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setPrice(reqDto.getPrice());
		entity.setPublishDate(reqDto.getPublishDate());
		entity.setPublisher(reqDto.getPublisher());
		entity.setReserve(reqDto.getReserve());
		entity.setTypeId(reqDto.getTypeId());
		if (reqDto.getReserve() == 0) {
			entity.setSaleStatus(SaleStatusEnum.sold_out.getCode());
		}
		entity.setSort(reqDto.getSort());
		dao.insert(entity);

		// 将图书封面图片存入指定地址
		MultipartFile file = reqDto.getFile();
		if (file.isEmpty()) {
			throw new Exception("上传图片不能为空");
		}
		String filePath = FileUploadsUtil.uploadPicture(UploadConfig.getUploadPath(), file);

		// 将图书封面图片地址存入数据库
		BookImage image = new BookImage();
		image.setBookId(entity.getId());
		image.setCreateTime(new Date());
		image.setUpdateTime(new Date());
		image.setImgUrl(filePath);
		bookImageService.insert(image);

		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}




}