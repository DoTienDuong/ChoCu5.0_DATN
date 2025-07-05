package com.manager.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentDTO {
    @ApiModelProperty(notes = "content", example = "path", required = true)
    private String content;

    @ApiModelProperty(notes = "video_id", example = "path", required = true)
    private String video_id;
}
