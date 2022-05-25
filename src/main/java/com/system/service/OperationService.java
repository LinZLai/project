package com.system.service;

import com.system.common.lang.Result;
import com.system.entity.Operation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface OperationService extends IService<Operation> {

    Result getOperation();
}
