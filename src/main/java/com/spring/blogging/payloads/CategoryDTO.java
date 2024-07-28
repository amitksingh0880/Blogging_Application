package com.spring.blogging.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer categoryId;
    @NotBlank
    @Size(min = 4 , message = "Min Size of category title is 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10 , message = "Min size of Category description is 10")
    private String categoryDescription;
}
