package com.beau.graduation.model.dto;

import com.beau.graduation.model.BookComment;
import lombok.Data;

/**
 * @classname: BookCommentDto.java
 * @author: Beau
 * @create: 2020/05/02 10:13
 * @version: 1.0.0
 **/
@Data
public class BookCommentDto extends BookComment {
    private String accountName;
}
