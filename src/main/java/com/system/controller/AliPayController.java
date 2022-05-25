package com.system.controller;

import cn.hutool.core.map.MapUtil;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.system.common.lang.Result;
import com.system.entity.Order;
import com.system.entity.UserInfo;
import com.system.entity.UserOper;
import com.system.service.OrderService;
import com.system.service.UserInfoService;
import com.system.service.UserOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderService orderService;

    @Resource
    private UserOperService userOperService;

    @ResponseBody
    @GetMapping("/pay")
    public String pay(@RequestParam("id") String subject,
                      @RequestParam("orderNumber") String orderNumber,
                      @RequestParam("totalAmount") String totalAmount) {
        AlipayTradePagePayResponse response;
        try {
            response = Factory.Payment.Page()
                    .pay(subject, orderNumber, totalAmount, "http://localhost:8080/financialCenter");
        } catch (Exception e) {
            System.out.println("遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException("支付失败");
        }
        return response.getBody();
    }


    @ResponseBody
    @RequestMapping("/notify")
    public Result notify(HttpServletRequest request) throws Exception {
        System.out.println("===============支付宝异步回调================");
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }

//            支付宝验签
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);
        if (signVerified) {
            System.out.println("支付宝验签成功");
        } else {
            System.out.println("支付宝验签失败");
        }
        String id = params.get("subject");
        id = id.substring(id.indexOf("：") + 1);
//            将id转为int
        int userId = Integer.parseInt(id);
//                更新数据库
        UserInfo userInfo = userInfoService.getById(userId);
        userInfo.setBalance(userInfo.getBalance() + Double.parseDouble(params.get("invoice_amount")));
        userInfoService.updateById(userInfo);
//              添加操作记录
        Order order = new Order();
        order.setOrderNumber(params.get("out_trade_no"));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderMoney(Double.parseDouble(params.get("invoice_amount")));
        order.setMonth(LocalDateTime.now().getMonthValue());
        order.setYear(LocalDateTime.now().getYear());
        order.setOwnerId(userId);
        order.setType("充值");
        orderService.save(order);
//        储存操作
        UserOper userOper = new UserOper();
        userOper.setOwnerId(userId);
        userOper.setDate(LocalDateTime.now());
        userOper.setOperation("充值" + params.get("invoice_amount") + "元");
        userOperService.save(userOper);
        return Result.success(MapUtil.builder()
                .put("msg", "支付成功")
                .put("balance", userInfo.getBalance())
                .build());
    }
}
