package com.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.lang.Result;
import com.system.entity.Order;
import com.system.mapper.OrderMapper;
import com.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.hutool.core.map.MapUtil;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Result getIncome() {
        double allIncome = 0;
        int year = new Date().getYear() + 1900;
        int lastYear = year - 1;
        int beforeLastYear = year - 2;
        Collection<Integer> incomeEveryYear = orderMapper.getIcomeEveryYear(year, lastYear, beforeLastYear);
        Collection<Integer> income = orderMapper.getIcome(year);
        for (Integer integer : income) {
            allIncome += integer;
        }
        return Result.success(MapUtil.builder()
                .put("incomeEveryYear", incomeEveryYear)
                .put("allIncome", allIncome)
                .put("income", income)
                .put("year", year)
                .build());
    }

    @Override
    public Result getOrder(Integer id) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().eq("owner_ID", id));
        return Result.success(orders);
    }

}
