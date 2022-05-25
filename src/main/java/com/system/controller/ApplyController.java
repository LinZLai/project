package com.system.controller;


import com.system.common.dto.ApplyAD;
import com.system.common.lang.Result;
import com.system.entity.Apply;
import com.system.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-08
 */
@RestController
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PostMapping("/admin/reject")
    public Result reject(@RequestBody Apply apply) {
        return applyService.reject(apply);
    }

    @PostMapping("/img")
    public Result imgUpload(@RequestParam("id") Integer id, MultipartFile file) throws IOException {
        return applyService.imgUpload(id + 1, file);
    }

    @PostMapping("/advertising")
    public Result advertising(@RequestBody ApplyAD apply) {
        return applyService.advertising(apply);
    }
}
