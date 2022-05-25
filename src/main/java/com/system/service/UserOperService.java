package com.system.service;

import com.system.common.lang.Result;
import com.system.entity.UserOper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-15
 */
public interface UserOperService extends IService<UserOper> {

    Result getUserOper(Integer id);
}
