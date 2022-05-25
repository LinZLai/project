package com.system.controller;


import com.system.common.dto.LoginDto;
import com.system.common.lang.Result;
import com.system.entity.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 默认账号密码：admin / 12345678
     */
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        return userService.login(loginDto, response);
    }

    // 退出
    @GetMapping("/logout")
    public Result logout() {
        return userService.logout();
    }

    @PostMapping("/register")
    public Result register(@RequestBody LoginDto user) {
        return userService.register(user);
    }

    @PostMapping("/change")
    public Result change(@RequestBody User user) {
        return userService.change(user);
    }

    @PostMapping("/changepwd")
    public Result changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("id") Integer id, @RequestParam("username") String username, MultipartFile file) throws IOException {
        return userService.upload(id, username, file);
    }

    @GetMapping("/avatar/{flag}")
    public void getFiles(HttpServletResponse response, @PathVariable String flag) {
        userService.getFiles(response, flag);
    }


    @GetMapping("/license/{flag}")
    public void getLicense(HttpServletResponse response, @PathVariable String flag) {
        userService.getLicense(response, flag);
    }
}
