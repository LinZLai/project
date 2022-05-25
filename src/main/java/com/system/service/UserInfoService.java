package com.system.service;

import com.system.common.dto.UserInfoDetail;
import com.system.common.lang.Result;
import com.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface UserInfoService extends IService<UserInfo> {

    Result userInfo(UserInfo userInfo);

    Result upload(Integer id, String username, MultipartFile file) throws IOException;

    Result getUserInfo(Integer id);

    Result updateUserInfo(UserInfoDetail userInfo);
}
