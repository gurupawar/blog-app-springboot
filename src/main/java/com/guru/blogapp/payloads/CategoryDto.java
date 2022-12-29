package com.guru.blogapp.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    @NotEmpty
    @Size(min = 4, message = "min size of category title is 4")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10, message = "min size of category description is 10")
    private String categoryDescription;
}
