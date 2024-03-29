package com.qgsg.service;

import com.qgsg.dto.SignDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.result.PageResult;

public interface SignService {

    /**
     * 签到表分页查询
     * @param signDTO
     * @return
     */
    PageResult page(SignDTO signDTO);
}
