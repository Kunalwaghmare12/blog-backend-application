package com.kunal.blogbackend.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "content is required")
    private String content;
    @NotEmpty(message = "Image is required")
    private String imageName="default.png";
    private Date addedDate;

    private long userId;
    private long categoryId;

}
