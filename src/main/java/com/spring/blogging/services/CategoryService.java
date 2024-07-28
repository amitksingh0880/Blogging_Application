package com.spring.blogging.services;

import com.spring.blogging.payloads.CategoryDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    //CREATE
     CategoryDTO createCategory(CategoryDTO categoryDTO);
    //UPDATE
    CategoryDTO updateCategory(CategoryDTO categoryDTO , Integer categoryId);
    //GET
     CategoryDTO getCategory(Integer categoryId);
    //GET ALL
    List<CategoryDTO> getAllCategory();
    //DELETE
    void deleteCategory(Integer categoryId);
}
