package com.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.dto.ChangeState;
import com.system.common.dto.SearchDto;
import com.system.common.lang.Result;
import com.system.entity.Allad;
import com.system.entity.Operation;
import com.system.entity.UserInfo;
import com.system.mapper.AlladMapper;
import com.system.mapper.OperationMapper;
import com.system.mapper.UserInfoMapper;
import com.system.service.AlladService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
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
public class AlladServiceImpl extends ServiceImpl<AlladMapper, Allad> implements AlladService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private OperationMapper operationMapper;

    @Resource
    private AlladMapper alladMapper;

    @Value("${filePath}")
    private String filePath;


    @Override
    public Result getAD(Integer id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }
        Allad one = alladMapper.selectOne(new QueryWrapper<Allad>().eq("id", userInfo.getId()));
        return Result.success(MapUtil.builder()
                .put("id", id)
                .put("name", userInfo.getName())
                .put("phone", userInfo.getPhone())
                .put("email", userInfo.getEmail())
                .put("AD", one)
                .build());
    }

    @Override
    public Result changeState(ChangeState changeState) {
        Allad temp = alladMapper.selectById(changeState.getId());
        if (temp == null) {
            return Result.fail("广告不存在");
        }
        temp.setState(changeState.getState());
        Operation operation = new Operation();
        operation.setOperate("修改id为：" + changeState.getId() + "的广告状态");
        operation.setOperateTime(LocalDateTime.now());
        alladMapper.updateById(temp);
        operationMapper.insert(operation);
        return Result.success("修改成功");
    }

    @Override
    public Result delete(Integer id) {
        Allad temp = alladMapper.selectById(id);
        if (temp == null) {
            return Result.fail("广告不存在");
        }
        alladMapper.deleteById(id);
        Operation operation = new Operation();
        operation.setOperate("删除id为：" + id + "的广告");
        operation.setOperateTime(LocalDateTime.now());
        operationMapper.insert(operation);
        return Result.success("删除成功");
    }

    @Override
    public Result delBatch(Integer[] ids) {
        alladMapper.deleteBatchIds(Arrays.asList(ids));
        Operation operation = new Operation();
        operation.setOperate("删除id为：" + Arrays.toString(ids) + "的广告");
        operation.setOperateTime(LocalDateTime.now());
        operationMapper.insert(operation);
        return Result.success("删除成功");
    }

    @Override
    public Result search(SearchDto searchDto) {
        if (searchDto.getId() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("id", searchDto.getId())));
        } else if (searchDto.getOwnerId() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("ownerId", searchDto.getOwnerId())));
        } else if (searchDto.getADtheme() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("ADtheme", searchDto.getADtheme())));
        } else if (searchDto.getId() != null && searchDto.getOwnerId() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("id", searchDto.getId()).like("ownerId", searchDto.getOwnerId())));
        } else if (searchDto.getId() != null && searchDto.getADtheme() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("id", searchDto.getId()).like("ADtheme", searchDto.getADtheme())));
        } else if (searchDto.getOwnerId() != null && searchDto.getADtheme() != null) {
            return Result.success(alladMapper.selectList(new QueryWrapper<Allad>().like("ownerId", searchDto.getOwnerId()).like("ADtheme", searchDto.getADtheme())));
        }
        return Result.fail("查询失败");
    }

    @Override
    public Result getAll(Integer currentPage) {
        Page<Allad> page = new Page<>(currentPage, 6);
        Page<Allad> alladPage = alladMapper.selectPage(page, new QueryWrapper<Allad>().orderByAsc("id"));
        return Result.success(alladPage);
    }

    @Override
    public Result userGetAll(Integer currentPage, Integer id) {
        Page<Allad> page = new Page<>(currentPage, 7);
        Page<Allad> alladPage = alladMapper.selectPage(page, new QueryWrapper<Allad>().eq("owner_ID", id));
        return Result.success(alladPage);
    }

    @Override
    public void getImg(HttpServletResponse response, String flag) {
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath + "img\\");
        String img = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(img)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(img, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + "/img/" + img);
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
