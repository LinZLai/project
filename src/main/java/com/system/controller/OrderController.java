package com.system.controller;


import cn.hutool.core.map.MapUtil;
import com.system.common.lang.Result;
import com.system.entity.Order;
import com.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/getIncome")
    public Result getIncome() {
        return orderService.getIncome();
    }

    @GetMapping("/buy")
    public Result buy(@RequestParam("money") String money, @RequestParam("id") String id) {
//        生成订单号
        String orderNumber = String.valueOf(System.currentTimeMillis());
        id = "用户id：" + id;
//        转换为double
        String payUrl = "http://localhost:8081/alipay/pay?id=" + id + "&orderNumber=" + orderNumber + "&totalAmount=" + money;
        return Result.success(payUrl);
    }

    @GetMapping("/getOrder")
    public Result getOrder(@RequestParam("id") Integer id) {
        return orderService.getOrder(id);
    }
}
