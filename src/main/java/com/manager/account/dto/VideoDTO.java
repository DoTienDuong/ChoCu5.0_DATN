package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class VideoDTO {
    @ApiModelProperty(notes = "content", example = "path", required = true)
    private String content;

    @ApiModelProperty(notes = "link_video", example = "path", required = true)
    private String link_video;

    @ApiModelProperty(notes = "age_rating", example = "path", required = true)
    private String age_rating;
}
