package com.beau.graduation.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.common.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @classname: CheckOrderInterceptor.java
 * @author: Beau
 * @create: 2020/04/25 20:27
 * @version: 1.0.0
 **/
@Component
@Aspect
public class CheckOrderInterceptor {

    @Pointcut("@annotation(com.beau.graduation.annotation.CheckOrder)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object checkOrderTime(ProceedingJoinPoint joinPoint) throws Throwable {
        ApiResult<Object> res = new ApiResult<>();
        Object[] args = joinPoint.getArgs();
        JSONObject jsonObject =  JSONObject.parseObject(JSONObject.toJSONString(args[0]));
        String orderId = jsonObject.get("orderId").toString();
        if (StringUtils.isEmpty(orderId)) {
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("订单id不能为空");
            return JSON.toJSONString(res);
        }

        return joinPoint.proceed();
    }

}
