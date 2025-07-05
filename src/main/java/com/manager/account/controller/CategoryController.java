package com.manager.account.controller;

import com.manager.account.constant.ResponseCode;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.CategoryRequestDTO;
import com.manager.account.dto.UPCategoryRequestDTO;
import com.manager.account.dto.token.TokenDTO;
import com.manager.account.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CategoryRequestDTO categoryRequestDTO
    ) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = categoryService.createCategory(categoryRequestDTO);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.ADD_CATEGORY_ERROR, "Add category error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateCategory(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid UPCategoryRequestDTO categoryRequestDTO
    ) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = categoryService.updateCategory(categoryRequestDTO);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.ADD_CATEGORY_ERROR, "Add category error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories() throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = categoryService.getCategories();
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.GET_CATEGORY_ERROR, "Get category error!");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        BaseResponseDTO response;
        try {
            response = categoryService.deleteCategory(id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.DELETE_CATEGORY_ERROR, "Delete category error!");
        }
        return ResponseEntity.ok(response);
    }

}
