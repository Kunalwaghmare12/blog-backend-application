package com.kunal.blogbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    @NotBlank(message = "Required field title")
    private String title;
    @NotBlank(message = "Required field description")
    private String description;

}
