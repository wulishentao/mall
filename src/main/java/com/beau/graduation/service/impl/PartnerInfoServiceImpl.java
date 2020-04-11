package com.beau.graduation.service.impl;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.Enum.LoginTypeEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.Enum.StatusEnum;
import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.common.Page;
import com.beau.graduation.dao.PartnerInfoDao;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.ShoppingCart;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.service.PartnerInfoService;
import com.beau.graduation.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 业务层实现类
 * PartnerInfoServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class PartnerInfoServiceImpl implements PartnerInfoService {

	private static final Long STORE_TIME = 15L;

	private static final Logger logger = LoggerFactory.getLogger(PartnerInfoServiceImpl.class);

    @Autowired
	PartnerInfoDao dao;

	@Autowired
	private LoginUtil loginUtil;

	@Autowired
	private RedisUtil redisUtil;

    @Override
    public void insert(PartnerInfo partnerInfo) {
        dao.insert(partnerInfo);
    }

	@Override
	public RegisterResDto insert(RegisterReqDto reqDto) {
		RegisterResDto resDto = new RegisterResDto();
		PartnerInfo pi = new PartnerInfo();
		pi.setPhone(reqDto.getPhone());
		PartnerInfo partnerInfo = dao.selectByObj(pi);
		if (partnerInfo != null) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("此电话号码已被注册");
			return resDto;
		}

		pi.setAccountName(reqDto.getAccountName());
		pi.setPassword(PasswordUtil.Md5Hex(reqDto.getPassword()));
		pi.setAccountType(reqDto.getAccountType());
		pi.setCreateTime(new Date());
		pi.setUpdateTime(new Date());
		pi.setGender(reqDto.getGender());

		int total = dao.insert(pi);
		if (total == 0) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("注册失败");
			return resDto;
		}
		resDto.setCode(ResultCode.success.getCode());
		resDto.setMsg("注册成功");
		return resDto;
	}

	@Override
    public int batchInsert(List<PartnerInfo> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(PartnerInfo partnerInfo) {
    	return dao.update(partnerInfo);
    }

    @Override
    public int delete(PartnerInfo partnerInfo) {
    	return dao.delete(partnerInfo);
    }

    @Override
    public int batchDelete(List<PartnerInfo> list) {
        return dao.batchDelete(list);
    }

	@Override
	public LoginResDto selectByObj(LoginReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
		PartnerInfo pi = new PartnerInfo();
		LoginResDto resDto = new LoginResDto();

		pi.setPhone(reqDto.getPhone());
		pi.setAccountType(reqDto.getAccountType());
		pi.setPassword(PasswordUtil.Md5Hex(reqDto.getPassword()));
		PartnerInfo partnerInfo;
		if (pi.getAccountType().equals(LoginTypeEnum.admin.getCode())) {
			partnerInfo = dao.selectAdmin(pi);
		} else {
			partnerInfo = dao.selectByObj(pi);
		}

		if (partnerInfo == null) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("用户名或密码错误");
			return resDto;
		}
		if (StatusEnum.disable.getCode().equals(partnerInfo.getStatus())) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("该用户账号当前被禁用");
			return resDto;
		}

		resDto.setId(String.valueOf(partnerInfo.getId()));
		resDto.setAccountName(partnerInfo.getAccountName());
		resDto.setAccountType(partnerInfo.getAccountType());
		String uuToken = UuidUtil.getUuid();
		// 将登录信息存入redis缓存
		loginUtil.setUser(uuToken, resDto, STORE_TIME, TimeUnit.DAYS);
		// 将token存入cookie中
		if (LoginTypeEnum.admin.getCode().equals(resDto.getAccountType())) {
			CookieUtil.addCookie(response, "admin_token", uuToken, null, 3600 * 24 * 15);
		} else {
			storageWithId(request, partnerInfo.getId());
			CookieUtil.addCookie(response, "user_token", uuToken, null, 3600 * 24 * 15);
		}

		resDto.setCode(ResultCode.success.getCode());
		resDto.setMsg("登录成功");
		return resDto;
	}

	/**
	 * 将之前未登录状态下添加的购物车商品合并到登录下的购物车中
	 * @param request
	 */
	private void storageWithId(HttpServletRequest request, Long userId) {
		String cart_uuid = CookieUtil.getCookieValue(request, "cart_uuid");
		String key = "cart_" + userId;
		ShoppingCart shoppingCart = redisUtil.get(cart_uuid, ShoppingCart.class);
		ShoppingCart cart = redisUtil.get(key, ShoppingCart.class);

		if (cart == null) {
			// 若登录用户购物车为空,则将未登录的购物车直接添加进登录购物车
			cart = shoppingCart;
		} else {
			if (StringUtils.isNotEmpty(cart_uuid)) {
				if (shoppingCart != null) {
					List<BookDto> bookDtoList = shoppingCart.getBookDtoList();
					List<BookDto> cartList = cart.getBookDtoList();
					ArrayList<BookDto> dtoList = new ArrayList<>();
					for (BookDto bookDto : cartList) {
						for (BookDto dto : bookDtoList) {
							if (dto.getId().equals(bookDto.getId())) {
								bookDto.setAmount(dto.getAmount() + bookDto.getAmount());
								dtoList.add(dto);
							}
						}
					}
					bookDtoList.removeAll(dtoList);
					cartList.addAll(bookDtoList);
					cart.setBookDtoList(cartList);
				}
			}
		}
		// 将cart_uuid所存储的购物车信息清空
		redisUtil.delete(cart_uuid);

		// 将合并过的购物车信息重新存入redis中
		cart.setUserId(userId);
		redisUtil.set(key, JSON.toJSONString(cart));
	}

	@Override
	public List<PartnerInfo> selectList(PartnerInfo partnerInfo) {
		return dao.selectList(partnerInfo);
	}

	@Override
	public Page<PartnerInfo> selectPage(PartnerInfo partnerInfo, Integer offset, Integer pageSize) {
		Page<PartnerInfo> pageList = new Page<>();

		int total = this.total(partnerInfo);

		int totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<PartnerInfo> list = dao.selectPage(partnerInfo, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(PartnerInfo partnerInfo) {
		return dao.total(partnerInfo);
	}

	@Override
	public LogoutResDto logout(LogoutReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
		LogoutResDto resDto = new LogoutResDto();
		String userRequestUri = "/openApi";
		String requestUri = request.getRequestURI();
		String token;
		String tokenName;

		if (requestUri.contains(userRequestUri)) {
			// 普通用户注销登录
			token = CookieUtil.getCookieValue(request, "user_token");
			tokenName = "user_token";
		} else {
			// 管理员登录注销
			token = CookieUtil.getCookieValue(request, "admin_token");
			tokenName = "admin_token";
		}
		// 从缓存中删除登录信息
		loginUtil.removeUser(token);
		CookieUtil.removeCookie(response, tokenName, null);
		resDto.setCode(ResultCode.success.getCode());
		return resDto;
	}

	/**
	 * 获取注册用户列表
	 * @method: getPartnerPage
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.GetPartnerResDto
	 */
	@Override
	public GetPartnerResDto getPartnerPage(GetPartnerReqDto reqDto) {
		GetPartnerResDto resDto = new GetPartnerResDto();
		PartnerInfo pi = new PartnerInfo();

		if (StringUtils.isNotEmpty(reqDto.getAccountName())) {
			pi.setAccountName(reqDto.getAccountName());
		}
		if (StringUtils.isNotEmpty(reqDto.getAccountStatus())) {
			pi.setStatus(reqDto.getAccountStatus());
		}
		if (StringUtils.isNotEmpty(reqDto.getPhone())) {
			pi.setPhone(reqDto.getPhone());
		}
		// 查出总记录数
		int total = dao.total(pi);
		// 查出满足条件数据
		Integer pageNo = reqDto.getPageNo();
		Integer pageSize = reqDto.getPageSize();
		List<PartnerInfo> partnerPage = dao.getPartnerPage(pi, PageUtil.getBeginAndSize(pageNo, pageSize));
		Page<PartnerInfo> page = new Page<>(total, partnerPage);

		resDto.setCode(ResultCode.success.getCode());
		resDto.setInfoPage(page);
		return resDto;
	}

	/**
	 * 启用/禁用用户
	 * @method: editPartnerStatus
	 * @param: [reqDto]
	 * @return: com.beau.graduation.basic.resdto.EditPartnerStatusResDto
	 */
	@Override
	public EditPartnerStatusResDto editPartnerStatus(EditPartnerStatusReqDto reqDto) throws Exception {
		EditPartnerStatusResDto resDto = new EditPartnerStatusResDto();
		PartnerInfo pi = new PartnerInfo();

		if (reqDto.getUserId() == 0 || reqDto.getUserId() == null) {
			throw new Exception("用户id不能为空");
		}
		pi.setId(reqDto.getUserId());
		PartnerInfo obj = dao.selectByObj(pi);
		if (obj == null) {
			resDto.setCode(ResultCode.failed.getCode());
			resDto.setMsg("该用户不存在");
			return resDto;
		}
		pi.setStatus(reqDto.getStatus());
		pi.setUpdateTime(new Date());
		dao.update(pi);

		resDto.setCode(ResultCode.success.getCode());
		resDto.setMsg("更改状态成功");
		return resDto;
	}
}