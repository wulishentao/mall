package com.beau.graduation.service.impl;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.Enum.LoginTypeEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.Enum.StatusEnum;
import com.beau.graduation.basic.reqdto.LoginReqDto;
import com.beau.graduation.basic.reqdto.RegisterReqDto;
import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.basic.resdto.RegisterResDto;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.common.PageList;
import com.beau.graduation.dao.PartnerInfoDao;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.ShoppingCart;
import com.beau.graduation.model.dto.BookDto;
import com.beau.graduation.service.PartnerInfoService;
import com.beau.graduation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
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
	private static final Long storeTime = 15L;

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
			resDto.setCode(ResultCode.FAILED.getCode());
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
			resDto.setCode(ResultCode.FAILED.getCode());
			resDto.setMsg("注册失败");
			return resDto;
		}
		resDto.setCode(ResultCode.SUCCESS.getCode());
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
		if (pi.getAccountType().equals(LoginTypeEnum.ADMIN.getCode())) {
			partnerInfo = dao.selectAdmin(pi);
		} else {
			partnerInfo = dao.selectByObj(pi);
		}

		if (partnerInfo == null) {
			resDto.setCode(ResultCode.FAILED.getCode());
			resDto.setMsg("用户名或密码错误");
			return resDto;
		}
		if (StatusEnum.disable.getCode().equals(partnerInfo.getStatus())) {
			resDto.setCode(ResultCode.FAILED.getCode());
			resDto.setMsg("该用户账号当前被禁用");
			return resDto;
		}

		resDto.setId(String.valueOf(partnerInfo.getId()));
		resDto.setAccountName(partnerInfo.getAccountName());
		resDto.setAccountType(partnerInfo.getAccountType());
		String uuToken = UuidUtil.getUuid();
		// 将登录信息存入redis缓存
		loginUtil.setUser(uuToken, resDto, storeTime, TimeUnit.DAYS);
		// 将token存入cookie中
		if (LoginTypeEnum.ADMIN.getCode().equals(resDto.getAccountType())) {
			CookieUtil.addCookie(response, "admin_token", uuToken, null, 3600 * 24 * 15);
		} else {
			storageWithId(request, partnerInfo.getId());
			CookieUtil.addCookie(response, "user_token", uuToken, null, 3600 * 24 * 15);
		}

		resDto.setCode(ResultCode.SUCCESS.getCode());
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
		ShoppingCart cart = new ShoppingCart();
		if (redisUtil.hasKey(key)) {
			cart = redisUtil.get(key, ShoppingCart.class);
		}
		cart.setUserId(userId);
		if (!StringUtils.isEmpty(cart_uuid)) {
			ShoppingCart shoppingCart = redisUtil.get(cart_uuid, ShoppingCart.class);
			if (shoppingCart != null) {
				List<BookDto> bookDtoList = shoppingCart.getBookDtoList();
				List<BookDto> cartList = cart.getBookDtoList();
				for (BookDto bookDto : bookDtoList) {
					for (BookDto dto : cartList) {
						if (dto.getId().equals(bookDto.getId())) {
							dto.setAmount(dto.getAmount() + bookDto.getAmount());
							bookDto = null;
						}
					}
				}
				cartList.addAll(bookDtoList);
				cart.setBookDtoList(cartList);
			}
			// 将cart_uuid所存储的购物车信息清空
			redisUtil.delete(cart_uuid);
		}
		// 将合并过的购物车信息重新存入redis中
		redisUtil.set(key, JSON.toJSONString(cart));
	}

	@Override
	public List<PartnerInfo> selectList(PartnerInfo partnerInfo) {
		return dao.selectList(partnerInfo);
	}

	@Override
	public PageList<PartnerInfo> selectPage(PartnerInfo partnerInfo, Integer offset, Integer pageSize) {
		PageList<PartnerInfo> pageList = new PageList<>();

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
}