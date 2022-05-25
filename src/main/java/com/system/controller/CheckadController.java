package com.system.controller;


import com.system.common.lang.Result;
import com.system.entity.Checkad;
import com.system.service.CheckadService;
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
public class CheckadController {

    @Autowired
    private CheckadService checkadService;

    @GetMapping("/admin/checkedAD")
    public Result getAll(@RequestParam(defaultValue = "1") Integer currentPage) {
        return checkadService.getAll(currentPage);
    }

    @PostMapping("/admin/pass")
    public Result pass(@RequestBody Checkad checkad) {
        return checkadService.pass(checkad);
    }

}
