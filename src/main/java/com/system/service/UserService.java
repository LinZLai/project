package com.system.service;

import com.system.common.dto.LoginDto;
import com.system.common.lang.Result;
import com.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface UserService extends IService<User> {

    Result login(LoginDto loginDto, HttpServletResponse response);

    Result logout();

    Result register(LoginDto user);

    Result change(User user);

    Result changePassword(User user);

    Result upload(Integer id, String username, MultipartFile file) throws IOException;

    void getFiles(HttpServletResponse response, String flag);

    void getLicense(HttpServletResponse response, String flag);
}
