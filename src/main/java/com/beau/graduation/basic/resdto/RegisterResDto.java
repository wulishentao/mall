package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.model.ShoppingCart;

import java.io.Serializable;

/**
 * @classname: RegisterResDto.java
 * @author: Beau
 * @create: 2020/03/30 15:23
 * @version: 1.0.0
 **/
public class RegisterResDto extends CommonResDTO implements Serializable {
    private ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
