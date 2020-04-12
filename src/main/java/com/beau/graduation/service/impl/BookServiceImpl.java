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
import com.beau.graduation.service.BookRelationTopicService;
import com.beau.graduation.service.BookService;
import com.beau.graduation.utils.*;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 业务层实现类
 * BookServiceImpl
 * @author beau
 * @date 2020/03/31
 */
@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookDao dao;

	@Autowired
	private BookImageService bookImageService;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private LoginUtil loginUtil;

	@Autowired
	private BookRelationTopicService relationTopicService;

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
		return dao.commodityDetailed(book);
	}

	@Override
	public BookDto selectById(Long bookId) {
		return dao.selectById(bookId);
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
				redisUtil.getAndSet(key, JSON.toJSONString(cart));
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
			redisUtil.set(cart_uuid, JSON.toJSONString(cart), 20L, TimeUnit.DAYS);
			resDto.setCode(ResultCode.success.getCode());
			return resDto;
		}

		// 重新生成cart_uuid
		// 将购物车实体存入redis
		String cartUuid = UuidUtil.getUuid();
		redisUtil.set(cartUuid, JSON.toJSONString(cart), 20L, TimeUnit.DAYS);
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
				ShoppingCart shoppingCart = syncCartWithKey(key, syncCartReqDto);
				redisUtil.getAndSet(key, JSON.toJSONString(shoppingCart));
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
				ShoppingCart shoppingCart = syncCartWithKey(cartUuid, syncCartReqDto);
				redisUtil.set(cartUuid, JSON.toJSONString(shoppingCart), 20L, TimeUnit.DAYS);
				resDto.setCode(ResultCode.success.getCode());
				resDto.setMsg("同步成功");
				return resDto;
			}
		}
		resDto.setCode(ResultCode.failed.getCode());
		resDto.setMsg("同步失败");
		return resDto;
	}

	/**
	 * 删除指定购物车商品
	 * @param reqDto
	 * @param request
	 * @return
	 */
	@Override
	public DelCartResDto delShoppingCart(DelCartReqDto reqDto, HttpServletRequest request) {
		DelCartResDto resDto = new DelCartResDto();

		// 校验是否登录
		String userToken = CookieUtil.getCookieValue(request, "user_token");
		if (StringUtils.isNotEmpty(userToken)) {
			// 已登录
			PartnerInfo pi = loginUtil.getUser(userToken);
			if (pi != null) {
				String key = "cart_" + pi.getId();
				ShoppingCart shoppingCart = delCartWithKey(key, reqDto);
				redisUtil.getAndSet(key, JSON.toJSONString(shoppingCart));
				resDto.setCode(ResultCode.success.getCode());
				resDto.setMsg("删除成功");
				return resDto;
			}
		}

		// 未登录
		String cartUuid = CookieUtil.getCookieValue(request, "cart_uuid");
		if (StringUtils.isNotEmpty(cartUuid)) {
			ShoppingCart cart = redisUtil.get(cartUuid, ShoppingCart.class);
			if (cart != null) {
				ShoppingCart shoppingCart = delCartWithKey(cartUuid, reqDto);
				redisUtil.set(cartUuid, JSON.toJSONString(shoppingCart), 20L, TimeUnit.DAYS);
				resDto.setCode(ResultCode.success.getCode());
				resDto.setMsg("删除成功");
				return resDto;
			}
		}
		resDto.setCode(ResultCode.failed.getCode());
		resDto.setMsg("删除失败");
		return resDto;
	}

	/**
	 * 根据key删除redis中的购物车商品
	 * @param key
	 * @param reqDto
	 */
	private ShoppingCart delCartWithKey(String key, DelCartReqDto reqDto) {
		ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
		List<Long> bookIds = reqDto.getBookIds();
		List<BookDto> collect = null;
		for (Long bookId : bookIds) {
			collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
				if (bookDto.getId().equals(bookId)) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
		}
		shoppingCart.setBookDtoList(collect);
		return shoppingCart;
	}

	/**
	 * 根据key同步更新购物车
	 * @method: syncCartWithKey
	 * @param: [key, syncCartReqDto]
	 * @return: void
	 */
	private ShoppingCart syncCartWithKey(String key, SyncCartReqDto syncCartReqDto) {
		ShoppingCart shoppingCart = redisUtil.get(key, ShoppingCart.class);
		List<BookDto> collect = shoppingCart.getBookDtoList().stream().filter(bookDto -> {
			if (bookDto.getId().equals(syncCartReqDto.getBookId())) {
				bookDto.setAmount(syncCartReqDto.getAmount());
			}
			return true;
		}).collect(Collectors.toList());
		shoppingCart.setBookDtoList(collect);
		return shoppingCart;
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
		entity.setCreateTime(new Date());
		storageBookBasic(entity, reqDto);
		dao.insert(entity);

		// 存储关联专题关系
		String topicIds = reqDto.getTopicIds();
		storageBookTopic(topicIds, entity.getId());


		// 将图书封面图片存入指定地址
		List<MultipartFile> files = reqDto.getFiles();
		if (files.size() == 0) {
			throw new Exception("上传图片不能为空");
		}
		storageBookImg(files, entity.getId());


		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}

	private void storageBookTopic(String topicIds, Long bookId) {
		if (StringUtils.isNotEmpty(topicIds)) {
			List<BookRelationTopic> relationTopics = new ArrayList<>();
			String[] split = topicIds.split(",");
			for (String s : split) {
				BookRelationTopic relationTopic = new BookRelationTopic();
				relationTopic.setBookId(bookId);
				relationTopic.setTopicId(Long.valueOf(s));
				relationTopic.setCreateTime(new Date());
				relationTopics.add(relationTopic);
			}
			relationTopicService.batchInsert(relationTopics);
		}
	}

	/**
	 * 批量存储封面图片
	 * @param files
	 * @param bookId
	 * @throws IOException
	 */
	private void storageBookImg(List<MultipartFile> files, Long bookId) throws IOException {
		List<String> filePaths = new ArrayList<>();
		for (MultipartFile file : files) {
			String filePath = FileUploadsUtil.uploadPicture(UploadConfig.getBookPicUploadPath(), file);
			filePaths.add(filePath);
		}

		// 将图书封面图片地址存入数据库
		List<BookImage> bookImages = new ArrayList<>();
		for (String filePath : filePaths) {
			BookImage image = new BookImage();
			image.setBookId(bookId);
			image.setCreateTime(new Date());
			image.setImgUrl(filePath);
			bookImages.add(image);
		}
		bookImageService.batchInsert(bookImages);
	}

	private void storageBookBasic(Book book, AddCommodityReqDto reqDto) {
		book.setUpdateTime(new Date());
		book.setTitle(reqDto.getTitle());
		book.setAuthor(reqDto.getAuthor());
		book.setIntroduction(reqDto.getIntroduction());
		book.setRecommendFlag(reqDto.getRecommendFlg());
		book.setPrice(reqDto.getPrice());
		book.setPublishDate(DateUtil.parseDate(reqDto.getPublishDate()));
		book.setPublisher(reqDto.getPublisher());
		book.setReserve(reqDto.getReserve());
		book.setTypeId(reqDto.getTypeId());
		if (reqDto.getReserve() == 0) {
			book.setSaleStatus(SaleStatusEnum.sold_out.getCode());
		}
		book.setSort(reqDto.getSort());
	}

	/**
	 * 编辑书籍信息
	 * @method: editCommodity
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.EditCommodityResDto
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public EditCommodityResDto editCommodity(EditCommodityReqDto reqDto) throws Exception {
		EditCommodityResDto resDto = new EditCommodityResDto();

		// 更新图书基本信息
		Book entity = new Book();
		entity.setId(reqDto.getBookId());
		storageBookBasic(entity, reqDto);
		dao.update(entity);

		// 更新专题关联关系
		// 1.先删除数据库保存的书籍关联专题关系数据
		BookRelationTopic relationTopic = new BookRelationTopic();
		relationTopic.setBookId(reqDto.getBookId());
		relationTopicService.delete(relationTopic);
		// 2.重新存储书籍关联专题关系
		String topicIds = reqDto.getTopicIds();
		storageBookTopic(topicIds, reqDto.getBookId());

		// 更新书籍封面图片
		// 1.先删除保存书籍封面图片
		BookImage bookImage = new BookImage();
		bookImage.setBookId(reqDto.getBookId());
		List<BookImage> bookImages = bookImageService.selectList(bookImage);
		List<String> imgUrls = bookImages.stream().map(BookImage::getImgUrl).collect(Collectors.toList());
		if (imgUrls.size() > 0) {
			for (String imgUrl : imgUrls) {
				File file = new File(imgUrl);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		// 2.删除数据库中存储的书籍封面地址记录
		bookImageService.delete(bookImage);
		// 3.存储新的图书封面
		List<MultipartFile> files = reqDto.getFiles();
		if (files.size() == 0) {
			throw new Exception("上传图片不能为空");
		}
		storageBookImg(files, reqDto.getBookId());

		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}

	/**
	 * 书籍详情页
	 * @method: commodityDetailed
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.CommodityDetailedResDto
	 */
	@Override
	public CommodityDetailedResDto commodityDetailed(CommodityDetailedReqDto reqDto) throws Exception {
		CommodityDetailedResDto resDto = new CommodityDetailedResDto();

		if (reqDto.getBookId() == 0 || reqDto.getBookId() == null) {
			throw new Exception("书籍id不能为空");
		}
		Book book = new Book();
		book.setId(reqDto.getBookId());
		BookDto bookDto = dao.commodityDetailed(book);

		resDto.setBookDto(bookDto);
		resDto.setCode(ResultCode.success.getCode());
		resDto.setMsg("书籍详情获取成功");
		return resDto;
	}

	/**
	 * 查找出所有类型或父级类型为删除类型的书籍id集合
	 * @param bookDto
	 * @return
	 */
	@Override
	public List<Long> getSuchBookList(BookDto bookDto) {
		List<Long> bookIds = dao.getSuchBookList(bookDto);
		return null;
	}
}