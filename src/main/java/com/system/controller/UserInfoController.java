package com.system.controller;


import com.system.common.dto.UserInfoDetail;
import com.system.common.lang.Result;
import com.system.entity.UserInfo;
import com.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@RestController
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/userInfo")
    public Result userInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.userInfo(userInfo);
    }

    @PostMapping("/license")
    public Result upload(@RequestParam("id") Integer id, @RequestParam("username") String username, MultipartFile file) throws IOException {
        return userInfoService.upload(id, username, file);
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam("id") Integer id) {
        return userInfoService.getUserInfo(id);
    }

    @RequestMapping("/UserInfoDetail")
    public Result updateUserInfo(@RequestBody UserInfoDetail userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }
}
