package com.manager.account.service;

import com.manager.account.dao.CategoryDAO;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.CategoryRequestDTO;
import com.manager.account.dto.UPCategoryRequestDTO;
import com.manager.account.entity.Category;
import com.manager.account.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public BaseResponseDTO createCategory(@Valid CategoryRequestDTO categoryRequestDTO) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Category category = new Category();

        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setImage(categoryRequestDTO.getImage());
        if (categoryRequestDTO.getParent_id() != null) {
            Category parent = new Category();
            parent.setId(Integer.parseInt(categoryRequestDTO.getParent_id().toString()));
            category.setParent(parent);
        }

        categoryDAO.save(category);
        return response;
    }

    public BaseResponseDTO updateCategory(@Valid UPCategoryRequestDTO categoryRequestDTO) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Category category = new Category();

        category.setId((int) categoryRequestDTO.getId());
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setImage(categoryRequestDTO.getImage());
        if (categoryRequestDTO.getParent_id() != null) {
            Category parent = new Category();
            parent.setId(Integer.parseInt(categoryRequestDTO.getParent_id().toString()));
            category.setParent(parent);
        }

        categoryDAO.save(category);
        return response;
    }

    public BaseResponseDTO getCategories() {
        BaseResponseDTO response = new BaseResponseDTO();
        List<Category> categories = categoryDAO.findByParentIsNull();
        response.setData(categories);
        return response;
    }

    public BaseResponseDTO deleteCategory(@Valid Integer categoryId) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        categoryDAO.deleteById(categoryId);
        return response;
    }
}
