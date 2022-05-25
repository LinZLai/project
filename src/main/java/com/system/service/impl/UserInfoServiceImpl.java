package com.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.dto.UserInfoDetail;
import com.system.common.lang.Result;
import com.system.entity.Operation;
import com.system.entity.User;
import com.system.entity.UserInfo;
import com.system.mapper.OperationMapper;
import com.system.mapper.UserInfoMapper;
import com.system.mapper.UserMapper;
import com.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;


    @Value("${server.port}")
    private String port;

    @Value("${filePath}")
    private String filePath;

    @Override
    public Result userInfo(UserInfo userInfo) {
        UserInfo one = userInfoMapper.selectById(userInfo.getId());
        if (one == null) {
            return Result.fail("用户不存在");
        }
        userInfoMapper.insert(userInfo);
        return Result.success("修改成功");
    }

    @Override
    public Result upload(Integer id, String username, MultipartFile file) throws IOException {
//        获取原文件名称
        String fileName = file.getOriginalFilename();
//        给文件加前缀
        assert fileName != null;
        String newFileName = IdUtil.fastSimpleUUID() + fileName.substring(fileName.lastIndexOf("."));
//        获取上传路径
        String fileFullPath = filePath + "license\\" + newFileName;
//        把文件写入到指定路径
        FileUtil.writeBytes(file.getBytes(), fileFullPath);
        String license = "http://localhost:" + port + "/license/" + newFileName;
        UserInfo setLicense = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("id", id)).setLicense(license);
        userInfoMapper.updateById(setLicense);
        return Result.success("上传成功！");
    }

    @Override
    public Result getUserInfo(Integer id) {
        User user = userMapper.selectById(id);
        UserInfo userInfo = query().eq("id", id).one();
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avater", user.getAvater())
                .put("nickname", user.getNickname())
                .put("phone", userInfo.getPhone())
                .put("email", userInfo.getEmail())
                .put("balance", userInfo.getBalance())
                .map()
        );
    }

    @Override
    public Result updateUserInfo(UserInfoDetail userInfo) {
        UserInfo userInfo1 = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("id", userInfo.getId()).eq("username", userInfo.getUsername()));
        if (userInfo1 == null) {
            return Result.fail("用户不存在");
        }
        userInfo1.setName(userInfo.getName());
        userInfo1.setCorporateName(userInfo.getCorporateName());
        userInfo1.setIDNumber(userInfo.getIDNumber());
        userInfoMapper.updateById(userInfo1);
        return Result.success("修改成功");
    }
}
