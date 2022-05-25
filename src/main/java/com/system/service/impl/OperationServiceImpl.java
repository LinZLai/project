package com.system.service.impl;

import com.system.common.lang.Result;
import com.system.entity.Operation;
import com.system.mapper.OperationMapper;
import com.system.service.OperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements OperationService {

    @Resource
    private OperationMapper operationMapper;

    @Override
    public Result getOperation() {
        return Result.success(operationMapper.selectList(null));
    }
}
