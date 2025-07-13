package com.manager.account.service;

import com.manager.account.dao.SlideDAO;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.SlideRequestDTO;
import com.manager.account.dto.SlideStatsResponse;
import com.manager.account.entity.Slide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class SlideService {

    @Autowired
    SlideDAO slideDAO;

    public BaseResponseDTO createSlide(@Valid SlideRequestDTO requestDTO) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Slide slide = new Slide();

        slide.setDescription(requestDTO.getDescription());
        slide.setImage(requestDTO.getImage());

        slideDAO.save(slide);
        return response;
    }

//    public BaseResponseDTO updateCategory(@Valid UPCategoryRequestDTO categoryRequestDTO) throws UsernameNotFoundException {
//        BaseResponseDTO response = new BaseResponseDTO();
//
//        Category category = new Category();
//
//        category.setId((int) categoryRequestDTO.getId());
//        category.setName(categoryRequestDTO.getName());
//        category.setDescription(categoryRequestDTO.getDescription());
//        category.setImage(categoryRequestDTO.getImage());
//        if (categoryRequestDTO.getParent_id() != null) {
//            Category parent = new Category();
//            parent.setId(Integer.parseInt(categoryRequestDTO.getParent_id().toString()));
//            category.setParent(parent);
//        }
//
//        categoryDAO.save(category);
//        return response;
//    }

    public BaseResponseDTO getSlides() {
        BaseResponseDTO response = new BaseResponseDTO();
        List<Slide> slides = slideDAO.findAll();
        response.setData(slides);
        return response;
    }

    public BaseResponseDTO deleteSlide(@Valid Integer slideId) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        slideDAO.deleteById(slideId);
        return response;
    }

    public BaseResponseDTO getSlideStats() {
        BaseResponseDTO response = new BaseResponseDTO();
        long count = slideDAO.count();
        var latest = slideDAO.findLatestCreatedAt();
        response.setData(new SlideStatsResponse(count, latest));
        return response;
    }
}
