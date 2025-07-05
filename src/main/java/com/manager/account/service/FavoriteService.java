package com.manager.account.service;

import com.manager.account.dao.FavoriteDAO;
import com.manager.account.dto.BaseResponseDTO;
import com.manager.account.entity.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FavoriteService {

    @Autowired
    FavoriteDAO favoriteDAO;

    public BaseResponseDTO checkFavorite(String username, String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setData(favoriteDAO.checkFavorite(video_id, username));
        return response;
    }

    public BaseResponseDTO getTotalFavorite(String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("total", favoriteDAO.getTotalFavoriteVideo(video_id).size());
        response.setData(map);
        return response;
    }

    public BaseResponseDTO handleFavorite(String username, String video_id) throws UsernameNotFoundException {
        BaseResponseDTO response = new BaseResponseDTO();
        Favorite result;
        Favorite favorite = favoriteDAO.checkFavorite(video_id, username);
        if (favorite == null) {
            result = new Favorite(video_id, username);
            favoriteDAO.save(result);
        } else {
            result = null;
            favoriteDAO.delete(favorite);
        }
        response.setData(result);
        return response;
    }
}
