package com.manager.account.service;

import com.manager.account.dao.CommentDAO;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    public BaseResponseDTO getCommentsByVideo(String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setData(commentDAO.getCommentsByVideo(video_id));
        return response;
    }

    public BaseResponseDTO addComment(String username, String video_id, String content) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();

        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setVideo_id(video_id);
        comment.setContent(content);
        comment.setComment_id(video_id + username + System.currentTimeMillis());
        comment.setDate_comment(System.currentTimeMillis());

        commentDAO.save(comment);

        response.setData(commentDAO.getCommentsByVideo(video_id));
        return response;
    }

    public BaseResponseDTO getTotalCommentsByVideo(String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("total", commentDAO.getCommentsByVideo(video_id).size());
        response.setData(map);
        return response;
    }

}
