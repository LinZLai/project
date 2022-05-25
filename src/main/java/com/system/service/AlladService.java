package com.system.service;

import com.system.common.dto.ChangeState;
import com.system.common.dto.SearchDto;
import com.system.common.lang.Result;
import com.system.entity.Allad;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-03
 */
public interface AlladService extends IService<Allad> {

    Result getAD(Integer id);

    Result changeState(ChangeState changeState);

    Result delete(Integer id);

    Result delBatch(Integer[] ids);

    Result search(SearchDto searchDto);

    Result getAll(Integer currentPage);

    Result userGetAll(Integer currentPage, Integer id);

    void getImg(HttpServletResponse response, String flag);
}
