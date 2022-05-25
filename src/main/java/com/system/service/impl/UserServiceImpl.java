package com.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.dto.LoginDto;
import com.system.common.lang.Result;
import com.system.entity.Role;
import com.system.entity.User;
import com.system.entity.UserInfo;
import com.system.mapper.RoleMapper;
import com.system.mapper.UserInfoMapper;
import com.system.mapper.UserMapper;
import com.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    JwtUtils jwtUtils;

    @Resource
    RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Value("${filePath}")
    private String filePath;

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost:";

    @Override
    public Result login(LoginDto loginDto, HttpServletResponse response) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        Role role = roleMapper.selectById(user.getRoleId());
        UserInfo userInfo = userInfoMapper.selectById(user.getId());
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avater", user.getAvater())
                .put("nickname", user.getNickname())
                .put("router", role.getRole())
                .put("phone", userInfo.getPhone())
                .put("email", userInfo.getEmail())
                .put("balance", userInfo.getBalance())
                .map()
        );
    }

    @Override
    public Result logout() {
        return Result.success(null);
    }

    @Override
    public Result register(LoginDto user) {
        User temp = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (temp != null) {
            return Result.fail("用户名已存在！");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(SecureUtil.md5(user.getPassword()));
        user1.setAvater("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user1.setNickname("昵称");
        user1.setRoleId(2);
        userMapper.insert(user1);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user1.getId());
        userInfo.setUsername(user.getUsername());
        userInfoMapper.insert(userInfo);
        return Result.success("注册成功！");
    }

    @Override
    public Result change(User user) {
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (one != null) {
            userMapper.updateById(user);
            return Result.success("修改成功！");
        }
        return Result.fail("用户不存在！");
    }

    @Override
    public Result changePassword(User user) {
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (one != null) {
            String newPassword = SecureUtil.md5(user.getPassword());
            one.setPassword(newPassword);
            userMapper.updateById(one);
            return Result.success("修改成功！");
        }
        return Result.fail("用户不存在！");
    }

    @Override
    public Result upload(Integer id, String username, MultipartFile file) throws IOException {
//        获取原文件名称
        String fileName = file.getOriginalFilename();
//        给文件加前缀
        assert fileName != null;
        String newFileName = IdUtil.fastSimpleUUID() + fileName.substring(fileName.lastIndexOf("."));
//        获取上传路径
        String fileFullPath = filePath + "avatar\\" + newFileName;
//        把文件写入到指定路径
        FileUtil.writeBytes(file.getBytes(), fileFullPath);
        String avatar = ip + port + "/avatar/" + newFileName;
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("id", id)).setAvater(avatar);
        userMapper.updateById(user);
        return Result.success("上传成功！");
    }

    @Override
    public void getFiles(HttpServletResponse response, String flag) {
        OutputStream os = null;
        List<String> fileNames = FileUtil.listFileNames(filePath + "avatar/");
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + "/avatar/" + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getLicense(HttpServletResponse response, String flag) {
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath + "license\\");
        String license = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(license)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(license, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + "/license/" + license);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
