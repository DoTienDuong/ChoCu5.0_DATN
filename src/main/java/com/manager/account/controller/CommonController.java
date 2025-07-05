package com.manager.account.controller;

import com.manager.account.business.CategoryServiceBusiness;
import com.manager.account.dto.*;
import com.manager.account.entity.Users;
import com.manager.account.service.*;
import com.manager.account.constant.ResponseCode;
import com.manager.account.dto.token.TokenDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/common")
@CrossOrigin
@Slf4j
public class CommonController extends BaseController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    CommentService commentService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = loginService.execute(request);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = loginService.getInfo(user.getData().getEmail());
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    public ResponseEntity<?> getAvatarById(@RequestParam("username") String username)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = userService.getAvatarUser(username);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/check-email", method = RequestMethod.GET)
    public ResponseEntity<?> checkEmail(@RequestParam("email") String email) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();

        try {
            response = loginService.getUserByEmail(email);
            response.setCode("0");
        } catch (Exception e) {
            response.setCode("1");
            response.setMessage("Bạn có thể sử dụng địa chỉ email này!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/check-username", method = RequestMethod.GET)
    public ResponseEntity<?> checkUsername(@RequestParam("username") String username) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();

        try {
            response = loginService.getUserByEmail(username);
            response.setCode("0");
        } catch (Exception e) {
            response.setCode("1");
            response.setMessage("Bạn có thể sử dụng username này!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = loginService.register(request);
        } catch (Exception e) {
            response.setCode("MG999");
            response.setMessage("Có lỗi xảy ra trong quá trình xử lý");
            log.error("Error:" + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update token cho user")
    @PostMapping("/updateDevice")
    public ResponseEntity<?> updateDevice(@RequestBody UpdateUserDeviceDTO request, @RequestHeader("Authorization") String token) {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user = null;
        try {
            user = convertToken(token);
            if (user != null) {
                response = userService.save(user, request);
            } else {
                response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User invalid!");
            }

        } catch (Exception e) {
            response.setCode(ResponseCode.UNKNOW);
            response.setMessage("Có lỗi xảy ra trong quá trình xử lý");
            log.error("Error:" + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Change password for user")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequestDTO requestDTO, @RequestHeader("Authorization") String token) {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            if (user != null) {
                response = loginService.changePassword(requestDTO);
            } else {
                response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User invalid!");
            }

        } catch (Exception e) {
            response.setCode(ResponseCode.UNKNOW);
            response.setMessage("Có lỗi xảy ra trong quá trình xử lý");
            log.error("Error:" + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Reset password for Admin")
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequestDTO requestDTO, @RequestHeader("Authorization") String token) {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            if (user != null) {
                response = loginService.resetPassword(requestDTO);
            } else {
                response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User invalid!");
            }

        } catch (Exception e) {
            response.setCode(ResponseCode.UNKNOW);
            response.setMessage("Có lỗi xảy ra trong quá trình xử lý");
            log.error("Error:" + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @RequestBody @Valid ProfileDTO dto)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            HashMap<String, Object> map = new HashMap<>();

            response = userService.updateProfile(user.getData().getEmail(), dto);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.UPDATE_ERROR, "Upload avatar failed");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/delete-video", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVideo(@RequestHeader("Authorization") String token, @RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            TokenDTO user = convertToken(token);
            response = videoService.deleteVideoById(user.getSub(), video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.UPDATE_ERROR, "Upload avatar failed");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public ResponseEntity<?> getVideoById(@RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = videoService.getVideoById(video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/increase-view", method = RequestMethod.GET)
    public ResponseEntity<?> increaseViews(@RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = videoService.increaseViewsVideo(video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/all-video", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVideo()
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = videoService.getAllVideo();
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/videos", method = RequestMethod.GET)
    public ResponseEntity<?> getMyVideo(@RequestHeader("Authorization") String token)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);

            response = videoService.getVideoByUsername(user.getSub());
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/check-favorite", method = RequestMethod.GET)
    public ResponseEntity<?> checkFavorite(@RequestHeader("Authorization") String token, @RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = favoriteService.checkFavorite(user.getSub(), video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/count-favorite", method = RequestMethod.GET)
    public ResponseEntity<?> countFavorite(@RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = favoriteService.getTotalFavorite(video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/count-comment", method = RequestMethod.GET)
    public ResponseEntity<?> countComment(@RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = commentService.getTotalCommentsByVideo(video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity<?> getAllComments(@RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = commentService.getCommentsByVideo(video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/post-video", method = RequestMethod.POST)
    public ResponseEntity<?> postVideo(@RequestHeader("Authorization") String token, @RequestBody @Valid VideoDTO videoDTO)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = videoService.createVideo(user.getSub(), videoDTO);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/post-comment", method = RequestMethod.POST)
    public ResponseEntity<?> postComment(@RequestHeader("Authorization") String token, @RequestBody @Valid CommentDTO commentDTO)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = commentService.addComment(user.getSub(), commentDTO.getVideo_id(), commentDTO.getContent());
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public ResponseEntity<?> handleFavoriteVideo(@RequestHeader("Authorization") String token, @RequestParam("video_id") String video_id)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        TokenDTO user;
        try {
            user = convertToken(token);
            response = favoriteService.handleFavorite(user.getSub(), video_id);
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.VIDEO_GET_ERROR, "Get my video error!");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<?> adminGetAllUser(@RequestHeader("Authorization") String token)
            throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        try {
            response = loginService.getUsers();
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }

    // Vô hiệu hoá user
    @RequestMapping(value = "/admin/users/{username}/deactivate", method = RequestMethod.PATCH)
    public ResponseEntity<?> adminDeactivateUser(
            @PathVariable String username,
            @RequestBody(required = false) DeactivateUserRequestDTO request
    ) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        Users user = (Users) loginService.getUserByUsername(username).getData();

        try {
            String reason = "";
            if (request != null) {
                reason = request.getReason();
            }
            response = userService.updateProfile(user.getEmail(), new ProfileDTO(reason, "DISABLED"));
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }

    // Vô hiệu hoá user
    @RequestMapping(value = "/admin/users/{username}/activate", method = RequestMethod.PATCH)
    public ResponseEntity<?> adminActivateUser(
            @PathVariable String username,
            @RequestBody(required = false) DeactivateUserRequestDTO request
    ) throws Exception {
        BaseResponseDTO response = new BaseResponseDTO();
        Users user = (Users) loginService.getUserByUsername(username).getData();

        try {
            String reason = "";
            if (request != null) {
                reason = request.getReason();
            }
            response = userService.updateProfile(user.getEmail(), new ProfileDTO(reason, "VERIFIED"));
        } catch (Exception e) {
            response = new BaseResponseDTO(ResponseCode.USER_INVALID, "User or password invalid");
        }
        return ResponseEntity.ok(response);
    }
}
