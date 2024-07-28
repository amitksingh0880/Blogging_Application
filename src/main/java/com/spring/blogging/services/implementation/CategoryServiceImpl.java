package com.spring.blogging.services.implementation;

import com.spring.blogging.entities.Category;
import com.spring.blogging.exceptions.ResourceNotFoundException;
import com.spring.blogging.payloads.CategoryDTO;
import com.spring.blogging.repositories.CategoryRepo;
import com.spring.blogging.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category cat = this.modelMapper.map(categoryDTO , Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat , CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category oldCat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        oldCat.setCategoryTitle(categoryDTO.getCategoryTitle());
        oldCat.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCat = this.categoryRepo.save(oldCat);
        return this.modelMapper.map(updatedCat,CategoryDTO.class);
    }
    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(cat, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
       List<CategoryDTO> catdtos = categories.stream().map((category)-> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return catdtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", categoryId));
       this.categoryRepo.delete(cat);
    }
}
