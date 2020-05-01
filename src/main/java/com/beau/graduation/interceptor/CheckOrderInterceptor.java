package com.beau.graduation.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beau.graduation.Enum.ConfirmStatusEnum;
import com.beau.graduation.Enum.ResultCode;
import com.beau.graduation.common.ApiResult;
import com.beau.graduation.model.dto.OrderDto;
import com.beau.graduation.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 校验订单时间是否过期
 * @classname: CheckOrderInterceptor.java
 * @author: Beau
 * @create: 2020/04/25 20:27
 * @version: 1.0.0
 **/
@Component
@Aspect
public class CheckOrderInterceptor {

    @Autowired
    private OrderService orderService;

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
            return res;
        }
        // 校验订单是否过期
        OrderDto orderDto = orderService.selectByOrderId(orderId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDto.getCreateTime());
        calendar.add(Calendar.MINUTE, 30);
        if (calendar.getTime().before(new Date())) {
            // 订单已过期,关闭订单
            orderDto.setConfirmStatus(ConfirmStatusEnum.closed.getCode());
            orderDto.setUpdateTime(new Date());
            orderService.update(orderDto);
            res.setCode(ResultCode.failed.getCode());
            res.setMsg("订单已过期");
            return res;
        }
        return joinPoint.proceed();
    }

}
