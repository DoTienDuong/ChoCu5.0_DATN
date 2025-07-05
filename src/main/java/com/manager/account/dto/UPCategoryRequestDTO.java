package com.manager.account.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UPCategoryRequestDTO {

    @NotNull(message = "Mã danh mục là cần thiết")
    private long id;

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    @NotBlank(message = "Mô tả danh mục không được để trống")
    private String description;

    private String image;

    @SerializedName("parent_id")
    private Long parent_id; // nullable
}
