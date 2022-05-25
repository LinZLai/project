package com.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.dto.ApplyAD;
import com.system.common.lang.Result;
import com.system.entity.*;
import com.system.mapper.*;
import com.system.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static cn.hutool.core.thread.ThreadUtil.sleep;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-08
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private CheckadMapper checkadMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private OperationMapper operationMapper;

    @Value("${filePath}")
    private String filePath;

    @Value("${server.port}")
    private String port;


    @Override
    public Result reject(Apply apply) {
        applyMapper.insert(apply);
        checkadMapper.deleteById(apply.getId());
        Operation operation = new Operation();
        operation.setOperate("id为：" + apply.getId() + "的广告审核不通过");
        operation.setOperateTime(LocalDateTime.now());
        operationMapper.insert(operation);
        return Result.success("操作成功");
    }

    @Override
    public Result imgUpload(Integer id, MultipartFile file) throws IOException {
        //        获取原文件名称
        String fileName = file.getOriginalFilename();
//        给文件加前缀
        assert fileName != null;
        String newFileName = IdUtil.fastSimpleUUID() + fileName.substring(fileName.lastIndexOf("."));
//        获取上传路径
        String fileFullPath = filePath + "img\\" + newFileName;
//        把文件写入到指定路径
        FileUtil.writeBytes(file.getBytes(), fileFullPath);
        String img = "http://localhost:" + port + "/img/" + newFileName;
        Apply apply = applyMapper.selectById(id);
        apply.setImg(img);
        applyMapper.updateById(apply);
        return Result.success("上传成功！");
    }

    @Override
    public Result advertising(ApplyAD apply) {
        UserInfo userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("id", apply.getOwnerID()));
        userInfo.setBalance(userInfo.getBalance() - apply.getPrice());
        if (userInfo.getBalance() < 0) {
            return Result.fail("余额不足！");
        }
        userInfoMapper.updateById(userInfo);
        Apply temp = new Apply();
        temp.setOwnerID(apply.getOwnerID());
        temp.setTheme(apply.getTheme());
        temp.setContent(apply.getContent());
        applyMapper.insert(temp);
        return Result.success("申请成功", MapUtil.builder().put("id", temp.getId()).build());
    }
}
