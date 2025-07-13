package com.manager.account.controller;

import com.manager.account.constant.ResponseCode;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.SlideRequestDTO;
import com.manager.account.dto.SlideStatsResponse;
import com.manager.account.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/slide")
public class SlideController extends BaseController {

    @Autowired
    SlideService slideService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSlides() {
        BaseResponseDTO response;
        try {
            response = slideService.getSlides();
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.GET_SLIDE_ERROR, "Get Slides error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addSlide(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid SlideRequestDTO requestDTO
    ) {
        BaseResponseDTO response;
        try {
            convertToken(token);
            response = slideService.createSlide(requestDTO);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.ADD_SLIDE_ERROR, "Add slide error!");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable("id") Integer id) {
        BaseResponseDTO response;
        try {
            response = slideService.deleteSlide(id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.DELETE_SLIDE_ERROR, "Delete slide error!");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getSlideStats() {
        BaseResponseDTO response;
        try {
            response = slideService.getSlideStats();
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.DELETE_SLIDE_ERROR, "Delete slide error!");
        }
        return ResponseEntity.ok(response);
    }
}
