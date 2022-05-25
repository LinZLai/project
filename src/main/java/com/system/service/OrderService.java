package com.system.service;

import com.system.common.lang.Result;
import com.system.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface OrderService extends IService<Order> {

    Result getIncome();

    Result getOrder(Integer id);
}
