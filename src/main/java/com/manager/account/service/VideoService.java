package com.manager.account.service;

import com.manager.account.config.JwtTokenUtil;
import com.manager.account.dao.VideoDAO;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.dto.VideoDTO;
import com.manager.account.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    VideoDAO videoDAO;

    public BaseResponseDTO getAllVideo() throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setData(videoDAO.getAllVideo());
        return response;
    }

    public BaseResponseDTO deleteVideoById(String username, String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        videoDAO.deleteById(video_id);
        response.setData(videoDAO.getVideoByUsername(username));
        return response;
    }

    public BaseResponseDTO getVideoById(String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setData(videoDAO.getVideoById(video_id));
        return response;
    }

    public BaseResponseDTO getVideoByUsername(String username) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setData(videoDAO.getVideoByUsername(username));
        return response;
    }

    public BaseResponseDTO createVideo(String username, VideoDTO videoDTO) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Video video = new Video();
        video.setVideo_id(UUID.randomUUID().toString());
        video.setUsername(username);
        video.setLink_video(videoDTO.getLink_video());
        video.setContent(videoDTO.getContent());
        video.setAge_rating(videoDTO.getAge_rating());
        video.setDateUploaded(System.currentTimeMillis());

        videoDAO.save(video);
        response.setData(videoDAO.getVideoByUsername(video.getUsername()));
        return response;
    }

    public BaseResponseDTO increaseViewsVideo(String videoId) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Video video = videoDAO.getVideoById(videoId);
        video.setNum_views(video.getNum_views() + 1);

        videoDAO.save(video);
        return response;
    }
}
