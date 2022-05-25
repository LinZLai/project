package com.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.lang.Result;
import com.system.entity.UserOper;
import com.system.mapper.UserOperMapper;
import com.system.service.UserOperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-15
 */
@Service
public class UserOperServiceImpl extends ServiceImpl<UserOperMapper, UserOper> implements UserOperService {
    @Resource
    private UserOperMapper userOperMapper;

    @Override
    public Result getUserOper(Integer id) {
        List<UserOper> operation = userOperMapper.selectList(new QueryWrapper<UserOper>().eq("owner_ID", id));
        return Result.success(operation);
    }
}
