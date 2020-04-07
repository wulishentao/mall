package com.beau.graduation.interceptor;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.utils.CookieUtil;
import com.beau.graduation.utils.LoginUtil;
import com.beau.graduation.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @classname: LoginInterceptor.java
 * @author: Beau
 * @create: 2020/03/11 17:54
 * @version: 1.0.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final String API_URI = "/api";

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private LoginUtil loginUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ApiResult<Object> res = new ApiResult<>();
        String requestURI = request.getRequestURI();
        String userToken = CookieUtil.getCookieValue(request, "user_token");
        String adminToken = CookieUtil.getCookieValue(request, "admin_token");
        if (StringUtils.isEmpty(userToken) && StringUtils.isEmpty(adminToken)) {
            res.setCode(ResultCode.login_required.getCode());
            res.setMsg("您还未登录,请登录");
            write(request, response, JSON.toJSONString(res));
            return false;
        }
        try {
            String token = UuidUtil.getUuid();
            // 若请求路径为"/api",则判断是否有管理员登录
            if (requestURI.contains(API_URI)) {
                PartnerInfo pi = loginUtil.getUser(adminToken);
                if (pi != null) {
                    // 清空之前的登录信息
                    loginUtil.removeUser(adminToken);
                    CookieUtil.removeCookie(response, "admin_token", null);
                    // 重新存储登录信息
                    loginUtil.setUser(token, pi, 15L, TimeUnit.DAYS);
                    CookieUtil.addCookie(response, "admin_token", token, null, 3600 * 24 * 15);
                    return true;
                }
            } else {
                // 请求路径为"/openApi",则判断是否有用户登录
                PartnerInfo pi = loginUtil.getUser(userToken);
                if (pi != null) {
                    // 清空之前的登录信息
                    loginUtil.removeUser(userToken);
                    CookieUtil.removeCookie(response, "user_token", null);
                    // 重新存储登录信息
                    loginUtil.setUser(token, pi, 15L, TimeUnit.DAYS);
                    CookieUtil.addCookie(response, "user_token", token, null, 3600 * 24 * 15);
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("preHandler error: ", e);
        }
        res.setCode(ResultCode.login_required.getCode());
        res.setMsg("您还未登录,请登录");
        write(request,response,JSON.toJSONString(res));
        return false;
    }

    /**
     * 通过response返回错误信息给前端
     *
     * @param request 请求
     * @param response 响应
     * @param content 响应内容
     */
    private void write(HttpServletRequest request, HttpServletResponse response, String content)
            throws IOException {

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(content);
    }
}
