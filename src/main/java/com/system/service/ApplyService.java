package com.system.service;

import com.system.common.dto.ApplyAD;
import com.system.common.lang.Result;
import com.system.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Lai
 * @since 2022-05-08
 */
public interface ApplyService extends IService<Apply> {


    Result reject(Apply apply);

    Result imgUpload(Integer id, MultipartFile file) throws IOException;

    Result advertising(ApplyAD apply);
}
