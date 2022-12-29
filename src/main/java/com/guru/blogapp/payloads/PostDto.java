package com.guru.blogapp.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date publishedDate;
    private CategoryDto category;
    private UserDto user;

}
