package com.system.controller;


import com.system.common.dto.ChangeState;
import com.system.common.dto.DeleteDto;
import com.system.common.dto.SearchDto;
import com.system.common.lang.Result;
import com.system.service.AlladService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
@RestController
public class AlladController {

    @Autowired
    private AlladService alladService;

    @GetMapping("/admin/getAll")
    public Result getAll(@RequestParam(defaultValue = "1") Integer currentPage) {
        return alladService.getAll(currentPage);
    }

    @GetMapping("/admin/getAD")
    public Result getAD(@RequestParam Integer id) {
        return alladService.getAD(id);
    }

    @PostMapping("/admin/changeState")
    public Result changeState(@RequestBody @Validated ChangeState changeState) {
        return alladService.changeState(changeState);
    }

    @DeleteMapping("/admin/delete")
    public Result delete(@RequestParam Integer id) {
        return alladService.delete(id);
    }

    @PostMapping("/admin/delBatch")
    public Result delBatch(@RequestBody @Validated DeleteDto deleteDto) {
        return alladService.delBatch(deleteDto.getIds());
    }

    @PostMapping("/search")
    public Result search(@RequestBody SearchDto searchDto) {
        return alladService.search(searchDto);
    }

    @GetMapping("/user/getAll")
    public Result userGetAll(@RequestParam(name = "currentPage") Integer currentPage, @RequestParam(name = "id") Integer id) {
        return alladService.userGetAll(currentPage, id);
    }

    @GetMapping("/img/{flag}")
    public void getLicense(HttpServletResponse response, @PathVariable String flag) {
        alladService.getImg(response, flag);
    }

}
