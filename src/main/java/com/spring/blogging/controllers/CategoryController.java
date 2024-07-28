package com.spring.blogging.controllers;

import com.spring.blogging.entities.Category;
import com.spring.blogging.payloads.ApiResponse;
import com.spring.blogging.payloads.CategoryDTO;
import com.spring.blogging.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //CREATE
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO catDTO)
    {
        CategoryDTO createCategory = this.categoryService.createCategory(catDTO);
        return new ResponseEntity<CategoryDTO>(createCategory , HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Integer catId)
    {
        CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO , catId);
        return new ResponseEntity<CategoryDTO>(updateCategory , HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully!", true), HttpStatus.OK);
    }

    //GET
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId) {
        CategoryDTO getCatDto  = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDTO>(getCatDto , HttpStatus.OK);
    }

    //GET ALL
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<CategoryDTO> getAllCatDto  = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDTO>>(getAllCatDto , HttpStatus.OK);
//        return ResponseEntity.ok(getAllCatDto);
    }

}
