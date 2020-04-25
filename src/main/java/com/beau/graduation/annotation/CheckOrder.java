package com.beau.graduation.annotation;

import java.lang.annotation.*;

/**
 * @classname: CheckOrder.java
 * @author: Beau
 * @create: 2020/04/25 20:36
 * @version: 1.0.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckOrder {
}
