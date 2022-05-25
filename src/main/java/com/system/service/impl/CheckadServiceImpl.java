package com.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.lang.Result;
import com.system.entity.Allad;
import com.system.entity.Checkad;
import com.system.entity.Operation;
import com.system.mapper.AlladMapper;
import com.system.mapper.CheckadMapper;
import com.system.mapper.OperationMapper;
import com.system.service.CheckadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@Service
public class CheckadServiceImpl extends ServiceImpl<CheckadMapper, Checkad> implements CheckadService {

    @Resource
    private AlladMapper alladMapper;

    @Resource
    private CheckadMapper checkadMapper;

    @Resource
    private OperationMapper operationMapper;

    @Override
    public Result pass(Checkad checkDto) {
        Allad temp = new Allad();
        temp.setId(checkDto.getId());
        temp.setOwnerId(checkDto.getOwnerID());
        temp.setADtheme(checkDto.getTheme());
        temp.setImg(checkDto.getImg());
        temp.setDate(Timestamp.valueOf(LocalDateTime.now()));
        temp.setContent(checkDto.getContent());
        temp.setState("正常");
        alladMapper.insert(temp);
        checkadMapper.deleteById(checkDto.getId());
        Operation operation = new Operation();
        operation.setOperate("审核通过id为：" + checkDto.getId() + "的广告");
        operation.setOperateTime(LocalDateTime.now());
        operationMapper.insert(operation);
        return Result.success("审核通过");
    }

    @Override
    public Result getAll(Integer currentPage) {
        Page<Checkad> page = new Page<>(currentPage, 4);
        Page<Checkad> alladPage = checkadMapper.selectPage(page, new QueryWrapper<Checkad>().orderByAsc("id"));
        return Result.success(alladPage);
    }
}
