package com.system.controller;


import com.system.common.lang.Result;
import com.system.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/admin/getOperation")
    public Result getOperation() {
        return operationService.getOperation();
    }
}
