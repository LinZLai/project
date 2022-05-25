package com.system.mapper;

import com.system.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface OrderMapper extends BaseMapper<Order> {

    Collection<Integer> getIcome(int year);

    Collection<Integer> getIcomeEveryYear(int year, int lastYear, int beforeLastYear);
}
