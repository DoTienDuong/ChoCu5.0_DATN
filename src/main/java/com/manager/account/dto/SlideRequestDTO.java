package com.manager.account.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SlideRequestDTO {

    private long id;

    @NotBlank(message = "Mô tả danh mục không được để trống")
    private String description;

    @NotBlank(message = "Hình ảnh là cần thiết")
    private String image;
}
